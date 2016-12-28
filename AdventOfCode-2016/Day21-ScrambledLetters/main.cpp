#include <algorithm>
#include <string>
#include <fstream>
#include <iostream>
#include <utility>
#include <regex>
#include <assert.h>

auto main0() -> int
{
   /*std::string pass{ "abcde" };
   std::cout << "given: " << pass << "\n";

   std::cout << "\nswap position 4 with position 0 => ebcda\n";
   std::iter_swap(begin(pass), begin(pass) + 4); std::cout << pass << "\n";
   assert(pass == "ebcda");

   std::cout << "\nswap letter d with letter b => edcba\n";
   std::iter_swap(
      std::find(begin(pass), end(pass), 'd'),
      std::find(begin(pass), end(pass), 'b')
   ); std::cout << pass << "\n";
   assert(pass == "edcba");

   std::cout << "\nreverse positions 0 through 4 => abcde\n";
   std::reverse(begin(pass), begin(pass) + 5); std::cout << pass << "\n";
   assert(pass == "abcde");

   std::cout << "\nrotate left 1 step => bcdea\n";
   std::rotate(begin(pass), begin(pass) + 1, end(pass)); std::cout << pass << "\n";
   assert(pass == "bcdea");

   std::cout << "\nmove position 1 to position 4 => bdeac\n";
   auto tmp = pass.at(1);
   std::copy(begin(pass) + 2, begin(pass) + 5, begin(pass) + 1);
   pass[4] = tmp; std::cout << pass << "\n";
   assert(pass == "bdeac");

   std::cout << "\nmove position 3 to position 0 => abdec\n";
   tmp = pass.at(3);
   std::copy(begin(pass) + 0, begin(pass) + 3, begin(pass) + 1);
   pass[0] = tmp; std::cout << pass << "\n";
   assert(pass == "abdec");

   std::cout << "\nrotate based on position of letter b => ecabd\n";
   auto b_index = std::distance(begin(pass), std::find(begin(pass), end(pass), 'b')); // 1
   auto at_least4 = b_index >= 4 ? 1 : 0;
   std::rotate(rbegin(pass), rbegin(pass) + 1 + b_index + at_least4, rend(pass));
   std::cout << pass << "\n";
   assert(pass == "ecabd");

   std::cout << "\nrotate based on position of letter d => decab\n";
   auto d_index = std::distance(begin(pass), std::find(begin(pass), end(pass), 'd'));  // 4
   at_least4 = d_index >= 4 ? 1 : 0;
   std::rotate(rbegin(pass), rbegin(pass) + (1 + d_index + at_least4) % pass.length(), rend(pass));
   std::cout << pass << "\n";
   assert(pass == "decab");*/

   return 0;
}

auto main1() -> int
{
   /*std::string pwd("abcdefgh");
   auto const PWD_LENGTH = pwd.length();

   auto ifs = std::ifstream ("input.txt");
   if (!ifs) {
      std::cerr << "input file not found\n";
   }

   std::regex reSwap       { R"(^swap (position|letter) (.) with (position|letter) (.)$)" };
   std::regex reRotate     { R"(^rotate (left|right) (\d) steps?$)" };
   std::regex reRotateBased{ R"(^rotate based on position of letter (.)$)" };
   std::regex reReverse    { R"(^reverse positions (.) through (.)$)" };
   std::regex reMove       { R"(^move position (.) to position (.)$)" };

   for (std::string line; std::getline(ifs, line);) {
      
      std::smatch matches;
      
      if (std::regex_search(line, matches, reSwap)) {
         auto iter1 = matches.str(1) == "position" ? begin(pwd) + std::stoul(matches.str(2)) : std::find(begin(pwd), end(pwd), matches.str(2).at(0));
         auto iter2 = matches.str(3) == "position" ? begin(pwd) + std::stoul(matches.str(4)) : std::find(begin(pwd), end(pwd), matches.str(4).at(0));
         std::iter_swap(iter1, iter2);
      }
      else if (std::regex_search(line, matches, reRotate)) {
         if (matches.str(1) == "left") {
            std::rotate(begin(pwd), begin(pwd) + stoul(matches.str(2)), end(pwd));
         }
         else {
            std::rotate(rbegin(pwd), rbegin(pwd) + stoul(matches.str(2)), rend(pwd));
         }
      }
      else if (std::regex_search(line, matches, reRotateBased)) {
         auto pos = std::distance(begin(pwd), std::find(begin(pwd), end(pwd), matches.str(1).at(0)));
         auto gt4 = pos >= 4 ? 1 : 0;
         std::rotate(rbegin(pwd), rbegin(pwd) + (1 + pos + gt4) % PWD_LENGTH, rend(pwd));
      }
      else if (std::regex_search(line, matches, reReverse)) {
         std::reverse(begin(pwd) + std::stoul(matches.str(1)), begin(pwd) + std::stoul(matches.str(2)) + 1);
      }
      else if (std::regex_search(line, matches, reMove)) {
         auto moved_pos = std::stoul(matches.str(1));
         auto dest_pos = std::stoul(matches.str(2));
         auto letter = pwd.at(moved_pos);
         if (moved_pos < dest_pos) {
            std::copy(begin(pwd) + moved_pos + 1, begin(pwd) + dest_pos + 1, begin(pwd) + moved_pos);
         }
         else {
            std::copy(begin(pwd) + dest_pos, begin(pwd) + moved_pos, begin(pwd) + dest_pos + 1);
         }
         pwd[dest_pos] = letter;
      }
      else {
         std::cerr << "Wtf!! " << line << "\n";
      }
   }

   std::cout << pwd << "\n";*/

   return 0;
}

