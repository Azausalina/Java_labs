/**
* Java Home Work №3
* Author: Anna Zausalina
* Date: 14.11.2021
*/

import java.util.Arrays;

class HomeWork3 {

/* 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
С помощью цикла и условия заменить 0 на 1, 1 на 0;
*/

    static void reverse0And1(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 1) ? 0 : 1;
        }
    }

/*
2. Задать пустой целочисленный массив длиной 100.
 С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
*/

    static int[] createArray0to100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }
        return arr;
    }

/*
3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
пройти по нему циклом, и числа меньшие 6 умножить на 2;
*/
    static void multiplyLess6(int arr[]){
        for (int i = 0; i < arr.length; i++){
            arr[i] = (arr[i] < 6) ? arr[i]*2 : arr[i];
        }
    }

/*
4. Создать квадратный двумерный целочисленный массив
(количество строк и столбцов одинаковое), и с помощью цикла(-ов)
заполнить его диагональные элементы
единицами (можно только одну из диагоналей, если обе сложно).
Определить элементы одной из диагоналей можно по
следующему принципу: индексы таких элементов равны,
то есть [0][0], [1][1], [2][2], …, [n][n];
*/

    static void fillDiagonal(int arr[][]){
        for (int i = 0; i < arr.length; i++){
            // main diagonal
            arr[i][i] = 1;
            // side diagonal
            arr[(arr.length - 1) - i][i] = 1;
      }
    }

/*
5. Написать метод, принимающий на вход два аргумента: len и initialValue,
и возвращающий одномерный массив типа int длиной len, каждая ячейка
которого равна initialValue;
*/

    static int[] createArrayWithValue(int len, int initialValue){
        int[] arr = new int[len];
        // fill without loop
        Arrays.fill(arr,initialValue);
        return arr;
    }

/*
6. * Задать одномерный массив и найти
в нем минимальный и максимальный элементы;
*/

    static int[] findMinMax(int[] arr){
        int[] minMax = {arr[0], arr[0]};
        for (int i = 1; i < arr.length; i++) {
            // find min
            if (minMax[0] > arr[i]) {
                minMax[0] = arr[i];
            }
            //find max
            if (minMax[1] < arr[i]) {
                minMax[1] = arr[i];
            }
        }
        return minMax;
    }

/*
7. ** Написать метод, в который передается не пустой одномерный
целочисленный массив, метод должен вернуть true, если в массиве есть место,
в котором сумма левой и правой части массива равны.
*/

    static boolean sumPartOfArr(int[] arr, int splitIndex){
        int sum1 = 0;
        int sum2 = 0;
        // 1st part
        for (int i = 0; i < splitIndex; i++){
            sum1 += arr[i];
        }
        // 2nd part
        for (int i = splitIndex; i < arr.length; i++){
            sum2 += arr[i];
        }
        return sum1 == sum2;
    }

    static boolean checkBalance(int[] arr){
        for (int i = 1; i < arr.length; i++){
            if (sumPartOfArr(arr,i)){
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {

        // 1st method
        int[] arr1 = {1, 0, 0, 1, 1, 1, 0, 1};
        reverse0And1(arr1);
        System.out.println(Arrays.toString(arr1));

        // 2nd method
        System.out.println(Arrays.toString(createArray0to100()));

        // 3rd method
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyLess6(arr3);
        System.out.println(Arrays.toString(arr3));

        // 4th method
        int size = 5;
        int[][] arr4 = new int[size][size];
        fillDiagonal(arr4);
        for (int i = 0; i < arr4.length; i++) {
            System.out.println(Arrays.toString(arr4[i]));
        }

        // 5th method
        System.out.println(Arrays.toString(createArrayWithValue(5,2)));

        // 6th* method
        int[] arr6 = {5, 3, 7, 3, 78, 5, 22, 2, 1, 5, 7, 9};
        System.out.println(Arrays.toString(findMinMax(arr6)));

        // 7th** method
        int[] arr7 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkBalance(arr7));
    }
}
