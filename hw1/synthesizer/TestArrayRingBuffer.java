package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(1); // 1
        arb.enqueue(2); // 1 2
        arb.enqueue(3); // 1 2 3
        arb.dequeue(); // 2 3
        arb.enqueue(1); // 2 3 1
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        assertEquals(10, arb.capacity);
        assertEquals(10, arb.fillCount);
        assertEquals((Object) 2, arb.peek());

    }
} 
