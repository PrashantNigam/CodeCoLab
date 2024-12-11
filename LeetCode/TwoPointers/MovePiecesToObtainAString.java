package TwoPointers;

public class MovePiecesToObtainAString {
    public static void main(String[] args) {
        var ob = new MovePiecesToObtainAString();
        System.out.println(ob.canChange("_L__R__R_", "L______RR"));
        System.out.println(!ob.canChange("R_L_", "__LR"));
        System.out.println(!ob.canChange("_R", "R_"));
    }

    public boolean canChange(String start, String target) {
        var t = 0;
        var n = start.length();

        for (var s = 0; s < n || t < n; s++, t++) {

            for (; s < n && start.charAt(s) == '_'; s++);
            for (; t < n && target.charAt(t) == '_'; t++);
          
            if (s == n || t == n)
                return s == n && t == n;
            if (start.charAt(s) != target.charAt(t) ||
                start.charAt(s) == 'L' && s < t ||
                start.charAt(s) == 'R' && s > t)
                return false;
        }

        return true;
    }
}
