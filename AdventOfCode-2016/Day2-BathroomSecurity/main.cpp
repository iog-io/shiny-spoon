// Day2-BathroomSecurity.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

#include <fstream>
#include <string>
#include <iostream>
#include <stdexcept>

const int N = 5;

const int numpad[][3] = {
   {1,2,3},
   {4,5,6},
   {7,8,9}
};

const int numpad2[][5] = {
   {0, 0, 1, 0, 0},
   {0, 2, 3, 4, 0},
   {5, 6, 7, 8, 9},
   {0, 10, 11, 12, 0},
   {0, 0, 13, 0, 0}
};

inline bool rangeCheck(int idx)
{
   return idx >= 0 && idx < N;
}

inline bool entryCheck(int r, int c)
{
   return numpad2[r][c] != 0;
}

class Turtle
{
public:
   Turtle(int r, int c) : x{ r }, y{ c } {}
   void Up()
   {
      if (rangeCheck(x - 1) && entryCheck(x - 1, y)) {
         x -= 1;
      }
   }
   void Down()
   {
      if (rangeCheck(x + 1) && entryCheck(x + 1, y)) {
         x += 1;
      }
   }
   void Left()
   {
      if (rangeCheck(y - 1) && entryCheck(x, y - 1)) {
         y -= 1;
      }
   }
   void Right()
   {
      if (rangeCheck(y + 1) && entryCheck(x, y + 1)) {
         y += 1;
      }
   }
   int Number()
   {
      return numpad2[x][y];
   }

private:
   int x;
   int y;
};

int main()
{
   std::ifstream ifs("input.txt");
   if (!ifs) {
      std::cerr << "file not found\n";
   }

   Turtle turtle{ 2, 0 };
   for (std::string line; getline(ifs, line); ) {
      for (size_t i = 0; i < line.length(); i++)
      {
         switch (line.at(i))
         {
         case 'U':
            turtle.Up();
            break;
         case 'D':
            turtle.Down();
            break;
         case 'L':
            turtle.Left();
            break;
         case 'R':
            turtle.Right();
            break;
         default:
            throw std::logic_error("");
         }
      }
      std::cout << turtle.Number() << "\n";
   }

    return 0;
}
