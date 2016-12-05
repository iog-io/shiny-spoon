#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <array>
#include <map>
#include <vector>

using std::string;
using std::array;
using std::cout;
using std::map;
using std::vector;

unsigned int part_one(void)
{
	std::regex badPat(R"(ab|cd|pq|xy)", std::regex_constants::icase);
	std::regex repeatPat(R"(([a-z])\1)", std::regex_constants::icase);
	//std::regex vowelPat("(.*[aeiou].*){3,}", std::regex_constants::icase); // works, but very slow

	unsigned int niceStrings = 0;
	vector<char> vowels;

	std::ifstream fin("input.txt");
	//fin.open("input.txt");
	if (!fin) std::cerr << "file not found\n";

	if (fin.is_open())
	{
		for (string line; std::getline(fin, line);)
		{
			if (!std::regex_search(line, badPat) && std::regex_search(line, repeatPat))
			{
				vowels.clear();
				for (size_t i = 0; i < line.length(); i++)
				{
					switch (line[i])
					{
					case 'a':
					case 'e':
					case 'i':
					case 'o':
					case 'u':
						vowels.push_back(line[i]);
						break;
					default:
						break;
					}
					if (vowels.size() >= 3)
					{
						niceStrings++;
						break;
					}
				}
			}
		}
		fin.close();
	}

	return niceStrings;
}

unsigned int part_two(void)
{
	std::regex overlapPat(R"(([a-z])\1\1)");
	std::regex pairPat(R"(([a-z][a-z]).*\1)");
	std::regex symPat(R"(([a-z]).\1)");
	
	std::ifstream fin("input.txt");
	if (!fin) std::cerr << "file not found\n";

	unsigned int niceStrings = 0;

	for (string line; std::getline(fin, line);)
	{
		if (!std::regex_search(line, overlapPat))
		{
			if (std::regex_search(line, pairPat) && std::regex_search(line, symPat))
				niceStrings++;
		}
	}
	fin.close();

	return niceStrings;
}

int main(int argc, char * argv[])
{
	//cout << "Part 1, number of nice strings: " << part_one() << "\n"; // 236

	cout << "Part 2, number of nice strings: " << part_two() << "\n";

	return EXIT_FAILURE;
}
