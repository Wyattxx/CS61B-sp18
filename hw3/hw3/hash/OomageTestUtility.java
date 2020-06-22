package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */

        int N = oomages.size();
        ArrayList<Oomage>[] buckets = new ArrayList[M];
        // 必须要初始化，这点debug了好久，不然下面26行就会 null pointer！！！！！！！！
        for (int i = 0; i < buckets.length; i += 1) {
            buckets[i] = new ArrayList<>();
        }
        for (Oomage o: oomages) {
            //int bucketNum = Math.floorMod(o.hashCode(), M);
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum].add(o);
        }
        //check nice spread
        for (ArrayList<Oomage> b: buckets) {
            if (b.size() < N / 50 || b.size() > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
