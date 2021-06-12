import java.util.ArrayList;
import java.util.Arrays;

class BrowserHistory {

    ArrayList<String> history;
    int pos;
    public BrowserHistory(String homepage) {
        history = new ArrayList<>(Arrays.asList(homepage));
        pos = 0;
    }

    public void visit(String url) {
        while (pos < history.size() - 1) {
            history.remove(history.size() - 1);
        }
        history.add(url);
        pos += 1;
    }

    public String back(int steps) {
        pos = pos - steps >= 0 ? pos - steps : 0;
        return history.get(pos);
    }

    public String forward(int steps) {
        pos = pos + steps < history.size() ? pos + steps : history.size() - 1;
        return history.get(pos);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */