package Topics.TwoPointers;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

// Google
public class SentenceSimilarityIII {
    public static void main(String[] args) {
        var ob = new SentenceSimilarityIII();
        System.out.println(ob.areSentencesSimilar("My name is Haley", "My Haley"));
        System.out.println(!ob.areSentencesSimilar("of", "A lot of words"));
        System.out.println(ob.areSentencesSimilar("Eating right now", "Eating"));
    }

    public static final String SPACE = " ";

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        var left1 = 0;
        var left2 = 0;
        var words1 = sentence1.split(SPACE);
        var words2 = sentence2.split(SPACE);
        var right1 = words1.length - 1;
        var right2 = words2.length - 1;

        while (left1 < words1.length && left2 < words2.length && words1[left1].equals(words2[left2])) {
            left1++;
            left2++;
        }
        while (right1 >= left1 && right2 >= left2 && words1[right1].equals(words2[right2])) {
            right1--;
            right2--;
        }

        return left1 - right1 == 1
                || left2 - right2 == 1;
    }

    public boolean areSentencesSimilar2(String sentence1, String sentence2) {
        var words1 = split(sentence1);
        var words2 = split(sentence2);

        while (!words1.isEmpty() && !words2.isEmpty() && words1.peekFirst().equals(words2.peekFirst())) {
            words1.pollFirst();
            words2.pollFirst();
        }
        while (!words1.isEmpty() && !words2.isEmpty() && words1.peekLast().equals(words2.peekLast())) {
            words1.pollLast();
            words2.pollLast();
        }

        return words1.isEmpty() || words2.isEmpty();
    }

    private Deque<String> split(String s) {
        var dq = new ArrayDeque<String>();
        Collections.addAll(dq, s.split(SPACE));
        return dq;
    }
}
