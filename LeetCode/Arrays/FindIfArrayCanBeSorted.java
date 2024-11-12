package Arrays;

public class FindIfArrayCanBeSorted {
    public static void main(String[] args) {
        var ob = new FindIfArrayCanBeSorted();
        System.out.println(ob.canSortArray(new int[]{8, 4, 2, 30, 15}));
        System.out.println(ob.canSortArray(new int[]{1, 2, 3, 4, 5}));
        System.out.println(!ob.canSortArray(new int[]{3, 16, 8, 4, 2}));
    }

    public boolean canSortArray(int[] nums) {
        var maxOfPrev = 0;

        for (var i = 0; i < nums.length;) {
            var j = i + 1;
            var minOfCurrent = nums[i];
            var maxOfCurrent = nums[i];            

            for (var population = Integer.bitCount(nums[i]); j < nums.length && population == Integer.bitCount(nums[j]); j++) {
                minOfCurrent = Math.min(minOfCurrent, nums[j]);
                maxOfCurrent = Math.max(maxOfCurrent, nums[j]);
            }

            if (maxOfPrev > minOfCurrent)
                return false;

            i = j;
            maxOfPrev = maxOfCurrent;
        }

        return true;
    }
}
