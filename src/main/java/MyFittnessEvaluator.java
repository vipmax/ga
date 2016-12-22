import competition.FitnessEvaluator;

/**
 * Created by max on 22.12.16.
 */
public class MyFittnessEvaluator implements FitnessEvaluator {

    private int result;


    public MyFittnessEvaluator() {
    }

    @Override
    public double evaluate(double[] genotype) {
        int fitness = 0;
        for(int i = 0; i < problemDimension(); ++i)
            fitness += genotype[i];

        result = fitness;
        if (result < 0) result = 0;
//        if (result > 10) result = 10;

        return fitness;
    }

    @Override
    public double result() {
        return result;
    }

    @Override
    public int problemDimension() {
        return 500;
    }
}
