import java.util.HashSet;

public class LargestComponent {
    public int largestComponentSize(int[] A) {
        int result = 0;
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            result = Math.max(search(A, seen, A[i]), result);
        }
        return result;
    }
    public int search(int[] A, HashSet<Integer> seen, int num1) {
        boolean complete = true;
        for (int i = 0; i < A.length; i++) {
            if (!seen.contains(A[i])) {
                complete = false;
            }
        }
        if (complete) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (!seen.contains(A[i]) && gcd(num1, A[i]) > 1) {
                HashSet<Integer> newSeen = (HashSet)seen.clone();
                newSeen.add(A[i]);
                result = Math.max(result, 1 + search(A, newSeen, A[i]));
            }
        }
        return result;
    }

    public int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}
