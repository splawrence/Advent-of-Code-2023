import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayOneTrebuchet {
    public static void main(final String[] args) throws IOException {
        // Specify the path to your file
        final String filePath = "input.txt";

        // Read all lines from the file into a List of Strings
        final List<String> lines = Files.readAllLines(Paths.get(filePath));

        int sum = 0;
        // Create a map for word replacements
        final Map<String, String> wordToNumberMap = new HashMap<>();
        wordToNumberMap.put("one", "1");
        wordToNumberMap.put("two", "2");
        wordToNumberMap.put("three", "3");
        wordToNumberMap.put("four", "4");
        wordToNumberMap.put("five", "5");
        wordToNumberMap.put("six", "6");
        wordToNumberMap.put("seven", "7");
        wordToNumberMap.put("eight", "8");
        wordToNumberMap.put("nine", "9");

        for (final String line : lines) {
            String sanitizedLine = replaceWordsWithNumbers(line, wordToNumberMap);
            // removing non-number strings
            sanitizedLine = sanitizedLine.replaceAll("[^\\d]", "");
            // first and last number
            final String firstAndLast = sanitizedLine.substring(0, 1) + sanitizedLine.substring(sanitizedLine.length() - 1);
            final int value = Integer.parseInt(firstAndLast);
            sum += value;
        }
        System.out.println(sum);
    }

    private static String replaceWordsWithNumbers(String input, final Map<String, String> replacements) {
        final Map<Integer, String> indexTextNumbersMap = new HashMap<>();
        // locating text numbers and get their indexes
        for (final Map.Entry<String, String> entry : replacements.entrySet()) {
            // indexes of all occurrences of all numbers in a input
            for (int index = input.indexOf(entry.getKey()); index >= 0; index = input.indexOf(entry.getKey(),
                    index + 1)) {
                indexTextNumbersMap.put(index, entry.getKey());
            }
        }
        
        // sorting by index to replace the numbers in order
        final List<Integer> textNumberIndexes = new ArrayList<>(indexTextNumbersMap.keySet());
        Collections.sort(textNumberIndexes);
        for (final Integer integer : textNumberIndexes) {
            input = input.substring(0, integer) + replacements.get(indexTextNumbersMap.get(integer))
                    + input.substring(integer + 1);
        }
        return input;
    }
}
