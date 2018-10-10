/*
 * At one international Olympiad in Informatics, the following task was given. 
 * Let's solve it! 
 *     7 
 *    3 8 
 *   8 1 0 
 *  2 7 4 4 
 * 4 5 2 6 5 
 * The figure shows an example of a triangle of numbers. Write a program that 
 * calculates the largest sum of numbers through which the path starts, starting
 * at the top and ending somewhere on the bottom. Each step can go diagonally
 * down to the right or diagonally to the left. 
 * In the example - this is the path 7➡3➡8➡7➡5, given a max amount of 30.
 */
package goldenmountain;

public class GoldenMountain {

    private final int[][] theMountain;
    private String route;
    
    public static void main(String[] args) {
        int[] line0 = {7};
        int[] line1 = {3, 8};
        int[] line2 = {8, 1, 0};
        int[] line3 = {2, 7, 4, 4};
        int[] line4 = {4, 5, 2, 6, 5};
        GoldenMountain gm = new GoldenMountain(line0, line1, line2, line3, line4);
        gm.calcRoute(0, 0);
        System.out.printf("The way: %s%n", gm.route.substring(0, gm.route.length() - 4));
        System.out.printf("The max value = %d%n", gm.solve(0, 0));
    }

    public GoldenMountain(int[]... args) {
        this.theMountain = args;
        this.route = "";
    }
    
    public int solve(int row, int column) {
        return this.theMountain[row][column] + 
                ((row == this.theMountain.length - 1) ? 0 :
                Math.max(solve(row + 1, column + 1), solve(row + 1, column)));
    }

    public void calcRoute(int row, int column) {
        this.route += String.format("[%d, %d]: %d -> ", row, column, 
                this.theMountain[row][column]);
        int routeRow = row;
        int routeColumn = column;
        if (!(row == this.theMountain.length - 1)) {
            routeRow++;
            if (solve(row + 1, column + 1) > solve(row + 1, column)) {
                routeColumn++;
            }
            this.calcRoute(routeRow, routeColumn);
        } 
    }
}