auto main() -> int
{
   //std::string pwd("decab");  // sample
   std::string pwd("fbgdceah");
   auto const PWD_LENGTH = pwd.length();

   auto ifs = std::ifstream("input.txt");
   if (!ifs) {
      std::cerr << "input file not found\n";
   }

   std::regex reSwap{ R"(^swap (position|letter) (.) with (position|letter) (.)$)" };
   std::regex reRotate{ R"(^rotate (left|right) (\d) steps?$)" };
   std::regex reRotateBased{ R"(^rotate based on position of letter (.)$)" };
   std::regex reReverse{ R"(^reverse positions (.) through (.)$)" };
   std::regex reMove{ R"(^move position (.) to position (.)$)" };

   std::vector<std::string> operations;
   for (std::string line; std::getline(ifs, line);) {
      operations.emplace_back(line);
   }

   for (auto beg = rbegin(operations); beg != rend(operations); ++beg)
   {
      std::string line(*beg);
      std::smatch matches;

      if (std::regex_search(line, matches, reSwap)) {
         auto iter1 = matches.str(1) == "position" ? begin(pwd) + std::stoul(matches.str(2)) : std::find(begin(pwd), end(pwd), matches.str(2).at(0));
         auto iter2 = matches.str(3) == "position" ? begin(pwd) + std::stoul(matches.str(4)) : std::find(begin(pwd), end(pwd), matches.str(4).at(0));
         std::iter_swap(iter1, iter2);
      }
      else if (std::regex_search(line, matches, reRotate)) {
         if (matches.str(1) == "right") {
            std::rotate(begin(pwd), begin(pwd) + stoul(matches.str(2)), end(pwd));
         }
         else {
            std::rotate(rbegin(pwd), rbegin(pwd) + stoul(matches.str(2)), rend(pwd));
         }
      }
      else if (std::regex_search(line, matches, reRotateBased)) {
         size_t i;
         for (i = 1; i < PWD_LENGTH; i++)
         {
            std::string pwdCpy(begin(pwd), end(pwd));
            // rotate left 1 position
            std::rotate(begin(pwdCpy), begin(pwdCpy) + (i % PWD_LENGTH), end(pwdCpy));

            // rotate back to right based on questioned character
            auto pos = std::distance(begin(pwdCpy), std::find(begin(pwdCpy), end(pwdCpy), matches.str(1).at(0)));
            auto gt4 = pos >= 4 ? 1 : 0;
            std::rotate(rbegin(pwdCpy), rbegin(pwdCpy) + (1 + pos + gt4) % PWD_LENGTH, rend(pwdCpy));
            if (pwdCpy == pwd) break;
         }
         std::rotate(begin(pwd), begin(pwd) + (i % PWD_LENGTH), end(pwd));
      }
      else if (std::regex_search(line, matches, reReverse)) {
         std::reverse(begin(pwd) + std::stoul(matches.str(1)), begin(pwd) + std::stoul(matches.str(2)) + 1);
      }
      else if (std::regex_search(line, matches, reMove)) {
         auto moved_pos = std::stoul(matches.str(1));
         auto dest_pos = std::stoul(matches.str(2));
         std::swap(moved_pos, dest_pos);
         auto letter = pwd.at(moved_pos);
         if (moved_pos < dest_pos) {
            std::copy(begin(pwd) + moved_pos + 1, begin(pwd) + dest_pos + 1, begin(pwd) + moved_pos);
         }
         else {
            std::copy(begin(pwd) + dest_pos, begin(pwd) + moved_pos, begin(pwd) + dest_pos + 1);
         }
         pwd[dest_pos] = letter;
      }
      else {
         std::cerr << "Wtf!! " << line << "\n";
      }
   }

   std::cout << pwd << "\n";
}
