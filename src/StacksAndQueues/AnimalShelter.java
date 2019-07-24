package StacksAndQueues;

import java.util.LinkedList;

/**
 * An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first
 * out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
 * that type). They cannot select which specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
 * and dequeueCat. You may use the built-in Linked List data structure.
 */

/**
 * Logic based. Practice for generalization.
 * Result:
 * 1dog1
 * 3cat1
 * 2dog2
 * 4cat2
 * 5dog3
 */
public class AnimalShelter {

  private int order;

  public synchronized int getOrder() {
    return order++;
  }

  public static abstract class Animal {
    private int order;
    protected String name;
    protected long timeEntered;
  }

  public static class Dog extends Animal {
    public Dog(String name) {
      super();
      this.name = name;
      this.timeEntered = System.nanoTime();
    }
  }

  public static class Cat extends Animal {
    public Cat(String name) {
      super();
      this.name = name;
      this.timeEntered = System.nanoTime();
    }
  }

  private LinkedList<Animal> dogs = new LinkedList<Animal>();
  private LinkedList<Animal> cats = new LinkedList<Animal>();

  private void include(Animal animal) {
    animal.order = getOrder();
    if (animal instanceof Dog) {
      dogs.add(animal);
    } else {
      cats.add(animal);
    }
  }

  private Animal getAny() {
    Animal animal;
    if (dogs.size() == 0 && cats.size() == 0) {
      throw new IllegalStateException();
    } else if (dogs.size() == 0) {
      animal = cats.poll();
    } else if (cats.size() == 0) {
      animal = dogs.poll();
    } else {
      Animal oldestDog = dogs.peek();
      Animal oldestCat = cats.peek();
      animal = oldestDog.timeEntered < oldestCat.timeEntered ? dogs.poll() : cats.poll();
    }
    return animal;
  }

  private Animal get(Class reqAnimal) {
    Animal animal;
    if (reqAnimal == Dog.class) {
      animal = dogs.poll();
    } else {
      animal = cats.poll();
    }
    return animal;
  }

  public static void main(String[] args) {

    AnimalShelter animalShelter = new AnimalShelter();
    animalShelter.include(new Dog("1dog1"));
    animalShelter.include(new Dog("2dog2"));
    animalShelter.include(new Cat("3cat1"));
    animalShelter.include(new Cat("4cat2"));
    animalShelter.include(new Dog("5dog3"));
    animalShelter.include(new Cat("6cat3"));

    Animal animal = animalShelter.getAny();
    System.out.println(animal.name);

    animal = animalShelter.get(Cat.class);
    System.out.println(animal.name);

    animal = animalShelter.get(Dog.class);
    System.out.println(animal.name);

    animal = animalShelter.getAny();
    System.out.println(animal.name);

    animal = animalShelter.getAny();
    System.out.println(animal.name);
  }

}
