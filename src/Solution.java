import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();
        int[] sol = new int[cases];
        for (int i = 0; i < cases; i++) {
            int numHouses = scan.nextInt();
            int total = scan.nextInt();
            String[] inputArr = scan.nextLine().split("");
            int[] houses = new int[inputArr.length];
            for (int j = 0; j < inputArr.length; i++) {
                houses[j] = Integer.parseInt(inputArr[j]);
            }
            sol[i] = howMany(numHouses, total, houses);
        }
        for (int i = 0; i < sol.length; i++) {
            System.out.println("Case #" + (i + 1) + " : " + sol[i]);
        }
    }

    public static int howMany(int numHouse, int total, int[] houses) {
        int index = 0;
        Arrays.sort(houses);
        while (index < houses.length && total > 0) {
            int newTotal = total - houses[index];
            if (newTotal >= 0) {
                index++;
                total = newTotal;
            } else {
                break;
            }
        }
        return index;
    }
}