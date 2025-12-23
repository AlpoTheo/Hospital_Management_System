// Stack implementation for discharge records
// Last In First Out - like browser history or undo button
public class DischargeStack {
    
    // points to the most recent discharge (top of the stack)
    private DischargeNode topOfStack;
    // keeps track of how many records are in the stack
    private int recordCount;
    
    // start with empty stack
    public DischargeStack() {
        this.topOfStack = null;
        this.recordCount = 0;
    }
    
    // push a new record onto the top of the stack
    // this is O(1) because I just update the top pointer
    public void push(DischargeRecord newRecord) {
        // wrap the record in a node
        DischargeNode freshNode = new DischargeNode(newRecord);
        
        boolean stackIsEmpty = (topOfStack == null);
        
        if (stackIsEmpty) {
            // if stack was empty, this becomes the top
            topOfStack = freshNode;
        } else {
            // new node points to old top, then becomes the new top
            freshNode.setNext(topOfStack);
            topOfStack = freshNode;
        }
        
        // one more record in the stack
        recordCount = recordCount + 1;
    }
    
    // removes and returns the top record
    // this is O(1), just moving the top pointer down
    public DischargeRecord pop() {
        boolean stackIsEmpty = (topOfStack == null);
        
        if (stackIsEmpty) {
            return null;
        }
        
        // grab the data from top node
        DischargeRecord removedRecord = topOfStack.getData();
        
        // move top pointer to the node below
        topOfStack = topOfStack.getNext();
        
        // one less record now
        recordCount = recordCount - 1;
        
        return removedRecord;
    }
    
    // look at top record without removing it
    // useful when I just want to check the latest discharge
    public DischargeRecord peek() {
        boolean stackIsEmpty = (topOfStack == null);
        
        if (stackIsEmpty) {
            return null;
        }
        
        return topOfStack.getData();
    }
    
    // prints all records from top to bottom
    public void printStack() {
        boolean stackIsEmpty = (topOfStack == null);
        
        if (stackIsEmpty) {
            System.out.println("Stack is empty");
            return;
        }
        
        // table header
        System.out.printf("%-12s %-18s%n", "Patient ID", "Time");
        System.out.printf("%-12s %-18s%n", "----------", "----------------");
        
        // walk through stack from top to bottom
        DischargeNode printNode = topOfStack;
        
        while (printNode != null) {
            DischargeRecord currentRecord = printNode.getData();
            
            System.out.printf("%-12d %-18d%n", 
                currentRecord.getPatID(), 
                currentRecord.getTime());
            
            printNode = printNode.getNext();
        }
    }
    
    // returns how many records are stored
    public int getSize() {
        return recordCount;
    }
    
    // checks if stack has anything
    public boolean isEmpty() {
        boolean checkEmpty = (topOfStack == null);
        return checkEmpty;
    }
}
