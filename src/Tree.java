import java.util.*;

public class Tree {

    int val;
    Tree left;
    Tree right;
    Tree() {}
    Tree(int val) { this.val = val; }
    Tree(int val, Tree left, Tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public int deepestLeavesSum(Tree root) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (root == null) {
            return 0;
        }
        ArrayDeque<Tree> fringe = new ArrayDeque<>();
        fringe.push(root);
        while (fringe != null) {
            Tree node = fringe.pop();
            if (node.left != null || node.right != null) {
                numbers.clear();
            }
            if (node.left != null) {
                fringe.push(node.left);
            }
            if (node.right != null) {
                fringe.push(node.right);
            }
            if (node.left == null && node.right == null) {
                numbers.add(node.val);
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    public boolean leafSimilar(Tree root1, Tree root2) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        HashSet<Integer> seenLeaves = new HashSet<>();
        List root1List = leaves(root1, arr);
        List root2List = leaves(root2, arr);
        seenLeaves.addAll(root1List);
        for (int i = 0; i < root2List.size(); i++) {
            if (!seenLeaves.contains(root2List.get(i))) {
                return false;
            }
            seenLeaves.remove(root2List.get(i));
        }
        return seenLeaves.isEmpty();
    }

    public List<Integer> leaves(Tree root, List<Integer> arr) {
        if (root == null) {
            return arr;
        }
        if (root.left == null && root.right == null) {
            arr.add(root.val);
        }
        leaves(root.left, arr);
        leaves(root.right, arr);
        return arr;
    }

    public static Tree  minHeight(int[] arr) {
        return helperMinHeight(arr, 0, arr.length);
    }

    public static Tree helperMinHeight(int[] arr, int low, int high) {
        if (low == high) {
            return null;
        }
        Tree root = new Tree(arr[(low + high) / 2]);
        root.left = helperMinHeight(arr, low, (low + high) / 2);
        root.right = helperMinHeight(arr,(low + high) / 2 + 1, high);
        return root;
    }

    public static ArrayList<LinkedList<Tree>> create(Tree root) {
        ArrayList<LinkedList<Tree>> result = new ArrayList<>();
        Queue<Tree> fringe = new LinkedList<>();
        LinkedList<Tree> levels = new LinkedList<>();
        fringe.add(root);
        fringe.add(null);
        while (!fringe.isEmpty()) {
            Tree curr = fringe.poll();
            if (curr == null) {
                result.add(levels);
                levels = new LinkedList<>();
                if (!fringe.isEmpty()) {
                    fringe.add(null);
                }
            } else {
                levels.offer(curr);
                if (curr.left != null) {
                    fringe.offer(curr.left);
                }
                if (curr.right != null) {
                    fringe.offer(curr.right);
                }
            }
        }
        return result;
    }
}
