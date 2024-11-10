package Strings;

//Amazon Apple eBay LinkedIn Microsoft
public class RotateString {
    public static void main(String[] args) {
        var ob = new RotateString();
        System.out.println(ob.rotateString("abcde", "cdeab"));
        System.out.println(!ob.rotateString("abcde", "abced"));
    }

    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() 
            && (s + s).contains(goal);
    }
}
