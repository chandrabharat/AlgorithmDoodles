import java.util.Stack;

public class Stacker extends Stack<Integer> {
    Stack<Integer> s2;

    Stacker() {
        s2 = new Stack<>();
    }

    public void push(int num) {
        if (s2.isEmpty()) {
            s2.push(num);
        } else if (num < s2.peek()) {
            s2.push(num);
        }
        super.push(num);
    }

    public Integer pop() {
        int val = super.pop();
        if (val == getMin()) {
            s2.pop();
        }
        return val;
    }

    public int getMin() {
        if (s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }
    }
}
