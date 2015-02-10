package projectx.problem;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mihails Volkovs on 2015.02.10.
 */
public class Problem42 {

    private static List<String> words = Lists.newArrayList();

    private static Set<Integer> triangleNumbers = Sets.newHashSet();

    public static void main(String... args) {

        // reading input
        Scanner scanner = new Scanner(Problem42.class.getClassLoader().getResourceAsStream("p042_words.txt"));
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            words.add(scanner.next().replaceAll("\"", "").toLowerCase());
        }

        // getting the longest word and trianle upper limit
        int longestWordSize = words.stream().mapToInt(word -> word.length()).max().getAsInt();
        int upperLimit = toInt('z') * longestWordSize;

        // generating triangles in defined limit
        int n = 1;
        int lastTriangle = 1;
        System.out.println(upperLimit);
        System.out.println(longestWordSize);
        while (lastTriangle <= upperLimit) {
            triangleNumbers.add(lastTriangle);
            lastTriangle = triangle(n++);
        }

        // counting triangle words
        int triangleWordsCount = 0;
        for (String word : words) {
            int wordFeature = 0;
            for (char character : word.toCharArray()) {
                wordFeature += toInt(character);
            }
            if (triangleNumbers.contains(wordFeature)) {
                triangleWordsCount++;
            }
        }
        System.out.println(triangleWordsCount);
    }

    private static int triangle(int n) {
        return n * (n + 1) / 2;
    }

    private static int toInt(char character) {
        return Character.getNumericValue(character) - Character.getNumericValue('a') + 1;
    }

    @Test
    public void triangle() {
        assertEquals(1, triangle(1));
        assertEquals(3, triangle(2));
        assertEquals(6, triangle(3));
        assertEquals(10, triangle(4));
        assertEquals(15, triangle(5));
        assertEquals(21, triangle(6));
        assertEquals(28, triangle(7));
    }

    @Test
    public void toInt() {
        assertEquals(1, toInt('a'));
        assertEquals(2, toInt('b'));
        assertEquals(3, toInt('c'));
        assertEquals(25, toInt('y'));
        assertEquals(26, toInt('z'));
    }

}
