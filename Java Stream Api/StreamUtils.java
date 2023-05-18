import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Fox {
    private int age;
    private String color;
    private String name;

    public Fox(int age, String color, String name) {
        this.age = age;
        this.color = color;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fox[" + age + ", " + color + ", " + name + "]";
    }
}

public class StreamUtils {
    public static void main(String[] args) {
        // Write a method to return a list of even numbers
        // from the list of integers below using the Stream API.
        List<Integer> numbers = Arrays.asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);
        List<Integer> expectedOutput = Arrays.asList(-2, -4, -8, 12, 6, 10, 14);
        numbers.stream().filter(x -> x % 2 == 0).forEach(System.out::println);

        // Write a method to return a list of squares of positive
        // numbers from the list of integers below using the Stream API.
        List<Integer> numbers2 = Arrays.asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);
        List<Integer> expectedOutput2 = Arrays.asList(1, 9, 144, 361, 36, 81, 100, 196);
        numbers2.stream()
                .filter(y -> y > 0)
                .map(y -> y * y)
                .toList().forEach(System.out::println);

        // Write a method to return a list of numbers whose square is greater than 20 from the list
        // of integers below using the Stream API. The value 20 should be one of the method's parameters.
        List<Integer> numbers3 = Arrays.asList(3, 9, 2, 8, 6, 5);
        List<Integer> expectedOutput3 = Arrays.asList(9, 8, 6, 5);
        int value = 20;
        System.out.println(squareGreaterThan20(numbers3, value));

        // Write a method to return the average of odd numbers in the list of integers below using
        // the Stream API. Implement this without calculating the sum first!
        List<Integer> numbers4 = Arrays.asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);
        double expectedOutput4 = 22.0/6.0;
        System.out.println(averageOddNumber(numbers4));

        // Write a method to return the sum of odd numbers
        // in the list of integers below using the
        // Stream API.
        List<Integer> numbers5 = Arrays.asList(5, 9, 1, 2, 3, 7, 5, 6, 7, 3, 7, 6, 8, 5, 4, 9, 6, 2);
        int expectedOutput5 = 61;
        System.out.println(returnSum(numbers5));

        // Write a method to return a list of uppercase
        // characters from a given string using the Stream API.
        String s = "Lorem Ipsum Dolor Sit Amet, Consectetur Adipiscing Elit. Morbi nec mattis odio.";
        List<Character> expectedOutput6 = Arrays.asList('L', 'I', 'D', 'S', 'A', 'C', 'A', 'E', 'M');
        System.out.println(returnUppercase(s));

        // Write a method to return a list of strings
        // which start with the specified character (method parameter)
        // using the Stream API.
        List<String> cities = Arrays.asList("ROME", "LONDON", "NAIROBI", "CALIFORNIA", "ZURICH", "NEW DELHI", "AMSTERDAM", "ABU DHABI", "PARIS");
        List<String> expectedOutput7;
        expectedOutput7 = Arrays.asList("ROME"); // for 'R'
        expectedOutput7 = Arrays.asList("NAIROBI", "NEW DELHI"); // for 'N'
        System.out.println(returnCities(cities, 'N'));

        // Write a method to return a string concatenated
        // from a given list of characters using the Stream API.
        List<Character> characters = Arrays.asList('L', 'o', 'r', 'e', 'm', ' ', 'i', 'p', 's', 'u', 'm');
        String expectedOutput8 = "Lorem ipsum";
        System.out.println(returnConcatenated(characters));

        // Write a method to return a map specifying the
        // frequency of characters in a given string using the Stream API
        String s2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.";
        // { =12, a=2, c=4, d=7, e=8, g=1, i=10, l=2, ,=2, L=1, m=5, n=4, .=1, o=7, p=3, r=4, s=6, t=7, u=4}
        System.out.println(returnFrequency(s2));

        // Create a Fox class with 3 properties: name, color and age.
        // Create a list of foxes and add at least 8 instances to it.
        // Then, using the Stream API
        //
        //write a method to return a list of foxes of green color
        //[Fox[4, GREEN, Skuld], Fox[9, GREEN, Hildr]]
        //write a method to return a list of foxes of green color younger than 5
        //[Fox[4, GREEN, Skuld]]
        //write a method to return a map specifying the frequency of foxes by color
        //{GREEN=2, WHITE=1, BLACK=1, GRAY=1, RED=3}
        List<Fox> foxes = Arrays.asList(
                new Fox(4, "GREEN", "Skuld"),
                new Fox(9, "GREEN", "Hildr"),
                new Fox(2, "WHITE", "Baldr"),
                new Fox(6, "BLACK", "Fenrir"),
                new Fox(5, "GRAY", "Loki"),
                new Fox(1, "RED", "Freyja"),
                new Fox(3, "RED", "Thor"),
                new Fox(8, "RED", "Odin")
        );

        List<Fox> greenFoxes = foxes.stream()
                .filter(f -> f.getColor().equals("GREEN"))
                .collect(Collectors.toList());
        System.out.println(greenFoxes);

        List<Fox> greenYoungFoxes = foxes.stream()
                .filter(f -> f.getColor().equals("GREEN") && f.getAge() < 5)
                .collect(Collectors.toList());
        System.out.println(greenYoungFoxes);

        Map<String, Long> foxesByColor = foxes.stream()
                .collect(Collectors.groupingBy(Fox::getColor, Collectors.counting()));
        System.out.println(foxesByColor);

        // Find a random Wikipedia article and copy and save the contents to a text file.
        //
        // Using the Stream API, write a method getWordFrequency which reads all text from
        // the saved file and returns a map of word frequencies.
        //
        // Next, using getWordFrequency and Stream API, write a method to return an
        // ordered list Entry<String, Long> of the most common words in descending order.
        // Keep in mind that the text contains punctuation characters which should be ignored.
        // The result should be something like this:
        String filePath = "src/wiki.txt";
        Map<String, Long> wordFrequency = getWordFrequency(filePath);
        System.out.println(wordFrequency);

        List<Map.Entry<String, Long>> mostCommonWords = getMostCommonWordsDescending(wordFrequency);
        System.out.println(mostCommonWords);

    }
    public static List<Integer> squareGreaterThan20(List<Integer> numbers, int value) {
        return numbers.stream()
                .filter(z -> z * z > value)
                .toList();
    }
    public static double averageOddNumber(List<Integer> numbers) {
        long count = numbers.stream()
                .filter(a -> a % 2 != 0)
                .count();
        double sum = numbers.stream()
                .filter(a -> a % 2 != 0)
                .mapToInt(Integer::intValue)
                .sum();
        return sum / count;
    }
    public static Integer returnSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(b -> b % 2 != 0)
                .mapToInt(Integer::intValue)
                .sum();
    }
    public static List<Character> returnUppercase(String letters) {
        return letters.chars()
                .filter(Character::isUpperCase)
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }
    public static List<String> returnCities(List<String> cities, Character character) {
        return cities.stream()
                .filter(d -> d.charAt(0) == character)
                .collect(Collectors.toList());
    }
    public static String returnConcatenated(List<Character> listOfCharacters) {
        return listOfCharacters.stream()
                .map(String::valueOf)
                .reduce("", String::concat);
    }
    public static HashMap<Character, Integer> returnFrequency(String string) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        string.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1));

        return frequencyMap;
    }
    public static Map<String, Long> getWordFrequency(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Map.Entry<String, Long>> getMostCommonWordsDescending(Map<String, Long> wordFrequency) {
        return wordFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }
}
