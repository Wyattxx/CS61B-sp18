public class BreakContinue {
   public static void windowPosSum(int[] a, int n) {
     /** replaces each element a[i] with the sum of a[i] through a[i + n], but only if a[i] is positive valued. */ 
     for (int i=0; i<a.length; i++){
       if (a[i] <=0){
         continue;
       }
       int total = 0;
       for (int j=0; j<=n; j++){
         total += a[i+j];
         if (i+j+1 == a.length){
           break;
         }
       }
       a[i] = total;
     }
   }
 
   public static void main(String[] args) {
     int[] a = {1, 2, -3, 4, 5, 4};
     int n = 3;
     windowPosSum(a, n);
 
     // Should print 4, 8, -3, 13, 9, 4
     System.out.println(java.util.Arrays.toString(a));
   }
 }