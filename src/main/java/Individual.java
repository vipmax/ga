/**
 * Created by max on 21.12.16.
 */
import competition.FitnessEvaluator;

import java.util.Arrays;
import java.util.Random;

public class Individual
{
    public static final int GENS_COUNT = 500;
    private double[] genes = new double[GENS_COUNT];
    private double fitnessValue;

    public Individual() {}

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public double getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, double gene) {
        this.genes[index] = gene;
    }

    public void randGenes() {
        Random rand = new Random();
        for(int i = 0; i< GENS_COUNT; ++i) {
            this.setGene(i, rand.nextInt(20) - 10);    /* All genes must be in the range [-10; 10] */
        }
    }

    public void mutate() {
        Random rand = new Random();
        int index = rand.nextInt(GENS_COUNT);
        this.setGene(index, 1-this.getGene(index));    // flip
    }

    public double evaluate(FitnessEvaluator evaluator) {
        double fitness = evaluator.evaluate(genes);
        this.setFitnessValue(fitness);
        return fitness;
    }
}