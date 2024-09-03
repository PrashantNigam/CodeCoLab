package Strings;

// Microsoft
public class SumOfDigitsOfStringAfterConvert {
    public static void main(String[] args) {
        var ob = new SumOfDigitsOfStringAfterConvert();
        System.out.println(ob.getLucky("iiii", 1) == 36);
        System.out.println(ob.getLucky("leetcode", 2) == 6);
        System.out.println(ob.getLucky("zbax", 2) == 8);
    }

    /**
     * 1. Convert s into an integer by replacing each letter with its position in the alphabet
     * (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26)
     * 2. Transform the integer by replacing it with the sum of its digits
     * 3. Repeat Step 2 k times
     *
     * @param s consisting of lowercase English letters
     * @param k repetitions
     * @return the resulting integer after performing the operations described above
     */
    public int getLucky(String s, int k) {
        var lucky = 0;
        var n = s.length();

        for (var i = 0; i < n; i++)
            lucky += digitSum(s.charAt(i) - 'a' + 1);

        while (--k > 0)
            lucky = digitSum(lucky);

        return lucky;
    }

    private int digitSum(int num) {
        var sum = 0;

        for (; num != 0; num /= 10)
            sum += num % 10;

        return sum;
    }
}