import java.util.HashMap;

public class RandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node ptr1 = head;
        Node ans = new Node(ptr1.val);
        Node ansTemp = ans;
        ptr1 = ptr1.next;
        while (ptr1 != null) {
            ansTemp.next = new Node(ptr1.val);
            ptr1 = ptr1.next;
            ansTemp = ansTemp.next;
        }
        ansTemp = ans;
        ptr1 = head;
        while (ptr1 != null) {
            Node newPtr1 = ptr1.next;
            ptr1.next = ansTemp;
            map.put(ansTemp, ptr1);
            ptr1 = newPtr1;
            ansTemp = ansTemp.next;
        }
        ansTemp = ans;
        while (ansTemp != null) {
            ansTemp.random = map.get(ansTemp).random.next;
            ansTemp = ansTemp.next;
        }
        return ans;
    }
}
