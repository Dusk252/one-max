/**
 * Created by Dusk on 10/3/2017.
 */
public class Individual implements Comparable<Individual> {
    protected String bin;
    protected double fit_val;

    public Individual (String bin, double fit_val) {
        this.bin = bin;
        this.fit_val = fit_val;
    }

    public String get_bin() {
        return bin;
    }
    public double get_fit() {
        return fit_val;
    }

    public void set_bin(String new_bin) {
        bin = new_bin;
    }
    public void set_fit(double new_fit) {
        fit_val = new_fit;
    }

    //overrides compareTo method to use the fitness as comparation criteria for sorting purposes
    @Override
    public int compareTo(Individual i) {
        return Double.compare(fit_val, i.fit_val);
    }

    //returns binary string
    @Override
    public String toString() {
        return bin;
    }

}