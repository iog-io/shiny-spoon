/*
 * This code works but very slow,
 * Try bitset or vector<bool> might be faster
 */
#include <string>
#include <vector>
#include <sstream>
#include <assert.h>
#include <iostream>

int main() {

   const size_t DISK_LEN{ 20 };
   const std::string INPUT("10000");

   /*const size_t DISK_LEN { 35651584 };
   const std::string INPUT("10010000000110000");*/

   size_t buffer_size{ INPUT.length() };
   while (buffer_size < DISK_LEN) {
      buffer_size *= 2;
      buffer_size++;
   }

   std::vector<char> buffer(INPUT.begin(), INPUT.end());
   buffer.reserve(buffer_size);

   while (buffer.size() < DISK_LEN) {
      buffer.push_back('0');
      for (auto it = buffer.rbegin() + 1; it != buffer.rend(); it++)
      {
         if (*it - '1' < 0) {
            buffer.push_back('1');
         }
         else {
            buffer.push_back('0');
         }
      }
   }
   buffer.resize(DISK_LEN);

//    std::cout << std::string(buffer.begin(), buffer.end()) << "\n";

   size_t group_len{ 2 };
   while ((DISK_LEN / group_len) % 2 == 0) {
      group_len *= 2;
   }
   std::string result(DISK_LEN / group_len, 'x');
   
   std::ostringstream oss;
   for (size_t i{ 0 }, cur{ 0 }; i < buffer.size(); i += group_len)
   {
      oss << std::string(buffer.begin() + i, buffer.begin() + i + group_len);
      while (oss.str().length() > 1) {
         std::string tmp(oss.str());
         oss.str("");
         for (size_t j = 0; j < tmp.length(); j += 2)
         {
            if (tmp[j] == tmp[j + 1]) {
               oss << '1';
            }
            else oss << '0';
         }
      }
      assert(oss.str().length() == 1);
      result[cur++] = oss.str()[0];
      oss.str("");
   }

   std::cout << result << "\n";

   return EXIT_SUCCESS;
}
