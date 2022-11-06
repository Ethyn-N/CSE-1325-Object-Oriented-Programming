#include "Matrix3.h"
#include <exception>
#include <iomanip>
#include <fstream>

int main(int argc, char* argv[]) {
    if (argc <= 2) {
        std::cout << "USAGE: ./read_add_save save_file addend1.m3 etc." << std::endl;
        return -1;
    }

    Matrix3 matrix, temp;

    for (int i = 2; i < argc; i++) {
        std::string in_file{argv[i]};
        std::ifstream ist{in_file};
        if (!ist) throw std::runtime_error{"can’t open input file " + in_file};
        ist >> temp;
        matrix = matrix + temp;
    }

    std::string out_file{argv[1]};
    std::ofstream ofs {out_file};
    if (!ofs) throw std::runtime_error{"can’t open output file " + out_file};
    ofs << matrix;

    return 0;
}