package test;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 *
 */
import java.util.*;

public class Person implements Comparable<Person>
{
  String name;
  int age;

  public Person(String name, int age)
  {
    this.name = name;
    this.age = age;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  public String toString()
  {
    return name + " : " + age;
  }

  /*
   **  Implement the natural order for this class
   */
  public int compareTo(Person p)
  {
    return getName().compareTo(p.getName());
  }

  static class AgeComparator implements Comparator<Person>
  {
    public int compare(Person p1, Person p2)
    {
      int age1 = p1.getAge();
      int age2 = p2.getAge();

      if (age1 == age2)
        return 0;
      else if (age1 > age2)
        return 1;
      else
        return -1;
    }
  }

  public static void main(String[] args)
  {
    List<Person> people = new ArrayList<Person>();
    people.add( new Person("Homer", 38) );
    people.add( new Person("Marge", 35) );
    people.add( new Person("Bart", 15) );
    people.add( new Person("Lisa", 13) );

    // Sort by natural order

    Collections.sort(people);
    System.out.println("Sort by Natural order");
    System.out.println("\t" + people);

    // Sort by reverse natural order

    Collections.sort(people, Collections.reverseOrder());
    System.out.println("Sort by reverse natural order");
    System.out.println("\t" + people);

    //  Use a Comparator to sort by age

    Collections.sort(people, new Person.AgeComparator());
    System.out.println("Sort using Age Comparator");
    System.out.println("\t" + people);

    //  Use a Comparator to sort by descending age

    Collections.sort(people,
        Collections.reverseOrder(new Person.AgeComparator()));
    System.out.println("Sort using Reverse Age Comparator");
    System.out.println("\t" + people);
  }
}
