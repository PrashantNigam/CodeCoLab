package Counting;

// ByteDance Google Hulu
public class KthSmallestInLexicographicalOrder {
    public static void main(String[] args) {
        var ob = new KthSmallestInLexicographicalOrder();
        System.out.println(ob.findKthNumber(13, 2) == 10);
        System.out.println(ob.findKthNumber(1, 1) == 1);
    }

    public int findKthNumber(int n, int k) {
        var num = 1;
        k--;

        while (k > 0) {
            var steps = getSteps(n, num, num + 1);

            if (steps <= k) {
                num++;
                k -= steps;
            } else {
                num *= 10;
                k--;
            }
        }

        return num;
    }

    // count steps in semi-closed range [current, min(next, n + 1))
    private int getSteps(int n, long current, long next) {
        var steps = 0;

        while (current <= n) {
            steps += Math.min(next, n + 1) - current;
            // move both current and next to next level
            current *= 10;
            next *= 10;
        }

        return steps;
    }

    // Method 2: Use LC 386. Gets TLE
    public int findKthNumber2(int n, int k) {
        var ans = new int[1];
        var kArr = new int[]{k};

        for (var i = 1; i < 10; i++)
            if (kThSmallest(i, n, kArr, ans))
                break;

        return ans[0];
    }

    private boolean kThSmallest(int i, int n, int[] k, int[] ans) {
        if (i > n)
            return false;

        if (k[0] == 1) {
            ans[0] = i;
            return true;
        }

        k[0]--;

        for (var j = 0; j < 10; j++)
            if (kThSmallest(i * 10 + j, n, k, ans))
                return true;

        return false;
    }
}
