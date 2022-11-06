#include "Matrix3.h"
#include <exception>
#include <iomanip>

Matrix3::Matrix3()
    : data
    {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
    }
    {}

Matrix3::Matrix3(int m00, int m01, int m02, int m10, int m11, int m12, int m20, int m21, int m22)
    : data
    {
        {m00, m01, m02},
        {m10, m11, m12},
        {m20, m21, m22}
    }
    {}
    
int arr[3];
int* Matrix3::operator[](int index) {
    if (index > 2 || index < 0) {
        std::cout << "Array index out of bound" << std::endl;
        exit(-1);
    }
    for (int j = 0; j < 3; j++)
        arr[j] = this->data[index][j];

    return arr;
}

Matrix3 Matrix3::operator+(Matrix3 rhs) {
    int sum[3][3];

    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            sum[i][j] = this->data[i][j] + rhs.data[i][j];
    
    return Matrix3(sum[0][0], sum[0][1], sum[0][2], sum[1][0], sum[1][1], sum[1][2], sum[2][0], sum[2][1], sum[2][2]);
}

Matrix3 Matrix3::operator*(int rhs) {
    int sum[3][3];

    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            sum[i][j] = this->data[i][j] * rhs;
    
    return Matrix3(sum[0][0], sum[0][1], sum[0][2], sum[1][0], sum[1][1], sum[1][2], sum[2][0], sum[2][1], sum[2][2]);
}

Matrix3 operator*(int lhs, Matrix3 rhs) {
    return rhs*lhs;
}

std::ostream& operator<<(std::ostream& out, Matrix3& m) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
                out << std::setw(4) << m.data[i][j] << " ";
        }
        out << "\n";
    }
    return out;
}

std::istream& operator>>(std::istream& in, Matrix3& m) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            in >> m.data[i][j];
        }
    }
    return in;
}
    
   