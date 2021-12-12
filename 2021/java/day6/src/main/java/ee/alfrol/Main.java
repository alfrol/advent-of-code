package ee.alfrol;

import ee.alfrol.model.Lanternfish;
import utils.FileUtils;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final int SIMULATION_DURATION_DAYS = 256;

    public static void main(String[] args) {
        String input = FileUtils.readFile(Main.class, "/input.txt").orElseThrow();

        List<Lanternfish> initialLanternfish = Arrays.stream(input.split(","))
                .map(value -> new Lanternfish(Integer.parseInt(value)))
                .toList();

        for (int day = 1; day <= SIMULATION_DURATION_DAYS; day++) {
            initialLanternfish.forEach(Lanternfish::simulate);
        }

        int population =
                initialLanternfish.size() + initialLanternfish.stream().mapToInt(Lanternfish::countChildren).sum();
        System.out.println(population);
    }
}
