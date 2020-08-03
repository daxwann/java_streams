package main.java;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach: Filter
    List<Person> femalesList1 = new ArrayList<>();
    for (Person person : people) {
      if (person.getGender().equals(Gender.FEMALE)) {
        femalesList1.add(person);
      }
    }

    System.out.println("---- I. Imperative Approach: Filter ----\n");
    femalesList1.forEach(System.out::println);
    System.out.println("\n");

    // Declarative approach
    System.out.println("---- II. Declarative Approach ----\n");

    // Stream Filter
    List<Person> femalesList2 = people.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .collect(Collectors.toList());

    System.out.println("------ 1. Filter (female) ----\n");
    femalesList2.forEach(System.out::println);

    // Stream Sort
    List<Person> sortedByAgeAndGender = people.stream()
        .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
        .collect(Collectors.toList());

    System.out.println("\n------ 2. Sort (by age and gender)----\n");
    sortedByAgeAndGender.forEach(System.out::println);

    // Stream All match
    boolean allMatchAgeOlderThanEight = people.stream()
        .allMatch(person -> person.getAge() > 8);
    System.out.println("\n------ 3. All match (age greater than 8) ----\n");
    System.out.println(allMatchAgeOlderThanEight);

    // Stream Any match
    boolean anyMatchAgeOlderThanEight = people.stream()
        .anyMatch(person -> person.getAge() > 8);
    System.out.println("\n------ 4. Any match (age greater than 8) ----\n");
    System.out.println(anyMatchAgeOlderThanEight);

    // Stream non match
    boolean nonMatchNameIsAntonio = people.stream()
        .noneMatch(person -> person.getName().equals("Antonio"));
    System.out.println("\n------ 5. Any match (name is Antonio) ----\n");
    System.out.println(nonMatchNameIsAntonio);

    // Stream max
    System.out.println("\n------ 6. Max (age) ----\n");

    people.stream()
        .max(Comparator.comparing(Person::getAge))
        .ifPresent(System.out::println);

    // Stream min
    System.out.println("\n------ 7. Min (age) ----\n");

    people.stream()
        .min(Comparator.comparing(Person::getAge))
        .ifPresent(System.out::println);

    // Group
    System.out.println("\n------ 8. Collect Grouping by (age) ----\n");
    Map<Gender, List<Person>> groupByGender = people.stream()
        .collect(Collectors.groupingBy(Person::getGender));

    groupByGender.forEach((gender, genderList) -> {
      System.out.println(gender);
      genderList.forEach(System.out::println);
    });

    // Chaining
    System.out.println("\n------ 9. Print name of oldest female ----\n");

    people.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .max(Comparator.comparing(Person::getAge))
        .map(Person::getName)
        .ifPresent(System.out::println);
  }

  private final static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }
}
