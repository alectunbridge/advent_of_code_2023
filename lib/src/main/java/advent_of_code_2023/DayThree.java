package advent_of_code_2023;

import com.google.common.base.MoreObjects;

import javax.swing.plaf.synth.SynthButtonUI;
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
            findNumbersNextToCoordinate(symbol.lineIndex,symbol.start);
        }
        return validNumbers.stream().mapToInt(PartNumber::number).sum();
    }

    private void findNumbersNextToCoordinate(int lineIndex, int start) {
        for( int lineDelta = -1; lineDelta < 2; lineDelta++ ) {
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
        while(numberMatcher.find()){
            PartNumber partNumber = new PartNumber(Integer.parseInt(numberMatcher.group(0)), lineIndex, numberMatcher.start(), numberMatcher.end()-1);
            numbers[lineIndex].add(partNumber);
        }
        
        Pattern symbolPattern = Pattern.compile("[^\\d.]");
        Matcher symbolMatcher = symbolPattern.matcher(line);
        while(symbolMatcher.find()){
            Symbol symbol = new Symbol(lineIndex, symbolMatcher.start());
            symbols.add(symbol);
        }
        
        
    }

    private record PartNumber(int number, int lineIndex, int start, int end) {}

    private record Symbol(int lineIndex, int start){}
}
