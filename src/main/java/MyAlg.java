import competition.Algorithm;
import competition.FitnessEvaluator;

import java.util.Random;

/**
 * Created by max on 22.12.16.
 */
public class MyAlg implements Algorithm {

    private final static int ELITISM_K = 5;
    private final static int POP_SIZE = 200 + ELITISM_K;  // population size
    private final static int MAX_ITER = 2000;             // max number of iterations
    private final static double MUTATION_RATE = 0.05;     // probability of mutation
    private final static double CROSSOVER_RATE = 0.7;     // probability of crossover

    private FitnessEvaluator evaluator;
    private Population population ;

    private static Random m_rand = new Random();  // random-number generator
    private Individual[] m_population;
    public double totalFitness;

    public MyAlg(FitnessEvaluator fitness) {
        evaluator = fitness;
        population = new Population(evaluator);
    }


    @Override
    public void setEvaluator(FitnessEvaluator fitnessEvaluator) {
        evaluator = fitnessEvaluator;
        population = new Population(evaluator);
    }

    @Override
    public void run() {

        Individual[] newPop = new Individual[POP_SIZE];
        Individual[] indiv = new Individual[2];

        for (int iter = 0; iter < MAX_ITER; iter++) {
            int count = 0;

            // Elitism
            for (int i=0; i<ELITISM_K; ++i) {
                newPop[count] = population.findBestIndividual();
                count++;
            }

            // build new Population
            while (count < POP_SIZE) {
                // Selection
                indiv[0] = population.rouletteWheelSelection();
                indiv[1] = population.rouletteWheelSelection();

                // Crossover
                if ( m_rand.nextDouble() < CROSSOVER_RATE ) {
                    indiv = Population.crossover(indiv[0], indiv[1]);
                }

                // Mutation
                if ( m_rand.nextDouble() < MUTATION_RATE ) {
                    indiv[0].mutate();
                }
                if ( m_rand.nextDouble() < MUTATION_RATE ) {
                    indiv[1].mutate();
                }

                // add to new population
                newPop[count] = indiv[0];
                newPop[count+1] = indiv[1];
                count += 2;
            }
            population.setPopulation(newPop);

            // reevaluate current population
            population.evaluate(evaluator);
//            System.out.printf("Total Fitness = %s Best Fitness = %s%n", population.totalFitness, population.findBestIndividual().getFitnessValue());
        }

        // best indiv
        Individual bestIndiv = population.findBestIndividual();
        System.out.println("bestIndividual = " + bestIndiv);

    }
}