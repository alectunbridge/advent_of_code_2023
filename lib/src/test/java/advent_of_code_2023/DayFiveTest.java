package advent_of_code_2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFiveTest {

    @Test
    void part1Example() {
        DayFive dayFive = new DayFive(Arrays.asList(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                "",
                "soil-to-fertilizer map:",
                "0 15 37",
                "37 52 2",
                "39 0 15",
                "",
                "fertilizer-to-water map:",
                "49 53 8",
                "0 11 42",
                "42 0 7",
                "57 7 4",
                "",
                "water-to-light map:",
                "88 18 7",
                "18 25 70",
                "",
                "light-to-temperature map:",
                "45 77 23",
                "81 45 19",
                "68 64 13",
                "",
                "temperature-to-humidity map:",
                "0 69 1",
                "1 0 69",
                "",
                "humidity-to-location map:",
                "60 56 37",
                "56 93 4",
                ""
        ));

        assertThat(dayFive.part1()).isEqualTo(35);
    }
    
    @Test
    void part2Example() {
        DayFive dayFive = new DayFive(Arrays.asList(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                "",
                "soil-to-fertilizer map:",
                "0 15 37",
                "37 52 2",
                "39 0 15",
                "",
                "fertilizer-to-water map:",
                "49 53 8",
                "0 11 42",
                "42 0 7",
                "57 7 4",
                "",
                "water-to-light map:",
                "88 18 7",
                "18 25 70",
                "",
                "light-to-temperature map:",
                "45 77 23",
                "81 45 19",
                "68 64 13",
                "",
                "temperature-to-humidity map:",
                "0 69 1",
                "1 0 69",
                "",
                "humidity-to-location map:",
                "60 56 37",
                "56 93 4",
                ""
        ));

        assertThat(dayFive.part2()).isEqualTo(46);
    }


    @Test
    void part1() {
        assertThat(new DayFive(Utils.readInputLinesFromFile("day05.txt")).part1()).isEqualTo(836040384);
    }
    @Test
    void part2() {
        assertThat(new DayFive(Utils.readInputLinesFromFile("day05.txt")).part2()).isEqualTo(10834440);
    }

    @Test
    void overlapOccurs() {
        SeedRange startRange = new SeedRange(79,92);
        SeedRange nextRange = new SeedRange(new String[]{"52", "50", "48"});
        SeedRange outputRange = startRange.destinationOverlapWithSource(nextRange);
        assertThat(outputRange.destinationStart).isEqualTo(81);
        assertThat(outputRange.destinationEnd).isEqualTo(94);
    }
    @Test
    void overlapDoesNotOccur() {
        SeedRange startRange = new SeedRange(79,92);
        SeedRange nextRange = new SeedRange(new String[]{"50", "98", "2"});
        SeedRange outputRange = startRange.destinationOverlapWithSource(nextRange);
        assertThat(outputRange).isNull();
    }
    
}
