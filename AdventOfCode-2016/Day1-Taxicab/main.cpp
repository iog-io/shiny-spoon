//
// Created by zeno on 01.12.2016.
//

/**
* Day 1: No Time for a Taxicab
*/

#include "sfml/Vector2.hpp"
#include <string>
#include <stdexcept>
#include <fstream>
#include <iostream>
#include <set>
#include <array>

const std::array<sf::Vector2i, 4> incs{
   sf::Vector2i{ 0, 1 },  /* North */
   sf::Vector2i{ 1, 0 },  /* East */
   sf::Vector2i{ 0, -1 }, /* South */
   sf::Vector2i{ -1, 0 }  /* West */
};

class Player
{
private:
   int face;
   sf::Vector2i position;

public:
   Player() : face{ 0 }, position{ 0,0 } {}
   void moveTo(sf::Vector2i);
   sf::Vector2i getPosition() const {
      return position;
   }
};

sf::Vector2i parseCommand(std::string cmdStr)
{
   sf::Vector2i cmd;
   switch (cmdStr[0])
   {
   case 'R':
      cmd.x = +1;
      break;
   case 'L':
      cmd.x = -1;
      break;
   default:
      throw std::invalid_argument("???????!!!!!!!");
   }
   cmd.y = std::stoi(cmdStr.substr(1));
   return cmd;
}

void Player::moveTo(sf::Vector2i cmd)
{
   face = (face + cmd.x) < 0 ? incs.size() - 1 : (face + cmd.x) % incs.size();
   position += cmd.y * incs[face];
}

int main(int argc, char **argv)
{
   std::ifstream fin(argv[1]);
   if (!fin) std::cerr << "file not found!\n";

   std::set<std::pair<int, int>> visited;
   Player player;
   for (std::string command; fin >> command; )
   {
      if (command.back() == ',') command.pop_back();
      sf::Vector2i before = player.getPosition();
      player.moveTo(parseCommand(command));
      // part 2
      sf::Vector2i after = player.getPosition();
      if (before.x == after.x) {
         int inc = before.y < after.y ? 1 : -1;
         for (int i = before.y; i != after.y; i += inc) {
            auto result = visited.insert({ before.x, i });
            if (!result.second) {
               std::cout << abs(before.x) + abs(i) << "\n";
               exit(0);
            }
         }
      }
      else if (before.y == after.y) {
         int inc = before.x < after.x ? 1 : -1;
         for (int i = before.x; i != after.x; i += inc) {
            auto result = visited.insert({ i, before.y });
            if (!result.second) {
               std::cout << abs(i) + abs(before.y) << "\n";
               exit(0);
            }
         }
      }
   }

   // part 1
   //    std::cout << abs(player.getPosition().x) + abs(player.getPosition().y) << "\n";

   return 0;
}