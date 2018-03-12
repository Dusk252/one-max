/**
 * Created by Dusk on 10/19/2017.
 */

import java.util.*;

public class Mutation {
    private double probability;
    private int l;
    private List<Individual> result;

    public Mutation (double probability, int l) {

        this.probability = probability;
        this.l = l;

    }

    //run mutation on a population
    public List<Individual> run (List<Individual> in, double[] rand, int rand_index) {
        result = new ArrayList<>();
        StringBuilder sb;
        for (Individual id : in) {
            String id_bin = id.get_bin();
            sb = new StringBuilder();
            for (int i = 0; i < l; i++) {
                //for each bit in a string, if mutation occurs
                if (rand[rand_index] < probability) {
                    //flip bit
                    sb.append(((id_bin.charAt(i) == '1') ? 0 : 1));
                }
                else {
                    sb.append(id_bin.charAt(i));
                }
                rand_index++;
            }
            id_bin = sb.toString();
            id.set_bin(id_bin);
            id.set_fit(Helper.compute_fitness(id_bin));
        }
        return result=in;
    }

    public List<Individual> get_last_result() {
        return result;
    }
}
