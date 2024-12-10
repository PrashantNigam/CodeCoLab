package Strings;

// Yelp
public class CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {
    public static void main(String[] args) {
        var ob = new CheckIfAWordOccursAsAPrefixOfAnyWordInASentence();
        System.out.println(ob.isPrefixOfWord("i love eating burger", "burg") == 4);
        System.out.println(ob.isPrefixOfWord("this problem is an easy problem", "pro") == 2);
        System.out.println(ob.isPrefixOfWord("i am tired", "you") == -1);
    }

    public int isPrefixOfWord(String sentence, String searchWord) {
        var words = sentence.split(" ");

        for (var i = 0; i < words.length; i++)
            if (words[i].startsWith(searchWord))
                return i + 1;

        return -1;
    }
}
