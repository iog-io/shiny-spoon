#include <fstream>
#include <string>
#include <utility>
#include <iostream>
#include <sstream>
#include <algorithm>

/*int main() {

   const size_t SIZE = 400000;

   std::ifstream ifs("input.txt");
   if (!ifs)
      std::cerr << "file not found\n";

   std::string input; std::getline(ifs, input);
   std::ostringstream oss;
   oss << "." << input << ".";

   input.swap(oss.str());
   std::string nextline(input.length(), '.');

   uint32_t row_counter{ 1 };
   int safe_counter{ std::count(std::begin(input), std::end(input), '.') - 2 };
   
   while (row_counter < SIZE)
   {
      for (size_t i = 1; i < input.length() - 1; i++)
      {
         int mask{ 0 }; char tile{ '.' };
         if (input[i - 1] == '.') mask ^= 4;
         if (input[i] == '.') mask ^= 2;
         if (input[i + 1] == '.') mask ^= 1;
         if (mask == 1 || mask == 4 || mask == 3 || mask == 6) tile = '^';
         else safe_counter++;
         nextline[i] = tile;
      }
      row_counter++;
      std::swap(input, nextline);
   }
   
   std::cout << safe_counter << "\n";

   return EXIT_SUCCESS;
}*/

#include <bitset>
int main() {

   const size_t SIZE = 400000;

   std::ifstream ifs("input.txt");
   if (!ifs)
      std::cerr << "file not found\n";

   std::string input; std::getline(ifs, input);
   //const uint32_t bitlen = input.length() + 2;
   // magic number 102 was 100 chars from input.txt and 2 virtual safe tiles (wall)
   std::bitset<102> current;
   current.set();
   for (size_t i = 0; i < input.length(); i++)
   {
      if (input[i] == '^') current.set(i + 1, false);
   }

   std::bitset<102> next(current);

   uint32_t row_counter{ 1 };
   uint32_t safe_counter{ current.count() - 2 };

   while (row_counter < SIZE)
   {
      for (size_t i = 1; i < current.size() - 1; i++)
      {
         int mask{ 0 };
         bool tile{ true };
         if (current[i - 1]) mask ^= 4;
         if (current[i])     mask ^= 2;
         if (current[i + 1]) mask ^= 1;
         if (mask == 1 || mask == 4 || mask == 3 || mask == 6) tile = false;
         //else safe_counter++;
         next.set(i, tile);
      }
      safe_counter += next.count() - 2;
      std::swap(current, next);
      row_counter++;
   }

   std::cout << safe_counter << "\n";

   return EXIT_SUCCESS;
}