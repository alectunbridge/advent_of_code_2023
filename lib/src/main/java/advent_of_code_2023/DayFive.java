package advent_of_code_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayFive {
    private final List<String> input;
    private List<Long> seeds;
    private List<List<SeedRange>> maps = new ArrayList<>();

    public DayFive(List<String> input) {
        this.input = input;
    }

    public long part1() {
        seeds = Arrays.stream(input.getFirst().replace("seeds: ", "").split(" "))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        for (int lineIndex = 1; lineIndex < input.size(); lineIndex++) {
            String currentLine = input.get(lineIndex);
            if ("".equals(currentLine)) {
                //do nowt
            } else if (currentLine.contains(":")) {
                maps.add(new ArrayList<>());
            } else {
                maps.getLast().add(new SeedRange(currentLine.split(" ")));
            }
        }

        Long closestSeed = Long.MAX_VALUE;
        for (Long seed : seeds) {
            MAPS:
            for (List<SeedRange> map : maps) {
                for (SeedRange seedRange : map) {
                    Long mappedValue = seedRange.mapSeed(seed);
                    if (mappedValue != null) {
                        seed = mappedValue;
                        continue MAPS;
                    }
                }
            }
            if(seed < closestSeed) {
                closestSeed = seed;
            }
        }

        return closestSeed;
    }

    private class SeedRange {

        private final long sourceStart;
        private final long sourceEnd;
        private final long offset;

        public SeedRange(String[] params) {
            long destinationStart = Long.parseLong(params[0]);
            sourceStart = Long.parseLong(params[1]);
            long range = Long.parseLong(params[2]);
            sourceEnd = sourceStart + range;
            offset = destinationStart - sourceStart;
        }

        public Long mapSeed(Long input) {
            if (input >= sourceStart && input <= sourceEnd) {
                return input + offset;
            }
            return null;
        }
    }
}
