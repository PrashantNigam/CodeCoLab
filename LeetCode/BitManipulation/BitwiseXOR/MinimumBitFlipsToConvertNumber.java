package BitManipulation.BitwiseXOR;

public class MinimumBitFlipsToConvertNumber {
    public static void main(String[] args) {
        var ob = new MinimumBitFlipsToConvertNumber();
        System.out.println(ob.minBitFlips(10, 7) == 3);
        System.out.println(ob.minBitFlips(3, 4) == 3);
    }

    // XOR 2 numbers. The result will have only those bits set which are different in the numbers
    public int minBitFlips(int start, int goal) {
        return Integer.bitCount(start ^ goal);
    }

    // Simple Approach: Compare each bit and count different ones
    public int minBitFlips2(int start, int goal) {
        var flips = 0;

        for (; start != 0 || goal != 0; start >>= 1, goal >>= 1)
            if ((start & 1) != (goal & 1))
                flips++;
        
        return flips;
    }
}
