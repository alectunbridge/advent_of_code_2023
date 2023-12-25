package advent_of_code_2023;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {
    private final List<String> input;
    private Set<PartNumber>[] numbers;
    private Set<Symbol> symbols = new HashSet<>();
    private Set<PartNumber> validNumbers;

    public DayThree(List<String> input) {
        this.input = input;
        numbers = new HashSet[input.size()];
        validNumbers = new HashSet<>();
    }

    public long part1() {
        for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {
            numbers[lineIndex] = new HashSet<>();
            String line = input.get(lineIndex);
            parseLineIntoNumbersAndSymbols(lineIndex, line);
        }

        for (Symbol symbol : symbols) {
            findNumbersNextToCoordinate(symbol.lineIndex, symbol.start);
        }
        return validNumbers.stream().mapToInt(PartNumber::number).sum();
    }

    private void findNumbersNextToCoordinate(int lineIndex, int start) {
        for (int lineDelta = -1; lineDelta < 2; lineDelta++) {
            for (PartNumber partNumber : numbers[lineIndex + lineDelta]) {
                if (Math.abs(partNumber.start - start) <= 1 || Math.abs(partNumber.end - start) <= 1) {
                    validNumbers.add(partNumber);
                }
            }
        }
    }

    private void parseLineIntoNumbersAndSymbols(int lineIndex, String line) {
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher numberMatcher = numberPattern.matcher(line);
        while (numberMatcher.find()) {
            PartNumber partNumber = new PartNumber(Integer.parseInt(numberMatcher.group(0)), lineIndex, numberMatcher.start(), numberMatcher.end() - 1);
            numbers[lineIndex].add(partNumber);
        }

        Pattern symbolPattern = Pattern.compile("[^\\d.]");
        Matcher symbolMatcher = symbolPattern.matcher(line);
        while (symbolMatcher.find()) {
            Symbol symbol = new Symbol(symbolMatcher.group(0), lineIndex, symbolMatcher.start());
            symbols.add(symbol);
        }


    }

    public long part2() {
        for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {
            numbers[lineIndex] = new HashSet<>();
            String line = input.get(lineIndex);
            parseLineIntoNumbersAndSymbols(lineIndex, line);
        }
        long total = 0;
        for (Symbol symbol : symbols) {
            if (symbol.isGear()) {
                total += findGear(symbol.lineIndex, symbol.start);
            }
        }


        return total;
    }

    private int findGear(int lineIndex, int start) {
        Set<PartNumber> gears = new HashSet<>();
        for (int lineDelta = -1; lineDelta < 2; lineDelta++) {
            for (PartNumber partNumber : numbers[lineIndex + lineDelta]) {
                if (Math.abs(partNumber.start - start) <= 1 || Math.abs(partNumber.end - start) <= 1) {
                    gears.add(partNumber);
                }
            }
        }
        if (gears.size() == 2) {
            return gears.stream().mapToInt(PartNumber::number).reduce(1, (a, b) -> a * b);
        }
        return 0;
    }

    private record PartNumber(int number, int lineIndex, int start, int end) {
    }

    private record Symbol(String character, int lineIndex, int start) {
        public boolean isGear() {
            return "*".equals(character);
        }
    }
}
