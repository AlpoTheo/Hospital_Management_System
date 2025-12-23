// LIFO Stack implementation
public class DischargeStack {
    
    private DischargeNode top;
    private int count;
    
    public DischargeStack() {
        this.top = null;
        this.count = 0;
    }
    
    // push: add to the top
    public void push(DischargeRecord rec) {
        DischargeNode node = new DischargeNode(rec);
        
        if (top == null) {
            top = node;
        } else {
            node.setNext(top); // new node points to old top
            top = node;        // update top
        }
        count++;
    }
    
    // pop: remove from top
    public DischargeRecord pop() {
        if (top == null) return null;
        
        DischargeRecord temp = top.getData();
        top = top.getNext(); // move top down
        count--;
        
        return temp;
    }
    
    // peek: see top element
    public DischargeRecord peek() {
        if (top == null) return null;
        return top.getData();
    }
    
    public void printStack() {
        if (top == null) {
            System.out.println("Stack is empty");
            return;
        }
        
        // Header with fixed column widths
        System.out.printf("%-12s %-18s%n", "Patient ID", "Time");
        System.out.printf("%-12s %-18s%n", "----------", "----------------");
        
        DischargeNode temp = top;
        while (temp != null) {
            System.out.printf("%-12d %-18d%n", temp.getData().getPatID(), temp.getData().getTime());
            temp = temp.getNext();
        }
    }
    
    public int getSize() {
        return count;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
}