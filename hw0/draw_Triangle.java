public class draw_Triangle {
   /** print lower triangle maxtrix with str '*' */
   public static void drawTriangle(final int n) {
      int row = 1;
      int col = 1;
      while (row < n + 1) {
         while (row >= col) {
            System.out.print('*');
            col = col + 1;
         }
         row = row + 1;
         col = 1;
         System.out.println();
      }
   }

   public static void main(final String[] args) {
      draw_Triangle.drawTriangle(10);
   }
}