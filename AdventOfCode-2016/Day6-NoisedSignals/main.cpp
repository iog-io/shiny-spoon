#include <algorithm>
#include <array>
#include <string>
#include <fstream>
#include <iostream>

int main() {
   std::ifstream ifs("input.txt");
   if (!ifs)
      std::cerr << "file not found\n";

   std::array<std::array<unsigned, 26>, 8> signal{};   // {} is extremely important, so we dont need the fill in zeroes step
                                                     // http://stackoverflow.com/questions/18295302/default-initialization-of-stdarray
                                                     /*for (auto &s : signal) {
                                                     s.fill(0);
                                                     }*/

   for (std::string line; std::getline(ifs, line);) {
      for (unsigned i{ 0 }; i < line.length(); ++i) {
         ++signal[i][line.at(i) - 'a'];
      }
   }

   std::string most_frequent(signal.size(), '_');
   std::string least_common(signal.size(), '_');
   for (unsigned i{ 0 }; i < signal.size(); ++i) {
      most_frequent[i] = (char)(std::max_element(signal[i].begin(), signal[i].end()) - signal[i].begin() + 'a');
      least_common[i] = (char)(std::min_element(signal[i].begin(), signal[i].end()) - signal[i].begin() + 'a');
   }

   std::cout << most_frequent << "\n";
   std::cout << least_common << "\n";

   return EXIT_SUCCESS;
}