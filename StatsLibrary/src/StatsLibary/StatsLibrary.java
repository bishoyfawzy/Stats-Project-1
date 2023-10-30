package StatsLibary;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatsLibrary {

    // Method to calculate the mean of an array of numbers
    public static double mean(double[] numbers) {
        
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }

    // Method to calculate the median of an array of numbers
    public static double median(double[] numbers) {

        Arrays.sort(numbers);
        int middle = numbers.length / 2;
        if (numbers.length % 2 == 0) {
            double middle1 = numbers[middle - 1];
            double middle2 = numbers[middle];
            return (middle1 + middle2) / 2;
        } else {
            return numbers[middle];
        }
    }

    // Method to calculate the mode of an array of numbers
    public static double mode(double[] numbers) {
        
        Map<Double, Integer> countMap = new HashMap<>();
        for (double num : numbers) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        double mode = 0;
        int maxCount = 0;
        for (Map.Entry<Double, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mode = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mode;
    }

    // Method to calculate the standard deviation of an array of numbers
    public static double standardDeviation(double[] numbers) {
       
        double mean = mean(numbers);
        double sumSquaredDeviations = 0;
        for (double num : numbers) {
            double deviation = num - mean;
            sumSquaredDeviations += deviation * deviation;
        }
        double variance = sumSquaredDeviations / numbers.length;
        return Math.sqrt(variance);
    }

    // Method to calculate the factorial of a number using BigInteger
    public static BigInteger factorial(int n) {

        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Method to calculate combinations (n choose k)
    public static BigInteger combinations(int n, int k) {
        
        BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(k).multiply(factorial(n - k));
        return numerator.divide(denominator);
    }

    // Method to calculate permutations (n P k)
    public static BigInteger permutations(int n, int k) {
        
        return factorial(n).divide(factorial(n - k));
    }

    // Method to calculate the probability of independent intersections
    public static double independentIntersectionProbability(double[] probabilities) {
    

        double result = 1.0;
        for (double probability : probabilities) {
            if (probability < 0 || probability > 1) {
                throw new IllegalArgumentException("Probabilities must be in the range [0, 1].");
            }
            result *= probability;
        }
        return result;
    }

    // Method to calculate the probability of dependent intersections
    public static double dependentIntersectionProbability(double[] probabilities) {

        double result = 1.0;
        for (double probability : probabilities) {
            if (probability < 0 || probability > 1) {
                throw new IllegalArgumentException("Probabilities must be in the range [0, 1].");
            }
            result *= probability;
        }
        return result;
    }

    // Method to calculate the probability of exclusive unions
    public static double exclusiveUnionProbability(double[] probabilities) {
        
        double result = 1.0;
        for (double probability : probabilities) {
            if (probability < 0 || probability > 1) {
                throw new IllegalArgumentException("Probabilities must be in the range [0, 1].");
            }
            result *= (1 - probability);
        }
        return 1 - result;
    }

    // Method to calculate the probability of non exclusive unions
    public static double notExclusiveUnionProbability(double[] probabilities) {
        
        double result = 0.0;
        for (double probability : probabilities) {
            if (probability < 0 || probability > 1) {
                throw new IllegalArgumentException("Probabilities must be in the range [0, 1].");
            }
            result += probability;
        }
        return result;
    }
    
    // Method to check if an element is present in an array
    private static boolean contains(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
    
    public static int[] union(int[] set1, int[] set2) {
        int[] result = Arrays.copyOf(set1, set1.length + set2.length);
        int index = set1.length;
        for (int num : set2) {
            if (!contains(result, num)) {
                result[index++] = num;
            }
        }
        return Arrays.copyOf(result, index);
    }

    // Method to calculate the intersection of two sets
    public static int[] intersection(int[] set1, int[] set2) {
        int[] result = new int[Math.min(set1.length, set2.length)];
        int index = 0;
        for (int num : set1) {
            if (contains(set2, num)) {
                result[index++] = num;
            }
        }
        return Arrays.copyOf(result, index);
    }

    
    public static int[] complement(int[] set, int[] universalSet) {
        List<Integer> setResult = Arrays.stream(universalSet)
                                        .boxed()
                                        .collect(Collectors.toList());
        List<Integer> setList = Arrays.stream(set)
                                      .boxed()
                                      .collect(Collectors.toList());

        setResult.removeAll(setList);

        return setResult.stream().mapToInt(i -> i).toArray();
    }


    // Method to calculate conditional probability P(A | B)
    public static double conditionalProbability(double probabilityA, double probabilityB) {
        if (probabilityB == 0) {
            throw new IllegalArgumentException("Division by zero. Probability B must be nonzero.");
        }
        return probabilityA / probabilityB;
    }

    // Method to calculate Bayes' Theorem P(A | B) = P(B | A) * P(A) / P(B)
    public static double bayesTheorem(double probabilityA, double probabilityBGivenA, double probabilityB) {
        if (probabilityB == 0) {
            throw new IllegalArgumentException("Division by zero. Probability B must be nonzero.");
        }
        return (probabilityBGivenA * probabilityA) / probabilityB;
    }

    // Method to determine independence of two events
    public static boolean isIndependent(double probabilityA, double probabilityB, double jointProbability) {
        // Events A and B are independent if P(A) * P(B) = P(A and B)
        return Math.abs((probabilityA * probabilityB) - jointProbability) < 1e-9;
    }
    
    public static int[] difference(int[] set1, int[] set2) {
        // Convert both arrays to lists for easier processing
        List<Integer> list1 = Arrays.stream(set1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(set2).boxed().collect(Collectors.toList());

        // Remove all elements in list2 from list1
        list1.removeAll(list2);

        // Convert the list back to an array 
        return list1.stream().mapToInt(i -> i).toArray();
    }

    
 // Method to calculate the binomial probability
    public static double binomialProbability(int n, int k, double p) {
        if (n < 0 || k < 0 || k > n || p < 0 || p > 1) {
            throw new IllegalArgumentException("Invalid input for binomial probability calculation.");
        }

        BigInteger numerator = combinations(n, k);
        double q = 1 - p;
        BigInteger denominator = BigInteger.valueOf((long) Math.pow(10, k)).multiply(BigInteger.valueOf((long) Math.pow(10, n - k)));
        return numerator.doubleValue() * Math.pow(p, k) * Math.pow(q, n - k) / denominator.doubleValue();
    }

    // Method to calculate the geometric probability
    public static double geometricProbability(int x, double p) {
        if (x < 1 || p < 0 || p > 1) {
            throw new IllegalArgumentException("Invalid input for geometric probability calculation.");
        }

        double q = 1 - p;
        return Math.pow(q, x - 1) * p;
    }

    // Method to calculate the hypergeometric probability
    public static double hypergeometricProbability(int N, int K, int n, int k) {
        if (N < 0 || K < 0 || n < 0 || k < 0 || K > N || k > n) {
            throw new IllegalArgumentException("Invalid input for hypergeometric probability calculation.");
        }

        BigInteger numerator = combinations(K, k).multiply(combinations(N - K, n - k));
        BigInteger denominator = combinations(N, n);
        return numerator.doubleValue() / denominator.doubleValue();
    }

    // Method to calculate the negative binomial probability
    public static double negativeBinomialProbability(int x, int r, double p) {
        if (x < r || r < 1 || p < 0 || p > 1) {
            throw new IllegalArgumentException("Invalid input for negative binomial probability calculation.");
        }

        double q = 1 - p;
        return combinations(x - 1, r - 1).doubleValue() * Math.pow(p, r) * Math.pow(q, x - r);
    }

    // Method to calculate the Poisson probability
    public static double poissonProbability(int x, double lambda) {
        if (x < 0 || lambda < 0) {
            throw new IllegalArgumentException("Invalid input for Poisson probability calculation.");
        }

        return Math.pow(lambda, x) * Math.exp(-lambda) / factorial(x).doubleValue();
    }

    // Method to calculate the Chebyshev's inequality
    public static double chebyshevInequality(int n, double k) {
        if (n < 1 || k <= 0) {
            throw new IllegalArgumentException("Invalid input for Chebyshev's inequality.");
        }

        return 1 - 1 / (k * k);
    }

}
