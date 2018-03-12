/**
 * Created by Dusk on 10/18/2017.
 */
import java.util.*;

public class OneP_Crossover {
    private List<Individual> result;
    double probability;
    int l;
    int last_rand_index = 0;

    public OneP_Crossover (double probability, int l) {

        this.probability = probability;
        this.l = l;

    }

    //run crossover on a population
    public List<Individual> run(List<Individual> in, double[] rand, int rand_index) {
        Individual p1, p2;
        String p1_bin, p2_bin, c1_bin, c2_bin;
        int initial_rand_index = rand_index;
        result = new ArrayList<>();
        int crosspoint, n = in.size();
        for (int i = 0; i<n; i++) {
            p1 = in.get(i);
            p2 = in.get(++i);
            //only do crossover with the indicated probability
            if (rand[rand_index++] < probability) {
                //choose crosspoint
                crosspoint = (int)(rand[rand_index++]*(l-1) + 1);
                //get parents
                p1_bin = p1.get_bin();
                p2_bin = p2.get_bin();
                //create children
                c1_bin = p1_bin.substring(0, crosspoint) + p2_bin.substring(crosspoint, l);
                c2_bin = p2_bin.substring(0, crosspoint) + p1_bin.substring(crosspoint, l);
                result.add(new Individual(c1_bin, Helper.compute_fitness(c1_bin)));
                result.add(new Individual(c2_bin, Helper.compute_fitness(c2_bin)));
            }
            else {
                result.add(new Individual(p1.get_bin(), p1.get_fit()));
                result.add(new Individual(p2.get_bin(), p2.get_fit()));
            }
        }
        last_rand_index = rand_index - initial_rand_index;
        return result;
    }

    public List<Individual> get_last_result() {
        return result;
    }

    public int get_rand_index() { return last_rand_index; }
}
