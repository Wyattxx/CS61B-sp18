package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     * and fail the test
     */
    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int paramsLength = 10;
            ArrayList<Integer> params = new ArrayList<>(paramsLength);
            for (int j = 0; j < paramsLength; j += 1) {
                if (j > 5) {
                    params.add(j);
                } else {
                    params.add(StdRandom.uniform(0, 255));
                }
            }
            deadlyList.add(new ComplexOomage(params));
        }
        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10)); // 理论上是false, AG要求这里失败
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
