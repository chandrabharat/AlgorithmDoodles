import java.util.ArrayList;

class MyLinkedList {

    private class myListNode {
        int val;
        myListNode next;
        private myListNode(int val, myListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /** Initialize your data structure here. */
    int pos;
    ArrayList<myListNode> dataStructure;
    public MyLinkedList() {
        dataStructure = new ArrayList<>();
        pos = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        return index >= 0 && index < dataStructure.size()
                ? dataStructure.get(index).val : -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        myListNode oldHead =  dataStructure.size() > 0 ? dataStructure.get(0) : null;
        dataStructure.add(0, new myListNode(val, oldHead));
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        dataStructure.get(dataStructure.size() - 1).next
                = new myListNode(val, null);
        dataStructure.add(new myListNode(val, null));
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index >= 0 && index <= dataStructure.size()) {
            if (index == 0) {
                addAtHead(val);
            }   else if (index == dataStructure.size()) {
                addAtTail(val);
            }   else {
                myListNode newListNode = new myListNode(val, dataStructure.get(index));
                dataStructure.add(index, newListNode);
                dataStructure.get(index - 1).next = newListNode;
            }
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= 0 && index < dataStructure.size()) {
            if (index == 0) {
                dataStructure.remove(index);
            } else if (index == dataStructure.size() - 1) {
                dataStructure.get(dataStructure.size() - 2).next = null;
                dataStructure.remove(index);
            } else {
                dataStructure.get(index - 1).next = dataStructure.get(index + 1);
                dataStructure.remove(index);
            }
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */