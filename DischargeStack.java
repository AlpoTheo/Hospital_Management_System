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
        
        System.out.println("Patient ID\tTime");
        System.out.println("----------\t----");
        
        DischargeNode temp = top;
        while (temp != null) {
            System.out.println(temp.getData().getPatID() + "\t\t" + temp.getData().getTime());
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