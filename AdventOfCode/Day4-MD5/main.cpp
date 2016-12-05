#include <string>
#include <iostream>
#include <regex>
#include <sstream>

#include "md5.h"

using std::cout; using std::endl; using std::string;
// namespace AOC
// {
// 	namespace day4
// 	{
		int main(int argc, char *argv[])
		{
			/*string hash = md5("abcdef609043");
			cout << "md5 of 'abcdef609043': " << hash << endl;
			hash = md5("pqrstuv1048970");
			cout << "md5 of 'pqrstuv1048970': " << hash << endl;
         hash = md5("abc3231929");
         cout << "md5 of 'abc3231929': " << hash << endl;
         hash = md5("abc5017308");
         cout << "md5 of 'abc5017308': " << hash << endl;
         hash = md5("abc5278568");
         cout << "md5 of 'abc5278568': " << hash << endl;*/

         /*string hash = md5("abc5278568");
         if (hash.substr(0, 5) == "00000")
            std::cout << hash << "\n";*/

         std::ostringstream oss;
         const string ID = "wtnhxymk";
         unsigned index = 9433035;
         unsigned counter = 0;
         string hash;
         string password(8, '*');
         /* from part 1*/
         password[2] = '7';
         password[4] = '6';
         password[1] = '3';
         password[7] = 'c';

         while (counter < 4)
         {
            oss.str("");
            oss << ID << index;
            hash = md5(oss.str());
            if (hash.substr(0, 5) == "00000") {
               char position = hash[5];
               if ((position >= '0' && position <= '7') && password[position - '0'] == '*') {
                  password[position - '0'] = hash[6];
                  std::cout << hash << " " << index << "\n";
                  ++counter;
               }
            }
            ++index;
         }

         std::cout << password << "\n";

			/*string key = "iwrupvqb";
			string foo = key.append(std::to_string(1000));
			cout << foo << " and " << md5(key) << endl;

			string regex_str = "^[0]{5}.*";
			std::regex reg1(regex_str, std::regex_constants::icase);

			if (std::regex_search(hash, reg1))
				cout << "Found\n";*/

			return 0;
		}
// 	}	
// }
