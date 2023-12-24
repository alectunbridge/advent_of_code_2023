package advent_of_code_2023;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTwo {
    public static final Pattern RED_PATTERN = Pattern.compile("(\\d+) red");
    public static final Pattern GREEN_PATTERN = Pattern.compile("(\\d+) green");
    public static final Pattern BLUE_PATTERN = Pattern.compile("(\\d+) blue");
    private final List<String> input;

    public DayTwo(List<String> input) {
        this.input = input;
    }

    public int part1(int maxRed, int maxGreen, int maxBlue) {
        int sumOfValidGameNumbers = 0;
        GAME:
        for (int gameNumber = 1; gameNumber <= input.size(); gameNumber++) {
            String line = input.get(gameNumber - 1);
            Matcher redMatcher = RED_PATTERN.matcher(line);
            Matcher greenMatcher = GREEN_PATTERN.matcher(line);
            Matcher blueMatcher = BLUE_PATTERN.matcher(line);
            sumOfValidGameNumbers += gameNumber;
            
            while (redMatcher.find()) {
                if (Integer.parseInt(redMatcher.group(1)) > maxRed) {
                    sumOfValidGameNumbers -= gameNumber;
                    continue GAME;
                }
            }
            while (greenMatcher.find()) {
                if (Integer.parseInt(greenMatcher.group(1)) > maxGreen) {
                    sumOfValidGameNumbers -= gameNumber;
                    continue GAME;
                }
            }
            while (blueMatcher.find()) {
                if (Integer.parseInt(blueMatcher.group(1)) > maxBlue) {
                    sumOfValidGameNumbers -= gameNumber;
                    continue GAME;
                }
            }
        }
        return sumOfValidGameNumbers;
    }

    public long part2() {
        int sumOfGameCubes = 0;
        for (int gameNumber = 1; gameNumber <= input.size(); gameNumber++) {
            String line = input.get(gameNumber - 1);
            Matcher redMatcher = RED_PATTERN.matcher(line);
            Matcher greenMatcher = GREEN_PATTERN.matcher(line);
            Matcher blueMatcher = BLUE_PATTERN.matcher(line);
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue= 0;

            while (redMatcher.find()) {
                int noOfReds = Integer.parseInt(redMatcher.group(1));
                if (noOfReds > maxRed) {
                    maxRed = noOfReds;
                }
            }
            while (greenMatcher.find()) {
                int noOfGreens = Integer.parseInt(greenMatcher.group(1));
                if (noOfGreens > maxGreen) {
                    maxGreen = noOfGreens;
                }
            }
            while (blueMatcher.find()) {
                int noOfBlue = Integer.parseInt(blueMatcher.group(1));
                if (noOfBlue > maxBlue) {
                    maxBlue = noOfBlue;
                }
            }
            sumOfGameCubes += maxRed * maxGreen * maxBlue;
        }
        return sumOfGameCubes;
    }
}
