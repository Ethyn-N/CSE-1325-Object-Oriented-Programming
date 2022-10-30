#include <iostream>
#include <string>

void reverse(std::string str) {
    for (int i = str.length() - 1; i >= 0; i--)
        std::cout << str[i];
    std::cout << std::endl;
}

int main(int argc, char* argv[]) {
    if (argc <= 1) {
        std::cout << "USAGE: ./a.out \"STRING\"" << std::endl;
        return -1;
    }

    int i{1};

    while (argv[i] != NULL) {
        reverse(argv[i]);
        i++;
    }

    return 0;
}