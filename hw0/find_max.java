public class find_max1 { 
   /** find the max number in an array using while loop */
   public static int max(final int[] m) {
      int i = 0;
      int max_num = 0;
      while (i < m.length) {
         if (m[i] > max_num) {
            max_num = m[i];
         }
         i = i + 1;
      }

      return max_num;
   }

   public static void main(final String[] args) {
      final int[] numbers = new int[] { 9, 2, 15, 2, 22, 10, 6 };
      System.out.print(find_max1.max(numbers));
      
   }
}


public class find_max { 
   /** find the max number in an array using for loop */
   public static int max(int[] m) {
      int max_num = 0;
      for (int i = 0; i < m.length; i++){
         if (m[i] > max_num){
            max_num = m[i];
         }
      }
      return max_num;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
      System.out.print(find_max.max(numbers));
      
   }
}

