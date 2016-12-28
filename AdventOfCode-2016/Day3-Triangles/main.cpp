// Day3-Triangles.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <fstream>
#include <iostream>
#include <cassert>
#include <tuple>
#include <iterator>
#include <vector>

bool isTriangle(int a, int b, int c)
{
   return abs(a - b) < c && c < (a + b);
}


int main()
{
   std::ifstream ifs("input.txt");

   if (!ifs)
      std::cerr << "file not found\n";

   std::vector<unsigned> buffer[3];
   unsigned cntPtOne{};
   unsigned cntPtTwo{};
   for (unsigned a, b, c, numRow{}; ifs >> a >> b >> c; ) {
      /**
      * Part 2
      */
      if (numRow <= 2) {
         //          buffer[numRow++] = std::move(std::vector<int>{ a, b, c });
         buffer[numRow++] = { a, b, c };
      }
      if (numRow == 3) {
         numRow = 0;
         for (size_t i = 0; i < 3; i++) {
            if (isTriangle(buffer[0][i], buffer[1][i], buffer[2][i])) {
               ++cntPtTwo;
            }
         }
      }

      /**
      * Part 1
      */
      if (isTriangle(a, b, c)) ++cntPtOne;
   }

   std::cout << "Easy: " << cntPtOne << " triangles\n";
   std::cout << "Morning-wood hard: " << cntPtTwo << " triangles\n";

   return EXIT_SUCCESS;
}

/*
void
solve(bool part2, std::istream& is, std::ostream& os)
{
   int num{ part2 ? 3 : 1 };
   int sum{ 0 };
   std::vector<int> tri(3 * num);
   for (std::istream_iterator<int> itr{ is }, end{}; itr != end;) {
      for (int& t : tri)
         t = *itr++;
      for (int n{ 0 }; n < num; ++n)
         sum += isTriangle(tri[n], tri[n + num], tri[n + 2 * num]);
   }
   os << sum << std::endl;
}

int
main()
{
   std::ifstream ifs("input.txt");
   if (!ifs)
      std::cerr << "file not found\n";

   solve(false, ifs, std::cout);
   // going back to the beginning of the fstream
   ifs.clear();
   ifs.seekg(0, std::ios::beg);
   solve(true, ifs, std::cout);

   return EXIT_SUCCESS;
}
*/

