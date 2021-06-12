import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'getUnallottedUsers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY bids
     *  2. INTEGER totalShares
     */

    public static List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        List<Integer> sol = new ArrayList<>();
        Collections.sort(bids, (n1, n2) -> n1.get(2) == n2.get(2) ? n1.get(3) - n2.get(3) : n2.get(2) - n1.get(2));
        removeId(bids, totalShares);
        for (List<Integer> bidder : bids) {
            sol.add(bidder.get(0));
        }
        return sol;
    }
    public static void removeId(List<List<Integer>> bids, int totalShares) {
        if (bids.size() <= 0 || totalShares <= 0) {
            return;
        }
        List<List<Integer>> giveShares = new ArrayList<>();
        int maxBid = bids.get(0).get(2);
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).get(2) == maxBid) {
                giveShares.add(bids.get(i));
            }
        }
        totalShares = pruneBids(giveShares, bids, totalShares);
        removeId(bids, totalShares);
    }

    public static int pruneBids(List<List<Integer>> giveShares,List<List<Integer>> bids, int totalShares) {
        List<List<Integer>> idToRemove = new ArrayList<>();
        if (giveShares.size() == 1) {
            totalShares -= giveShares.get(0).get(1);
            if (totalShares >= 0) {
                bids.remove(giveShares.get(0));
            }
        } else {
            int index = 0;
            while (!giveShares.isEmpty() && totalShares > 0) {
                int currentBidder = (index % giveShares.size());
                giveShares.get(currentBidder).set(1, giveShares.get(currentBidder).get(1) - 1);
                idToRemove.add(giveShares.get(currentBidder));
                if (giveShares.get(currentBidder).get(1) == 0) {
                    bids.remove(giveShares.get(currentBidder));
                    idToRemove.remove(giveShares.get(currentBidder));
                    giveShares.remove(currentBidder);
                } else {
                    index++;
                }
                totalShares--;
            }
        }
        for (List<Integer> toRemove : idToRemove) {
            bids.remove(toRemove);
        }
        return totalShares;
    }

    public static void main (String[] args) {
        List<Integer> first = new ArrayList<>(Arrays.asList(1,5,5,0));
        List<Integer> second = new ArrayList<>(Arrays.asList(2,7,8,1));
        List<Integer> third = new ArrayList<>(Arrays.asList(3,7,5,1));
        List<Integer> fourth = new ArrayList<>(Arrays.asList(4,10,3,3));
        List<List<Integer>> tester = new ArrayList<>();
        tester.add(third);
        tester.add(first);
        tester.add(second);
        tester.add(fourth);
        getUnallottedUsers(tester, 18);
    }
}