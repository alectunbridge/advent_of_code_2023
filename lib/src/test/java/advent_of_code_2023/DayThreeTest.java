package advent_of_code_2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DayThreeTest {

    @Test
    void part1Example() {
        DayThree dayThree = new DayThree(List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598..")
        );

        assertThat(dayThree.part1()).isEqualTo(4361);
    }

    @Test
    void part1() {
        DayThree dayThree = new DayThree(Utils.readInputLinesFromFile("day03.txt")
        );

        assertThat(dayThree.part1()).isEqualTo(4361);
    }

    @Test
    void part2Example() {
        assertThat(new DayThree(Arrays.asList(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598..")
        ).part2()).isEqualTo(467835);
    }

    @Test
    void part2() {
        DayThree dayThree = new DayThree(Utils.readInputLinesFromFile("day03.txt"));

        assertThat(dayThree.part2()).isEqualTo(89471771);
    }
}
