package advent_of_code_2023;

import com.google.common.base.MoreObjects;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DayFour {

    public static final Pattern LINE_PATTERN = Pattern.compile("^.*: +([^|]*) \\| +(.*)$");
    private final List<String> input;
    private List<Integer> matchingNumbers = new ArrayList<>();

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
            matchingNumbers.add(cardNumbers.size());
            total += Math.pow(2, cardNumbers.size() - 1);
        }
        return total;
    }

    public int part2() {
        List<Integer> valuesOfCards = new ArrayList<>(Collections.nCopies(matchingNumbers.size(), 0));
        for (int cardIndex = matchingNumbers.size() - 1; cardIndex >= 0; cardIndex--) {
            int valueOfCard = 1;
            for (int cardsToAdd = 1; cardsToAdd <= matchingNumbers.get(cardIndex); cardsToAdd++) {
                int index = cardIndex + cardsToAdd;
                if (index < valuesOfCards.size()) {
                    valueOfCard += valuesOfCards.get(index);
                }
            }
            valuesOfCards.set(cardIndex, valueOfCard);
        }
        return valuesOfCards.stream().mapToInt(Integer::valueOf).sum();
    }

    private static Set<Integer> parseNumbers(String numbersString) {
        return Arrays.stream(numbersString
                        .split(" +"))
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }
}
