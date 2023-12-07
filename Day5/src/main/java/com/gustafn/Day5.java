package com.gustafn;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day5 {

    private List<Long> seeds;

    private final TreeMap<Long, Range> seedToSoilMap = new TreeMap<>();
    private final TreeMap<Long, Range> soilToFertilizerMap = new TreeMap<>();
    private final TreeMap<Long, Range> fertilizerToWaterMap = new TreeMap<>();
    private final TreeMap<Long, Range> waterToLightMap = new TreeMap<>();
    private final TreeMap<Long, Range> lightToTemperatureMap = new TreeMap<>();
    private final TreeMap<Long, Range> temperatureToHumidityMap = new TreeMap<>();
    private final TreeMap<Long, Range> humidityToLocationMap = new TreeMap<>();

    public Day5(List<String> input) {
        seeds = parseData(input);
    }

    public long part1() {
        List<Long> locations = new LinkedList<>();
        for (Long seed : seeds) {
            locations.add(seedToLocation(seed));
        }
        long location = locations.stream().min(Long::compareTo).get();
        System.out.println("Lowest location " + location);
        return location;
    }

    public long part2() {
        long location = seedToLocation(seeds.get(0));
        for (int i = 0; i < seeds.size(); i += 2) {
            for (long j = 0; j < seeds.get(i + 1); j++) {
                long otherLocation = seedToLocation(seeds.get(i) + j);
                if (otherLocation < location) {
                    location = otherLocation;
                }
            }
        }
        System.out.println("Lowest location " + location);
        return location;
    }

    private List<Long> parseData(List<String> input) {
        List<Long> seeds = new LinkedList<>();
        Map<Long, Range> currentMap = null;
        for (String s : input) {
            if (s.isBlank()) {
                continue;
            } else if (!Character.isDigit(s.charAt(0))) {
                if (s.startsWith("seeds: ")) {
                    String[] seedStrings = s.substring("seeds: ".length())
                            .split(" ");
                    for (String seedString : seedStrings) {
                        seeds.add(Long.parseLong(seedString.trim()));
                    }
                } else if (s.startsWith("seed-to-soil")) {
                    currentMap = seedToSoilMap;
                } else if (s.startsWith("soil-to-fertilizer")) {
                    currentMap = soilToFertilizerMap;
                } else if (s.startsWith("fertilizer-to-water")) {
                    currentMap = fertilizerToWaterMap;
                } else if (s.startsWith("water-to-light")) {
                    currentMap = waterToLightMap;
                } else if (s.startsWith("light-to-temperature")) {
                    currentMap = lightToTemperatureMap;
                } else if (s.startsWith("temperature-to-humidity")) {
                    currentMap = temperatureToHumidityMap;
                } else if (s.startsWith("humidity-to-location")) {
                    currentMap = humidityToLocationMap;
                }
                System.out.println("Parsing map: " + s);
            } else if (currentMap != null) {
                Range range = new Range(s);
                currentMap.put(range.source, range);
            }
        }
        return seeds;
    }

    private long seedToLocation(long seedId) {
        long soil = getFromTree(seedId, seedToSoilMap);
        long fertilizer = getFromTree(soil, soilToFertilizerMap);
        long water = getFromTree(fertilizer, fertilizerToWaterMap);
        long light = getFromTree(water, waterToLightMap);
        long temperature = getFromTree(light, lightToTemperatureMap);
        long humidity = getFromTree(temperature, temperatureToHumidityMap);
        return getFromTree(humidity, humidityToLocationMap);
    }

    private long getFromTree(long id, TreeMap<Long, Range> map) {
        // System.out.printf("Getting %d from %s%n", id, map.toString());
        Map.Entry<Long, Range> e = map.floorEntry(id);
        if (e != null && e.getValue() == null) {
            e = map.lowerEntry(id);
        }
        return e == null ? id : e.getValue().getActualDestination(id);
    }

    private class Range {
        private final long source;
        private final long destination;
        private final long range;

        public Range(String s) {
            String[] parts = s.split(" ");
            this.destination = Long.parseLong(parts[0].trim());
            this.source = Long.parseLong(parts[1].trim());
            this.range = Long.parseLong(parts[2].trim());
        }

        @Override
        public String toString() {
            return "Range{" +
                    "source=" + source +
                    ", destination=" + destination +
                    ", range=" + range +
                    '}';
        }

        public long getActualDestination(long source) {
            long actualDestination = destination + (source - this.source);
            if (actualDestination <= destination + range) {
                return actualDestination;
            } else {
                return source;
            }
        }
    }

}
