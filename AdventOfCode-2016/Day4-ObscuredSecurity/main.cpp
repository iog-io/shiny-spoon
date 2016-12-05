#include <fstream>
#include <iostream>
#include <string>
#include <map>
#include <set>
#include <array>
#include <sstream>
#include <assert.h>

int main()
{
   std::ifstream ifs("input.txt");
   if (!ifs)
      std::cerr << "file not found.\n";

   std::ostringstream oss;
   int sum = 0;
   for (std::string line; std::getline(ifs, line); )
   {
      auto lastDashPosition = line.find_last_of('-');
      std::string name = line.substr(0, lastDashPosition);
      std::string checkingPart = line.substr(lastDashPosition + 1);
      auto openBracket = checkingPart.find_first_of('[');
      int code = std::stoi(checkingPart.substr(0, openBracket));
      std::string checksum = checkingPart.substr(openBracket + 1, checkingPart.find_first_of(']') - openBracket - 1);

//       std::cout << name << " " << code << " " << checksum << "\n";

      std::array<unsigned, 26> freq; freq.fill(0);
      for (size_t i = 0; i < name.length(); i++)
      {
         if (name.at(i) != '-') {
            ++freq[name.at(i) - 'a'];
         }
      }
      std::map<unsigned, std::set<char>> st;
      for (size_t i = 0; i < freq.size(); i++)
      {
         if (freq[i] != 0) {
            st[freq[i]].insert('a' + i);
         }
      }

      for (auto first = st.rbegin(); first != st.rend() && oss.str().length() < 5; ++first)
      {
         for (auto ch : first->second) {
            if (oss.str().length() >= 5) break;
            oss << ch;
         }
      }
//       assert(oss.str().length() == 5);

      // part 1
      /*if (oss.str() == checksum) {
         sum += code;
      }
      std::cout << "\nsum = " << sum << "\n";*/

      // part 2
      if (oss.str() == checksum) {
         oss.str("");
         for (size_t i = 0; i < name.length(); i++)
         {
            if (name[i] == '-') oss << " ";
            else oss << (char)('a' + ((name[i] + (code % 26) - 'a') % 26));
         }
//          std::cout << oss.str() << "\n";
         if (oss.str() == "northpole object storage") {
            std::cout << code << "\n";
            break;
         }
      }
      oss.str("");
   }

   return EXIT_SUCCESS;
}