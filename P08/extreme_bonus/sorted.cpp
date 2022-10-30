#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

int main(int argc, char* argv[]) {
    std::vector<std::string> v;
    std::string line;

    while (std::getline(std::cin, line))
        v.push_back(line);

    std::sort(v.begin(), v.end());

    for(auto i : v) 
        std::cout << i << std::endl;

    return 0;
}