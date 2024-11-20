package BinarySearch;

import java.util.List;
import java.util.TreeSet;

public class PrimeSubtractionOperation {
    public static void main(String[] args) {
        var ob = new PrimeSubtractionOperation();
        System.out.println(ob.primeSubOperation(new int[]{4, 9, 6, 10}));
        System.out.println(ob.primeSubOperation(new int[]{6, 8, 11, 12}));
        System.out.println(!ob.primeSubOperation(new int[]{5, 8, 3}));
    }

    public static final TreeSet<Integer> PRIMES = new TreeSet<>(List.of(2));

    static {
        var sieve = new Boolean[1001];
        sieve[2] = true;

        for (var i = 3; i <= 1000; i += 2) {
            if ((sieve[i] != null && !sieve[i]) || !isPrime(i))
                continue;

            sieve[i] = true;
            PRIMES.add(i);

            for (var j = 3; i * j <= 1000; j += 2)
                sieve[i * j] = false;
        }
    }

    /*
    find the smallest prime such that nums[i - 1] - prime < nums[i]
    the smallest prime such that nums[i - 1] - nums[i] < prime
    */
    public boolean primeSubOperation(int[] nums) {
        for (var i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1])
                continue;

            var higher = PRIMES.higher(nums[i - 1] - nums[i]);

            if (higher == null || nums[i - 1] <= higher)
                return false;

            nums[i - 1] -= higher;
        }

        return true;
    }

    private static boolean isPrime(int n) {
        if (n == 3)
            return true;
        if (n % 3 == 0)
            return false;

        var potentialFactor = 5; // 6k - 1

        // step alternates between 2 and 4
        for (var step = 2; potentialFactor * potentialFactor <= n; step = 6 - step) {
            if (n % potentialFactor == 0)
                return false;
            potentialFactor += step; // Alternates between 6k+1 and 6k-1
        }

        return true;
    }
}
