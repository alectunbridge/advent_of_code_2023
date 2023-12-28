package advent_of_code_2023;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DayFour {

    public static final Pattern LINE_PATTERN = Pattern.compile("^.*: +([^|]*) \\| +(.*)$");
    private final List<String> input;

    public DayFour(List<String> input) {
        this.input = input;
    }

    public int part1() {
        int total = 0;
        for (String line : input) {
            Matcher lineMatcher = LINE_PATTERN.matcher(line);
            lineMatcher.matches();
            Set<Integer> winningNumbers = parseNumbers(lineMatcher.group(1));
            Set<Integer> cardNumbers = parseNumbers(lineMatcher.group(2));
            cardNumbers.retainAll(winningNumbers);
            total += Math.pow(2, cardNumbers.size() - 1);
        }
        return total;
    }

    private static Set<Integer> parseNumbers(String numbersString) {
        return Arrays.stream(numbersString
                        .split(" +"))
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }
}
