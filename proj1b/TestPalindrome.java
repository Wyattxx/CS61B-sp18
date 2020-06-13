import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("anna"));
        assertFalse(palindrome.isPalindrome("wyatt"));

        assertTrue(palindrome.isPalindrome("flake", new OffByOne()));
        assertFalse(palindrome.isPalindrome("wyatt", new OffByOne()));

        assertTrue(palindrome.isPalindrome("abc", new OffByN(2)));
        assertFalse(palindrome.isPalindrome("abbc", new OffByN(2)));
        assertTrue(palindrome.isPalindrome("anna", new OffByN(0)));

    }


}
