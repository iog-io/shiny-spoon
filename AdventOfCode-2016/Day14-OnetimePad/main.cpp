#include <iostream>
#include <sstream>
#include <string>
#include <set>
#include <regex>
#include <iterator>
#include <map>
#include <cctype>
#include "..\Commonwealth\md5.h"
#include <assert.h>

const std::string SALT = "ngcjuoqr";
// const std::string SALT = "abc";

inline std::string& hashmore(std::string& str) {
   for (size_t i = 0; i < 2017; i++)
   {
      str.swap(md5(str));
   }
   return str;
}

int main(int argc, char *argv[]) {

   std::set<unsigned> padkey;
   std::map<unsigned char, std::vector<unsigned>> mapping;
   std::ostringstream oss;
   std::regex reTriple(R"((.)\1{2})", std::regex::optimize);
   //    std::regex reQuintuple(R"((.)\1{4})", std::regex::optimize);
   unsigned index{ 0 };

   while (padkey.size() < 80)
   {
      oss.str("");
      oss << SALT << index;

//       std::string hash{ md5(oss.str()) }; // part 1
      std::string hash{ hashmore(oss.str()) }; // part 2

      unsigned mpos{ 0 };
      std::match_results<std::string::iterator> matches;
      for (std::string::iterator fromIt = hash.begin();
         std::regex_search(fromIt, hash.end(), matches, reTriple);) {

         unsigned tmp{ mpos };

         int start = matches.position();
         unsigned char match_str = matches.str(1).at(0);

         mpos += start + 3;
         if (mpos + 1 < hash.length() && hash[mpos] == match_str && hash[mpos + 1] == match_str) {
            for (auto idx : mapping[match_str]) {
               if (index <= idx + 1000) {
                  padkey.insert(idx);
               }
            }
            mapping[match_str] = {};
         }

         if (tmp == 0) {
            mapping[match_str].push_back(index);
         }

         fromIt += mpos - tmp;
      }

      index++;
   }

   unsigned numeration{ 1 };
   for (auto item : padkey) {
      std::cout << numeration++ << " : " << item << "\n";
   }

   return EXIT_SUCCESS;
}

/*
int main(int argc, char *argv[]) {

   std::vector<unsigned> padkey; padkey.reserve(64);
   std::map<unsigned char, std::vector<unsigned>> mapping;
   std::ostringstream oss;
   std::regex reTriple(R"((.)\1{2})", std::regex::optimize);
//    std::regex reQuintuple(R"((.)\1{4})", std::regex::optimize);
   unsigned index{ 0 };
   std::string hash;

   while (padkey.size() < 64)
   {
      oss.str("");
      oss << SALT << index;
      hash = md5(oss.str());
//       std::transform(hash.begin(), hash.end(), hash.begin(), ::tolower);

      unsigned mpos{ 0 };
      std::match_results<std::string::iterator> matches;
      for (std::string::iterator fromIt = hash.begin();
         std::regex_search(fromIt, hash.end(), matches, reTriple);) {

         unsigned tmp{ mpos };

         int start = matches.position();
         unsigned char match_str = matches.str(1).at(0);

         mpos += start + 3;
         if (mpos + 1 < hash.length() && hash[mpos] == match_str && hash[mpos + 1] == match_str) {
            for (auto idx : mapping[match_str]) {
               if (index <= idx + 1000) {
                  padkey.push_back(idx);
               }
            }
            mapping[match_str] = {};
         }

         if (tmp == 0) {
            mapping[match_str].push_back(index);
         }

         fromIt += / *start + 5* /mpos - tmp;
      }

      / *std::smatch match;
      if (std::regex_search(hash, match, reTriple)) {
         mapping[match.str(1).at(0)].push_back(index);
      }* /

      / *auto triplet_begin = std::sregex_iterator(hash.begin(), hash.end(), reQuintuple);
      auto words_end = std::sregex_iterator();
      for (std::sregex_iterator it = triplet_begin; it != words_end; ++it)
      {
         std::smatch match = *it;
         unsigned char match_str = match.str()[0];
         for (unsigned idx : mapping[match_str]) {
            if (index <= 1000 + idx) {
               padkey.push_back(idx);
            }
         }
         mapping[match_str].swap(std::vector<unsigned>());
      }
      std::smatch match;
      if (std::regex_search(hash, match, reTriple)) {
         mapping[match.str(1)[0]].push_back(index);
      }* /

      index++;
   }

   / *unsigned numeration{ 1 };
   for (auto item : padkey) {
      std::cout << numeration++ << " : " << item << "\n";
   }* /
   std::cout << padkey[63] << "\n";

   return EXIT_SUCCESS;
}*/