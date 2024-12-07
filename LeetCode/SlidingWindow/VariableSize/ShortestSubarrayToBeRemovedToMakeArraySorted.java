package SlidingWindow.VariableSize;

// Amazon
public class ShortestSubarrayToBeRemovedToMakeArraySorted {
    public static void main(String[] args) {
        var ob = new ShortestSubarrayToBeRemovedToMakeArraySorted();
        System.out.println(ob.findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 4, 2, 3, 5}) == 3);
        System.out.println(ob.findLengthOfShortestSubarray(new int[]{5, 4, 3, 2, 1}) == 4);
        System.out.println(ob.findLengthOfShortestSubarray(new int[]{1, 2, 3}) == 0);
    }

    public int findLengthOfShortestSubarray(int[] arr) {
        var left = endOfLongestIncreasingSubArrayOnLeft(arr);
        // array already in non-descending order, so no removal required
        if (left == arr.length - 1)
            return 0;

        var right = startOfLongestIncreasingSubArrayOnRight(arr, left);
        // min(remove subarray after left, remove subarray before right)
        var minLength = Math.min(arr.length - left - 1, right);
        return Math.min(minLength, minRemovalsToMergeSubarrays(arr, right, left));
    }

    /**
     * Identifies the last index of the longest increasing subarray starting
     * from the beginning of the input array.
     *
     * @param arr the input array of integers
     * @return the index of the last element in the longest increasing subarray on the left
     */
    private int endOfLongestIncreasingSubArrayOnLeft(int[] arr) {
        var i = 0;

        while (i + 1 < arr.length && arr[i] <= arr[i + 1])
            i++;

        return i;
    }

    /**
     * Identifies the starting index of the longest increasing subarray from the right,
     * ending after the given `leftEnd` index.
     *
     * @param arr     the input array of integers
     * @param leftEnd the end index of the left subarray
     * @return the starting index of the longest increasing subarray on the right
     */
    private int startOfLongestIncreasingSubArrayOnRight(int[] arr, int leftEnd) {
        var i = arr.length - 1;

        while (i > leftEnd && arr[i - 1] <= arr[i])
            i--;

        return i;
    }

    /**
     * Computes the minimum length of subarray to remove by merging the left
     * and right non-decreasing subarrays.
     *
     * @param arr   the input array of integers
     * @param right the starting index of the right subarray
     * @param left  the ending index of the left subarray
     * @return the minimum length of the subarray to remove
     */
    private int minRemovalsToMergeSubarrays(int[] arr, int right, int left) {
        var i = 0;
        var minLength = arr.length;

        while (i <= left && right < arr.length)
            if (arr[i] <= arr[right]) {
                minLength = Math.min(minLength, right - i - 1);
                i++;
            } else {
                right++;
            }

        return minLength;
    }
}
