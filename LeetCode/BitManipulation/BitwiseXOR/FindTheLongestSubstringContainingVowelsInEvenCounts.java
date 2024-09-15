package BitManipulation.BitwiseXOR;

import java.util.HashMap;
import java.util.Map;

// Microsoft
public class FindTheLongestSubstringContainingVowelsInEvenCounts {
    public static void main(String[] args) {
        var ob = new FindTheLongestSubstringContainingVowelsInEvenCounts();
        System.out.println(ob.findTheLongestSubstring("eleetminicoworoep") == 13);
        System.out.println(ob.findTheLongestSubstring("leetcodeisgreat") == 5);
        System.out.println(ob.findTheLongestSubstring("bcbcbc") == 6);
    }

    private static final Map<Character, Integer> VOWEL_TO_MASK = Map.of('a', 1 << 0,
                                                                        'e', 1 << 1,
                                                                        'i', 1 << 2,
                                                                        'o', 1 << 3,
                                                                        'u', 1 << 4);

    public int findTheLongestSubstring(String s) {
        var maxSize = 0;
        var bitMask = 0;
        var n = s.length();
        var maskToFirstIndex = new HashMap<>(Map.of(0, -1));

        for (var i = 0; i < n; i++) {

            bitMask ^= VOWEL_TO_MASK.getOrDefault(s.charAt(i), 0);

            if (!maskToFirstIndex.containsKey(bitMask))                 
                maskToFirstIndex.put(bitMask, i); // executes exactly once for 32 possible masks
            else 
                maxSize = Math.max(maxSize, i - maskToFirstIndex.get(bitMask)); // max of all size-excluding-lower-bound will be the result
        }

        return maxSize;
    }
}

/* if-else block can be replaced by 
    var temp = i;
    maxSize = Math.max(maxSize, i - maskToFirstIndex.computeIfAbsent(bitMask, _ -> temp));
*/