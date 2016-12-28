#include <map>
#include <iostream>

auto main() -> int
{
   std::map<uint32_t, uint32_t> ranges;
   ranges[5] = 8;
   ranges[0] = 2;

   auto equal_iter = ranges.equal_range(0);
   if (equal_iter.first != std::end(ranges))
   {
      std::cout << equal_iter.first->first << "\n";
      std::cout << std::boolalpha << (equal_iter.first == ranges.lower_bound(0)) << std::noboolalpha << "\n";
   }

   if (equal_iter.second != std::end(ranges))
   {
      std::cout << equal_iter.second->first << "\n";
      std::cout << std::boolalpha << (equal_iter.second == ranges.upper_bound(0)) << "\n";
   }

   return EXIT_SUCCESS;
}