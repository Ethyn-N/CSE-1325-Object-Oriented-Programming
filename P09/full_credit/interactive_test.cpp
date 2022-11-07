// Interactive Matrix3 Tester
//
// This streams in two matrices and then streams out their sum

#include "matrix3.h"
#include <iomanip>

int main() {
    Matrix3 m1, m2, m3;
    std::cout << "Enter two 3x3 matrices:" << std::endl;
    std::cin >> m1 >> m2;
    m3 = m1 + m2;
    std::cout << std::setw(5) << m1 << "      +\n" << m2 << "      =\n" << m3 << std::endl;
}