import competition.FitnessEvaluator;

/**
 * Created by max on 22.12.16.
 */
public class MyFittnessEvaluator implements FitnessEvaluator {

    private double result;

    @Override
    public double evaluate(double[] genotype) {
        int fitness = 0;
        for(int i = 0; i < problemDimension(); ++i)
            fitness += genotype[i];

        result = fitness / (problemDimension() * 0.5) - 1;

        if (result < 0) result = 0;

//        System.out.println("result = " + result);;

        return result;
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
