package advent_of_code_2023;

import com.google.common.base.MoreObjects;

import java.util.*;
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
            if (seed < closestSeed) {
                closestSeed = seed;
            }
        }

        return closestSeed;
    }

    public long part2() {
        String[] seedString = input.getFirst().replace("seeds: ", "").split(" ");
        List<SeedRange> startRanges = new ArrayList<>();
        for (int i = 0; i <= seedString.length / 2; i += 2) {
            long start = Long.parseLong(seedString[i]);
            long rangeSize = Long.parseLong(seedString[i + 1]);
            startRanges.add(new SeedRange(start, start + rangeSize - 1));
        }

        for (int lineIndex = 2; lineIndex < input.size(); lineIndex++) {
            String currentLine = input.get(lineIndex);
            if ("".equals(currentLine)) {
                maps.getLast().sort(Comparator.comparingLong(SeedRange::getSourceStart));

                SeedRange firstRange = maps.getLast().getFirst();
                if (firstRange.getSourceStart() != 0) {
                    maps.getLast().addFirst(new SeedRange(0, firstRange.getSourceStart() - 1, firstRange.getSourceStart()));
                }

                long lastRangeStart = maps.getLast().getLast().getSourceEnd() + 1;
                maps.getLast().add(new SeedRange(lastRangeStart, lastRangeStart, Long.MAX_VALUE - lastRangeStart));
            } else if (currentLine.contains(":")) {
                maps.add(new ArrayList<>());
            } else {
                maps.getLast().add(new SeedRange(currentLine.split(" ")));
            }
        }

        List<SeedRange> inputRanges = startRanges;
        for (List<SeedRange> map : maps) {
            inputRanges = DayFive.mapInputRangesToOutputRanges(inputRanges, map);
        }

        return inputRanges.stream().mapToLong(SeedRange::getDestinationStart).min().getAsLong();
    }

    private static List<SeedRange> mapInputRangesToOutputRanges(List<SeedRange> inputRanges, List<SeedRange> mappingRanges) {
        List<SeedRange> outputRanges = new ArrayList<>();
        for (SeedRange inputRange : inputRanges) {
            for (SeedRange mappingRange : mappingRanges) {
                SeedRange overlap = inputRange.destinationOverlapWithSource(mappingRange);
                if(overlap != null){
                    outputRanges.add(overlap);
                }
            }
        }
        return outputRanges;
    }


}

class SeedRange {

    Long sourceStart;
    Long sourceEnd;
    Long offset;
    Long destinationStart;
    Long destinationEnd;

    public Long getSourceStart() {
        return sourceStart;
    }

    public Long getSourceEnd() {
        return sourceEnd;
    }

    public Long getOffset() {
        return offset;
    }

    public Long getDestinationStart() {
        return destinationStart;
    }

    public Long getDestinationEnd() {
        return destinationEnd;
    }

    public SeedRange(String[] params) {
        destinationStart = Long.parseLong(params[0]);
        sourceStart = Long.parseLong(params[1]);
        long range = Long.parseLong(params[2]);
        sourceEnd = sourceStart + range - 1;
        destinationEnd = destinationStart + range;
        offset = destinationStart - sourceStart;
    }

    public SeedRange(long destinationStart, long destinationEnd) {
        this.destinationStart = destinationStart;
        this.destinationEnd = destinationEnd;
        this.offset = null;
        this.sourceStart = null;
        this.sourceEnd = null;
    }

    public SeedRange(long sourceStart, long sourceEnd, long range) {
        this.sourceStart = sourceStart;
        this.sourceEnd = sourceStart + range - 1;
        this.destinationStart = sourceStart;
        this.destinationEnd = sourceStart + range - 1;
        this.offset = 0L;
    }

    public Long mapSeed(Long input) {
        if (input >= sourceStart && input <= sourceEnd) {
            return input + offset;
        }
        return null;
    }

    public SeedRange destinationOverlapWithSource(SeedRange nextRange) {
        long maxStart = Long.max(this.destinationStart, nextRange.sourceStart);
        long minEnd = Long.min(this.destinationEnd, nextRange.sourceEnd);
        if (maxStart <= minEnd) {
            return new SeedRange(maxStart + nextRange.offset, minEnd + nextRange.offset);
        }

        return null;
    }
}
