import competition.Algorithm;
import competition.ExampleFitnessEvaluator;
import competition.FitnessEvaluator;

/**
 * Created by max on 22.12.16.
 */
public class Main {
    public static void main(String[] args) {
        FitnessEvaluator fitness = new MyFittnessEvaluator();
        Algorithm alg = new MyAlg(fitness);
        long startTime = System.currentTimeMillis();
        alg.run();

        System.out.println("fitness.result = " + fitness.result());
        System.out.println("elapsed  = " + (System.currentTimeMillis() - startTime) + " ms");
    }
}