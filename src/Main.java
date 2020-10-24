import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");

        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),

                    families.get(new Random().nextInt(families.size())),

                    new Random().nextInt(100),

                    Sex.values()[new Random().nextInt(Sex.values().length)],

                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countNotAdultPerson = persons
                .stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List countAdultPerson = persons
                .stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 27)
                .map(person -> person.getName())
                .collect(Collectors.toList());

        Collection countAdultEducationWomen = persons
                .stream()
                .filter(person -> person.getSex() == Sex.WOMEN)
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 60)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        Collection countAdultEducationMan = persons
                .stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(countAdultEducationMan);


    }

}
