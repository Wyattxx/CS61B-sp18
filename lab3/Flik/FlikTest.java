import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    @Test
    public void testFilk() {
        int a = 1;
        int b = 1;
        int a1 = 1;
        int b1 = 2;
        assertTrue("XXXXXX", Flik.isSameNumber(a, b));
        assertFalse("?????", Flik.isSameNumber(a1, b1));
    }

}
