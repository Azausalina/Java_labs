/**
* Class implement Cats
* Author: Anna Zausalina
* Date: 24.11.2021
*/

package animal;

public class Cat extends Animal {

    public Cat(String name, String breed, String sex,
        float height, float wieght, int age){

          super(name, breed, "Cat", sex, height, wieght,
                age, 200, 0);
        }
}
