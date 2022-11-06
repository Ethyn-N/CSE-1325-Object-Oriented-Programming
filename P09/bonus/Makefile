CXXFLAGS = --std=c++17

all: matrix_generator simple_matrix3_test test_matrix3 test_matrix3 interactive_matrix3 read_add_save

# Linkers
test_matrix3: test_matrix3.o Matrix3.o *.h
	$(CXX) $(CXXFLAGS) test_matrix3.o Matrix3.o -o test_matrix3

interactive_test: interactive_test.o Matrix3.o *.h
	$(CXX) $(CXXFLAGS) interactive_test.o Matrix3.o -o interactive_test

read_add_save: read_add_save.o Matrix3.o *.h
	$(CXX) $(CXXFLAGS) read_add_save.o Matrix3.o -o read_add_save

# Class compilation
test_matrix3.o: test_matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c test_matrix3.cpp -o test_matrix3.o

interactive_test.o: interactive_test.cpp *.h
	$(CXX) $(CXXFLAGS) -c interactive_test.cpp -o interactive_test.o

read_add_save.o: read_add_save.cpp *.h
	$(CXX) $(CXXFLAGS) -c read_add_save.cpp -o read_add_save.o

Matrix3.o: Matrix3.cpp
	$(CXX) $(CXXFLAGS) -c Matrix3.cpp -o Matrix3.o

clean:
	-rm -f *.o *~ a.out matrix3 test_matrix3 interactive_test read_add_save