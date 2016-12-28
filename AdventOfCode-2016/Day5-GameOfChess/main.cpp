#include <string>
#include <iostream>
#include <sstream>
#include "..\Commonwealth\md5.h"


int main(int argc, char *argv[])
{
   const std::string ID = "wtnhxymk";
   std::string hash;
   std::string password(8, '#');
   unsigned index = 0;
   unsigned counter = 0;
   std::ostringstream oss;

   while (counter < 8)
   {
      oss.str("");
      oss << ID << index;
      hash = md5(oss.str());
      if (hash.substr(0, 5) == "00000") {
         /************************************************************************/
         /* part 2                                                               */
         /************************************************************************/
         unsigned position = hash[5] - '0';
         if (position >= 0 && position <= 7 && password[position] == '#') {
            password[position] = hash[6];
            ++counter;
         }

         /************************************************************************/
         /* part 1                                                               */
         /************************************************************************/
//          password[counter++] = hash[5];
      }
      ++index;
   }

   std::cout << password << "\n";

   return 0;
}
