/**
 * Created by Dusk on 10/17/2017.
 * Assignment for an Evolutionary Computation course
 * using an array of rands and rand_index is an artifact from having to read the rands instead of generating
 * not going to fix
 */
import java.util.*;
public class main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        //population size
        System.out.println("Population size: ");
        int n = in.nextInt();
        //individual size
        System.out.println("Individual size: ");
        int l = in.nextInt();
        //tournament size
        System.out.println("Tournament size: ");
        int s = in.nextInt();

        //mutation probability
        System.out.println("Probability of mutation: ");
        double pm = in.nextDouble();
        //crossover probability
        System.out.println("Probability of crossover: ");
        double pc = in.nextDouble();

        //replacement ratio (r=1 -> full replacement)
        //double r = 1;
        System.out.println("Replacement ratio: ");
        double r = in.nextDouble();

        //number of generations
        //int g = 1;
        System.out.println("Number of generations: ");
        int g = in.nextInt();

        //int m = in.nextInt();

        double[] rand = new double[n*l+g*(n*l+n+s*(n-1))];
        for (int i = 0; i<rand.length; i++) {
            rand[i] = Math.random();
            //System.out.println(rand[i]);
        }
        /*double[] rand = new double[m];
        for (int i = 0; i < m; i++) {
            rand[i] = in.nextDouble();
        }*/

        int rand_index = 0;

        //generate initial population
        List<Individual> population = Helper.population_gen(rand, rand_index, n, l);

        //print initial population
        System.out.println(Helper.output_gen(population, 0));

        rand_index += n*l;

        //create new population
        List<Individual> new_population;
        NoRep_Tournament T = new NoRep_Tournament(n, s);
        OneP_Crossover C = new OneP_Crossover(pc, l);
        Mutation M = new Mutation(pm, l);

        //for each generation, run tournament selection, crossover, and mutation
        int i = 0;
        for (i = 0; i<g; i++) {
            new_population = T.run(population, rand, rand_index);
            rand_index += s*(n-1);
            new_population = C.run(new_population, rand, rand_index);
            rand_index += C.get_rand_index();
            new_population = M.run(new_population, rand, rand_index);
            rand_index += n*l;

            //sort old population to keep the best values (compareTo method for Individual is overriden)
            Collections.sort(population);

            //grab best values from the old population according to r and complete with values from the new population
            int r_index = (int)(n*(1-r));
            population = population.subList(n-r_index, n);
            population.addAll(new_population.subList(0, n-r_index));
            //print population at each gen
            System.out.println(Helper.output_gen(population, i+1));
        }
    }
}
