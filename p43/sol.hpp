#include "cppcommon/headers.hpp"
using namespace std;

class Solution {
public:


    void _convert_to_int_reversed(string s, int * nums, int size) {
        for (int i = size - 1; i >=0; i--) {
            nums[size - i - 1] = s[i] - 48;
        }
    }

    void _convert_to_str_reversed(int * nums, int size, string *s) {
        for (int i = s -> size() - 1; i >= 0; i--) {
            (*s)[size - i - 1] = nums[i] + 48;
        }
    }

    void _do_multiply(int* base, int size, int multiplier, int zero_paddings, int size_res, int * res) {
        int carry = 0;
        res[size_res - 1] = 0;
        for (int i=0; i<zero_paddings; i++) {
            res[i] = 0;
        }
        for (int i=zero_paddings; i<size_res; i++) {
            if (i < size + zero_paddings) {
                res[i] = multiplier * base[i-zero_paddings] + carry;
            } else {
                res[i] += carry;
            }
            carry = res[i] / 10;
            res[i] = res[i] - 10 * carry;
        }
    }

    void _merge(int* nums_1, int* nums_2, int size) {
        bool carry = 0;
        for (int i=0; i<size; i++ ) {
            nums_1[i]  = nums_1[i] + nums_2[i] + carry;
            carry = nums_1[i] >= 10;
            nums_1[i] -= carry * 10;
        }
    }

    string multiply(string num1, string num2) {
        if (num1 == "0" || num2 == "0") {
            return "0";
        }
        if (num1 == "1") {
            return num2;
        }   
        if (num2 == "1") {
            return num1;
        }
        int s1 = num1.size(); int s2 = num2.size(); int size_res = s1 + s2;
        int size = max(s1, s2); int size_min = min(s1, s2);
        int data[( size_res + size_res + size ) + (s1 + s2)];


        int* res = &data[0]; // size_res
        int* tmp = &data[size_res]; // size_res
        int* r_1 = &data[size_res * 2]; // size
        int* r_2 = &data[size_res * 2 + size ];

        if (s1 > s2) {
            _convert_to_int_reversed(num1, r_1, size);
            _convert_to_int_reversed(num2, r_2, size_min);
        } else {
            _convert_to_int_reversed(num1, r_2, size_min);
            _convert_to_int_reversed(num2, r_1, size);
        }
        

        memset(data, 0, size_res * 2 * sizeof(int));

        for (int i=0; i< size_min; i++) {
            _do_multiply(r_1, size, r_2[i], i, size_res, tmp);
            _merge(res, tmp, size_res);
        }
        string res_str(size_res, ' ');
        _convert_to_str_reversed(res, size_res, &res_str);

        int start = 0;
        for (int i=0; i<size_res; i++) {
            if (res_str[i] == 48) {
                start ++;
            } else {
                break;
            }
        }
        return &res_str[start];
    }
};