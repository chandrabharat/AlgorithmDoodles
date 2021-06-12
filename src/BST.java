public class BST {
    int val;
    BST left;
    BST right;
    public BST(int val) {
        this.val = val;
    }

    public BST(int val, BST left, BST right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void addNode(int toAdd) {
        if (val == toAdd) {
            return;
        } else if (val > toAdd && left == null) {
            left = new BST(toAdd);
        } else if (val > toAdd && left != null) {
            left.addNode(toAdd);
        } else if (val < toAdd && right == null) {
            right = new BST(toAdd);
        } else {
            right.addNode(toAdd);
        }
    }
}
