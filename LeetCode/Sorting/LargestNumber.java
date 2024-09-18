package Sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

// Amazon Facebook Microsoft Salesforce Walmart WorksApplications
public class LargestNumber {
    public static void main(String[] args) {
        var ob = new LargestNumber();
        System.out.println(ob.largestNumber(new int[]{10, 2}).equals("210"));
        System.out.println(ob.largestNumber(new int[]{3, 30, 34, 5, 9}).equals("9534330"));
    }

    // Method 1: Without String conversion
    public String largestNumber(int[] nums) {
        var boxed = new Long[nums.length];
        Arrays.setAll(boxed, i -> (long) nums[i]);
        Arrays.sort(boxed, (a, b) -> Long.compare(join(b, a), join(a, b)));

        return boxed[0] == 0
             ? "0"
             : Arrays.stream(boxed)
                     .map(String::valueOf)
                     .collect(Collectors.joining());
    }

    private long join(long a, long b) {
        var digits = (b == 0)
                   ? 1
                   : 1 + Math.floor(Math.log10(b));

        return (long) (a * Math.pow(10, digits) + b);
    }

    // Method 2: With String Conversion
    public String largestNumber2(int[] nums) {
        var strNums = new String[nums.length];
        Arrays.setAll(strNums, i -> Integer.toString(nums[i]));
        Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b));

        return strNums[0].equals("0")
             ? "0"
             : String.join("", strNums);
    }
}
