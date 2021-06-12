public class CustomStack {
    int size;
    int maxSize;
    int[] values;
    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        values = new int[maxSize];
    }

    public void push(int x) {
        if (size < maxSize) {
            values[size] = x;
            size++;
        }
    }

    public int pop() {
        int result = values[size - 1];
        values[size - 1] = 0;
        size--;
        return result;
    }

    public void increment(int k, int val) {
        int counter = 0;
        while (k != 0 && counter < size) {
            values[counter] = values[counter] + val;
            k--;
            counter++;
            }
        }
    }

