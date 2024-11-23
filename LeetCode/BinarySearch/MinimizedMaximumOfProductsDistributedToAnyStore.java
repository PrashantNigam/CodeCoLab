package BinarySearch;

public class MinimizedMaximumOfProductsDistributedToAnyStore {
    public static void main(String[] args) {
        var ob = new MinimizedMaximumOfProductsDistributedToAnyStore();
        System.out.println(ob.minimizedMaximum(6, new int[]{11, 6}) == 3);
        System.out.println(ob.minimizedMaximum(7, new int[]{15, 10, 10}) == 5);
        System.out.println(ob.minimizedMaximum(1, new int[]{100000}) == 100000);
    }

    private static final int MAX = (int) 1e5;

    public int minimizedMaximum(int n, int[] quantities) {
        var minMax = 0;
        var high = MAX;

        for (var low = 1; low <= high; ) {
            var mid = low + (high - low >> 1);
            if (distributable(n, quantities, mid)) {
                minMax = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return minMax;
    }

    private boolean distributable(int n, int[] quantities, int shops) {
        for (var quantity : quantities) {
            n -= Math.ceilDiv(quantity, shops);
          
            if (n < 0)
                return false;
        }

        return true;
    }
}
