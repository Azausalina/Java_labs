/*
Домашнее задание  №1
*/

class HomeWork1 {

/*
1. Создайте метод printThreeWords(),
который при вызове должен отпечатать в столбец три слова:
Orange
Banana
Apple.
*/

    static void printThreeWords() {
        String fruits = "Orange"
		    + System.lineSeparator() + "Banana"
		    + System.lineSeparator() + "Apple";
    System.out.println(fruits);
    }

/*
2. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b,
и инициализируйте их любыми значениями, которыми захотите.
Далее метод должен просуммировать эти переменные, и если их сумма больше или равна 0,
то вывести в консоль сообщение “Сумма положительная”,
в противном случае - “Сумма отрицательная”;
*/
    static void	checkSumSign() {
        int a = 8;
        int b = 16;
    System.out.println(((a + b) >= 0) ? "Сумма положительная" : "Сумма отрицательная");
    }

/*
3. Создайте метод printColor() в теле которого задайте int переменную value и инициализируйте ее любым значением.
Если value меньше 0 (0 включительно), то в консоль метод должен вывести сообщение “Красный”,
если лежит в пределах от 0 (0 исключительно) до 100 (100 включительно), то “Желтый”,
если больше 100 (100 исключительно) - “Зеленый”;
*/
    static void printColor() {
        int value = 15;
        if (value <= 0) {
            System.out.println("Красный");
        }
        else if (value <= 100) {
            System.out.println("Желтый");
        }
        else if (value > 100) {
            System.out.println("Зеленый");
        }
    }
  /*
  4. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
  и инициализируйте их любыми значениями, которыми захотите. Если a больше или равно b,
  то необходимо вывести в консоль сообщение “a >= b”, в противном случае “a < b”;
  */
    static void compareNumbers() {
        int a = 82;
        int b = 14;
    System.out.println((a >= b) ? "a >= b" : "a < b");
    }

    public static void main(String[] args) {
      printThreeWords();
      checkSumSign();
      printColor();
      compareNumbers();
     }
}
