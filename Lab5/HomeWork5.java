/**
* Java Home Work №5
* Author: Anna Zausalina
* Date: 17.11.2021
*/

import java.util.Arrays;

class HomeWork5 {

    public static void main(String[] args) {

        // 4. Создать массив из 5 сотрудников.
        Employee[] employeesArr = new Employee[5];

        employeesArr[0] = new Employee("Ivanov Ivan Ivanovich",
                                       "Junior Java programmer",
                                       "i.ivanov@mycorp.com",
                                       "8(999)123-45-67",
                                       50_000, 23);

        employeesArr[1] = new Employee("Sidorov Vladimir Vladimirovich",
                                       "Middle Java programmer",
                                       "v.sidorov@mycorp.com",
                                       "8(999)223-45-67",
                                       150_000, 30);

        employeesArr[2] = new Employee("Petrov Petr Aleksandrovich",
                                       "Senior Java programmer",
                                       "p.petrov@mycorp.com",
                                       "8(999)323-45-67",
                                       350_000, 40);

        employeesArr[3] = new Employee("Ovechkin Sergey Eugenevich",
                                       "Product Manger",
                                       "s.ovechkin@mycorp.com",
                                       "8(999)423-45-67",
                                       450_000, 45);

        employeesArr[4] = new Employee("Hitriy Dmitriy Albertovich",
                                       "CEO",
                                       "d.hitriy@mycorp.com",
                                       "8(999)523-45-67",
                                       500_000, 55);

        // 5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
        System.out.println("Employees over 40 years:");

        int count = 0;
        for (Employee employee : employeesArr) {
            if (employee.getAge() > 40) {
                if (count > 0) {
                    System.out.println("#####################################");
                }
                System.out.println(employee.toString());
                count++;
            }
        }
  }

/*
1. Создать класс "Сотрудник" с полями: ФИО,
должность, email, телефон, зарплата, возраст.
*/
}

class Employee {

    private String fullName;
    private String position;
    private String email;
    private String telephone;
    private int salary;
    private int age;

    /*
    2. Конструктор класса должен заполнять эти поля при создании объекта.
    */
    Employee(String fullName, String position, String email,
             String telephone, int salary, int age) {

        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.telephone = telephone;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*
    3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
    */
    public String toString() {
        String resultString = "Info about employee:\n";

        resultString += "\t1.Name: " + this.fullName + "\n";
        resultString += "\t2.Position: " + this.position + "\n";
        resultString += "\t3.Email: " + this.email + "\n";
        resultString += "\t4.Telephone: " + this.telephone + "\n";
        resultString += "\t5.Salary: " + this.salary + "\n";
        resultString += "\t6.Age: " + this.age;

        return resultString;
    }

}
