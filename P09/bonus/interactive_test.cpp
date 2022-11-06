// Interactive Matrix3 Tester
//
// This streams in two matrices and then streams out their sum

#include "matrix3.h"
#include <iomanip>

int main() {
    Matrix3 m1, m2, m3;
    int multiplier;

    std::cout << "Enter a 3x3 matrices:" << std::endl;
    std::cin >> m1;

    std::cout << "Enter scalar multiplier: ";
    std::cin >> multiplier;

    m2 = m1 * multiplier;
    std::cout << std::setw(5) << m1 << "       *" << multiplier << "=\n" << m2 << std::endl;

    m3 = multiplier * m1;
    std::cout << std::setw(5) << "       " << multiplier << "*\n" << m1 << "        =\n" << m3 << std::endl;

    return 0;
}