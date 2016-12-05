// Day3-Triangles.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <fstream>
#include <iostream>
#include <cassert>
#include <tuple>

int clean(int x)
{
   if (x < 10) return x;
   int tenth = 10;
   while (x >= tenth) tenth *= 10;
   tenth /= 10;
   return x / tenth;
}

bool isTriangle(int a, int b, int c)
{
   return abs(a - b) < c && c < (a + b);
}

int main()
{
   std::ifstream ifs("input.txt");

   if (!ifs)
      std::cerr << "file not found\n";

   int a, b, c;
   unsigned int counter = 0;
   int buffer[3][3];
   unsigned numRow = 0;
   while (ifs >> a >> b >> c) {
      if (numRow <= 2) {
         buffer[0][numRow] = a;
         buffer[1][numRow] = b;
         buffer[2][numRow] = c;
         ++numRow;
      }
      if (numRow == 3) {
         for (size_t i = 0; i < 3; i++)
         {
            if (clean(buffer[i][0]) == clean(buffer[i][1]) == clean(buffer[i][2]))
               if (isTriangle(buffer[i][0], buffer[i][1], buffer[i][2]))
                  ++counter;
         }
         numRow = 0;
      }
   }

   std::cout << counter << "\n";
   return 0;
}

