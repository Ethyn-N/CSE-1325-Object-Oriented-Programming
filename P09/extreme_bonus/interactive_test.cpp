// Interactive Matrix3 Tester
//
// This streams in two matrices and then streams out their sum

#include "matrix3.h"
#include <iomanip>

int main() {
    Matrix3 m;
    int x, y;

    std::cout << "Enter a 3x3 matrices:" << std::endl;
    std::cin >> m;

    std::cout << "Enter 2 subsricpt values ie. m[i][j]: ";
    std::cin >> x >> y;

    std::cout << std::setw(5) << m << std::endl;

    std::cout << std::setw(5) << "m[" << x << "][" << y << "] = " << m[x][y] << std::endl;

    return 0;
}