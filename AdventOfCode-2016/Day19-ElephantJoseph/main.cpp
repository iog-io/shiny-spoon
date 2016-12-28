#include <bitset>
#include <iostream>
#include <cassert>
#include <vector>

//auto main() -> int
//{
//   auto const ELVES = size_t{ 3001330 };
//   auto elves = std::bitset<ELVES>{};
//   elves.set();
//
//   auto switcher = bool{ true };
//   while (elves.count() != 1)
//   {
//      for (auto i = size_t{ 0 }; i < elves.size(); i++)
//      {
//         if (elves.test(i))
//         {
//            if (!switcher) {
//               elves.reset(i);
//            }
//            switcher = !switcher;
//         }
//      }
//   }
//
//   for (size_t i = 0; i < elves.size(); i++)
//   {
//      if (elves.test(i)) {
//         std::cout << ++i << "\n";
//         break;
//      }
//   }
//}

auto main() -> int
{
   const size_t Size = 3001330;
   auto vec = std::vector<bool>(Size, true);

   size_t bits = Size;
   while (bits > 1)
   {
      for (size_t i{0}; i < Size; ++i)
      {
         if (vec[i])
         {
            
         }
      }
   }

}
