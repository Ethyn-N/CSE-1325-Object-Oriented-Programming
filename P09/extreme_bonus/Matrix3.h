#ifndef __MATRIX3_H
#define __MATRIX3_H

#include <iostream>

class Matrix3 {
  public:
    Matrix3();
    Matrix3(int m00, int m01, int m02, int m10, int m11, int m12, int m20, int m21, int m22);
    Matrix3 operator+(Matrix3 rhs);
    Matrix3 operator*(int rhs);
    int* operator[](int);
    friend Matrix3 operator*(int lhs, Matrix3 rhs);
    friend std::ostream& operator<<(std::ostream& out, Matrix3& m);
    friend std::istream& operator>>(std::istream& in, Matrix3& m);

  private:
    int data[3][3];
};

#endif