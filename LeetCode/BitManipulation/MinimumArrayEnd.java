package BitManipulation;

public class MinimumArrayEnd {
    public static void main(String[] args) {
        var ob = new MinimumArrayEnd();
        System.out.println(ob.minEnd(3, 4) == 6);
        System.out.println(ob.minEnd(2, 7) == 15);
    }

    public long minEnd(int n, int x) {
        var num = (long) x;

        for (var i = 1; i < n; i++)
            num = (num + 1) | x;

        return num;
    }
}
