package StatsLibary;

import java.util.Arrays;

public class StatsLibraryTester {

    private static double[] data = { 1.2, 2.5, 3.6, 2.5, 4.8, 3.6 };
    
    public static void main(String[] args) {
        // Invoking all the test methods
        testStatistics();
    }

    public static void testStatistics() {
        
        testMeanMedianModeStandardDeviation();
        testProbabilityAxioms();
        testSetTheoryConditionalProbability();
        testProbabilityDistributions();
        
    }

    private static void testMeanMedianModeStandardDeviation() {
        System.out.println("Testing Mean, Median, Mode & Standard Deviation");
        System.out.println("---------------------------------------------");
        System.out.println("Given data: " + Arrays.toString(data));
        
        System.out.println("Mean: " + StatsLibrary.mean(data));
        System.out.println("Median: " + StatsLibrary.median(data));
        System.out.println("Mode: " + StatsLibrary.mode(data));
        System.out.println("Standard Deviation: " + StatsLibrary.standardDeviation(data));
        System.out.println();
    }

    private static void testProbabilityAxioms() {
        System.out.println("Testing Probability Axioms");
        System.out.println("--------------------------");
        
        double[] independentProbabilities = { 0.5, 0.4, 0.3 };
        double[] dependentProbabilities = { 0.5, 0.4, 0.3 };
        double[] exclusiveProbabilities = { 0.2, 0.3, 0.4 };
        double[] notExclusiveProbabilities = { 0.2, 0.3, 0.4 };

        System.out.println("Independent Intersection Probability: " + StatsLibrary.independentIntersectionProbability(independentProbabilities));
        System.out.println("Dependent Intersection Probability: " + StatsLibrary.dependentIntersectionProbability(dependentProbabilities));
        System.out.println("Exclusive Union Probability: " + StatsLibrary.exclusiveUnionProbability(exclusiveProbabilities));
        System.out.println("Not Exclusive Union Probability: " + StatsLibrary.notExclusiveUnionProbability(notExclusiveProbabilities));
        System.out.println();
    }

    private static void testSetTheoryConditionalProbability() {
        System.out.println("Testing Set Theory, Conditional Probability, and Independence");
        System.out.println("---------------------------------------------------------");
        
        int[] set1 = { 1, 2, 3, 4, 5 };
        int[] set2 = { 4, 5, 6, 7, 8 };
        int[] universalSet = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        System.out.println("Union: " + Arrays.toString(StatsLibrary.union(set1, set2)));
        System.out.println("Intersection: " + Arrays.toString(StatsLibrary.intersection(set1, set2)));
        System.out.println("Difference (Set1 - Set2): " + Arrays.toString(StatsLibrary.difference(set1, set2)));
        System.out.println("Complement of Set1 in Universal Set: " + Arrays.toString(StatsLibrary.complement(set1, universalSet)));
        
        System.out.println();
    }

    private static void testProbabilityDistributions() {
        System.out.println("Testing Probability Distributions");
        System.out.println("-------------------------------");

        // Test binomialProbability
        int n = 5;
        int k = 2;
        double p = 0.5;
        System.out.println("Binomial Probability (n=5, k=2, p=0.5): " + StatsLibrary.binomialProbability(n, k, p));

        // Test geometricProbability
        int x = 3;
        System.out.println("Geometric Probability (x=3, p=0.5): " + StatsLibrary.geometricProbability(x, p));

        // Test hypergeometricProbability
        int N = 20;
        int K = 10;
        n = 5;
        System.out.println("Hypergeometric Probability (N=20, K=10, n=5, k=2): " + StatsLibrary.hypergeometricProbability(N, K, n, k));

        // Test negativeBinomialProbability
        int r = 3;
        System.out.println("Negative Binomial Probability (x=5, r=3, p=0.5): " + StatsLibrary.negativeBinomialProbability(x, r, p));

        // Test poissonProbability
        double lambda = 3.5;
        System.out.println("Poisson Probability (x=3, lambda=3.5): " + StatsLibrary.poissonProbability(x, lambda));

        // Test chebyshevInequality
        double k_val = 2.5;
        System.out.println("Chebyshev's Inequality (n=5, k=2.5): " + StatsLibrary.chebyshevInequality(n, k_val));

        System.out.println();
    }

}
