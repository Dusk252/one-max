/**
 * Created by Dusk on 10/18/2017.
 */
import java.util.*;

public class NoRep_Tournament {
    private int n, s;
    private List<Individual> result;

    public NoRep_Tournament(int n, int s) {
        this.n = n;
        this.s = s;
    }

    //run a tournament
    //calls its various methods
    public List<Individual> run(List<Individual> in, double[] rand, int rand_index) {
        result = new ArrayList<>();
        Individual[] individuals = in.toArray(new Individual[in.size()]);
        int bag_counter = 0, rand_counter = 0;
        //puts individuals in random order for tournaments
        Individual[] current = new_rand_perm(individuals, rand, rand_counter, rand_index);
        //do n tournaments
        for (int i = 0; i<n; i++) {
            //if bag is empty (all individuals have participated in a tournament), new bag until n winners are reached
            if (bag_counter == n) {
                bag_counter = 0;
                rand_counter += (n-1);
                current = new_rand_perm(individuals, rand, rand_counter, rand_index);
            }
            //match
            match(current, bag_counter);
            bag_counter += s;
        }
        return result;
    }

    //generate random permutation of individuals in a population
    private Individual[] new_rand_perm(Individual[] source, double[] rand, int rand_counter, int rand_index) {
        Individual[] new_perm = new Individual[n];
        System.arraycopy(source, 0, new_perm, 0, n);
        int r;
        for (int i=0; i<(n-1); i++) {
            r = i + (int) ((n - i) * rand[i+rand_counter+rand_index]);
            Individual temp = new_perm[i];
            new_perm[i] = new_perm[r];
            new_perm[r] = temp;
        }
        return new_perm;
    }

    //match between two individuls
    //first one is the winner in case of a tie
    private void match(Individual[] perm, int start_index) {
        Individual winner;
        winner = perm[start_index];
        for (int i=1; i<s; i++) {
            if (perm[i+start_index].compareTo(winner) == 1) {
                winner = perm[i+start_index];
            }
        }
        result.add(winner);
    }

    public List<Individual> get_last_result() {
        return result;
    }
}
