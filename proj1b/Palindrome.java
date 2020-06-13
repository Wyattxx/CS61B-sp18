public class Palindrome {

    /**
     * Given a String, wordToDeque should return a Deque where the characters
     * appear in the same order as in the String.
     * For example, if the word is “persiflage”, then the returned Deque
     * should have ‘p’ at the front, followed by ‘e’, and so forth.
     * @param word
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> a = new LinkedListDeque<>();
        String word2 = word.toLowerCase(); //upper/lower character check
        for (int i = 0; i < word2.length(); i++) {
            a.addLast(word2.charAt(i));
        }
        return a;
    }


    /**
     * helper method, return true if the first and last characters
     * of the deque are the same
     */
    private boolean isPalindrome(Deque<Character> word) {
        if (word.size() < 2) {
            return true;
        }
        if (word.removeFirst() != word.removeLast()) {
            return false;
        }
        return isPalindrome(word);
    }

    /** The isPalindrome method should return true
     * if the given word is a palindrome, and false otherwise.
     * @param word
     */
    public boolean isPalindrome(String word) {
        Deque<Character> a = wordToDeque(word);
        return isPalindrome(a);
    }


    /**
     * OffByOne
     * helper method, return true if the first and last characters
     * of the deque are OffByOne
     */
    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() < 2) {
            return true;
        }
        if (!cc.equalChars(word.removeFirst(), word.removeLast())) {
            return false;
        }
        return isPalindrome(word, cc);
    }
    /**
     * OffByOne
     * The method will return true if the word is a palindrome
     * according to the character comparison test provided by
     * the CharacterComparator passed in as argument cc.
     * @param word
     * @param cc
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> a = wordToDeque(word);
        return isPalindrome(a, cc);
    }
}
