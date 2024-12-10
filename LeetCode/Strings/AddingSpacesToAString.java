package Strings;

public class AddingSpacesToAString {
    public static void main(String[] args) {
        var ob = new AddingSpacesToAString();
        System.out.println(ob.addSpaces("LeetcodeHelpsMeLearn", new int[]{8, 13, 15}).equals("Leetcode Helps Me Learn"));
        System.out.println(ob.addSpaces("icodeinpython", new int[]{1, 5, 7, 9}).equals("i code in py thon"));
        System.out.println(ob.addSpaces("spacing", new int[]{0, 1, 2, 3, 4, 5, 6}).equals(" s p a c i n g"));
    }

    public String addSpaces(String s, int[] spaces) {
        var prev = 0;
        var modified = new StringBuilder();

        for (var space : spaces) {
            modified.append(s, prev, space).append(" ");
            prev = space;
        }

        return modified.append(s, prev, s.length()).toString();
    }
}
