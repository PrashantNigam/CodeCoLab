package DivideAndConquer;

// Amazon Microsoft
public class FindKthBitInNthBinaryString {
    public static void main(String[] args) {
        var ob = new FindKthBitInNthBinaryString();
        System.out.println(ob.findKthBit(3, 1) == '0');
        System.out.println(ob.findKthBit(4, 11) == '1');
    }

    public char findKthBit(int n, int k) {
        if (n == 1)
            return '0';

        var sizeOfBinary = (1 << n) - 1;
        var halfSize = Math.ceilDiv(sizeOfBinary, 2);

        if (k == halfSize)
            return '1';
        if (k < halfSize)
            return findKthBit(n - 1, k);
      
        return invert(findKthBit(n - 1, sizeOfBinary - k + 1));
    }

    private char invert(char bit) {
        return bit == '0' ? '1' : '0';
    }
}
