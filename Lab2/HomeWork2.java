/*
Java Home Work №2

Author: Anna Zausalina

Date: 11.11.2021
*/

class HomeWork2 {

/*
1. Написать метод, принимающий на вход два целых числа и проверяющий, что
их сумма лежит в пределах от 10 до 20 (включительно),
если да – вернуть true, в противном случае – false.
*/

    static boolean isSumBtwn10And20(int a, int b) {
        return (a + b >= 10) & (a + b <= 20);
    }
/*
2. Написать метод, которому в качестве параметра передается целое число,
метод должен напечатать в консоль, положительное ли число передали или отрицательное.
Замечание: ноль считаем положительным числом.
*/
    static String checkSign(int x) {
        return (x >= 0) ? "Positive" : "Negative";
    }

    static void printCheckSign(int x) {
        System.out.println(checkSign(x));
    }

/*
3. Написать метод, которому в качестве параметра передается целое число.
Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
*/

    static boolean isNegativeOrPositive(int x) {
        return x < 0;
    }

/*
4. Написать метод, которому в качестве аргументов передается строка и число,
метод должен отпечатать в консоль указанную строку, указанное количество раз;
*/
    static void printMultiString(String str, int count) {
        for(int i = 0; i < count; i++){
            System.out.println(str);
        }
    }

/*
5. * Написать метод, который определяет, является ли год високосным,
и возвращает boolean (високосный - true, не високосный - false).
Каждый 4-й год является високосным, кроме каждого 100-го,
при этом каждый 400-й – високосный.
*/

    static boolean isBisSextus(int year){
        if (((year % 100 != 0) | (year == 400)) & (year % 4 == 0)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        // 1st method
        boolean result = isSumBtwn10And20(10,10);
        System.out.println(result);

        // 2nd method
        printCheckSign(10);

        // 3rd method
        result = isNegativeOrPositive(-10);
        System.out.println(result);

        // 4th method
        printMultiString("Give me 5", 5);

        // 5th* method
        result = isBisSextus(2012);
        System.out.println(result);
    }

}
