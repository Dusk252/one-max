/**
 * Created by Dusk on 10/19/2017.
 */

import java.text.DecimalFormat;
import java.util.*;

public final class Helper {
    private Helper() {}

    //randomly generate a new population
    public static List<Individual> population_gen(double[]rand, int rand_index, int n, int l) {
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j<l; j++) {
                if (rand[rand_index + i*l + j] < 0.5) {
                    sb.append('1');
                }
                else {
                    sb.append('0');
                }
            }
            String s = sb.toString();
            population.add(new Individual(s, compute_fitness(s)));
        }
        return population;
    }

    //compute fitness of a binary string
    public static int compute_fitness(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') { count++; }
        }
        return count;
    }

    //print a generation
    //prints highest fitness, average fitness, and lowest fitness
    public static String output_gen(List<Individual> population, int id) {
        double sum = 0, current_fit;
        double max_fit = population.get(0).get_fit(), min_fit = population.get(0).get_fit();
        for (Individual i : population) {
            current_fit = i.get_fit();
            if (current_fit > max_fit) { max_fit = current_fit; }
            else { if (current_fit < min_fit) { min_fit = current_fit; } }
            sum += current_fit;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return id + ": " + df.format(max_fit) + " " + df.format(sum/population.size())
                + " " + df.format(min_fit);
    }
}
