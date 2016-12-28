#include <map>
#include <regex>
#include <string>
#include <iostream>
#include <fstream>

/*auto main() -> int
{
   std::ifstream ifs{ "input.txt" };
   if (!ifs) return EXIT_FAILURE;

   std::map<uint32_t, uint32_t> used{};
   std::map<uint32_t, uint32_t> avail{};

   std::regex rx{ "^/dev/grid/node-x(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)%$" };
   std::smatch matches{};
   for (std::string line{}; std::getline(ifs, line); )
   {
      if (std::regex_search(line, matches, rx))
      {
         ++used[std::stoul(matches.str(4))];
         ++avail[std::stoul(matches.str(5))];
      }
      else
      {
         std::cerr << "WTF! " << line << "\n";
      }
   }

   uint32_t counter{};
   for(const auto & pused : used)
   {
      for(auto rbeg{rbegin(avail)}; rbeg != rend(avail); ++rbeg)
      {
         if (pused.first > 0 && pused.first <= rbeg->first) counter += (pused.second * rbeg->second);
         else break;
      }
   }

   std::cout << counter << "\n";
}*/

#include <array>

auto main() -> int
{
   using std::stoi;
   using std::stoul;

   std::ifstream ifs{ "input.txt" };
   if (!ifs) return EXIT_FAILURE;

   const uint32_t W{ 38 };
   const uint32_t H{ 26 };
   const uint32_t SPACE{ 92 };

   std::array<std::array<uint8_t, W>, H> G{};

   std::regex rx{ "^/dev/grid/node-x(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)%$" };
   std::smatch matches{};
   for (std::string line{}; std::getline(ifs, line); )
   {
      if (std::regex_search(line, matches, rx)) 
      {
         G[stoi(matches.str(2))][stoi(matches.str(1))] = stoul(matches.str(4)) <= SPACE ? '.' : '#';
      }
   }

   G[22][17] = '_';
   G[0][37] = 'G';
   G[0][0] = '$';

   for (const auto& a : G)
   {
      for (const auto b : a)
      {
         std::cout << b;
      }
      std::cout << "\n";
   }

   std::cout << "\nLet's solve it by hand!\n";
}
