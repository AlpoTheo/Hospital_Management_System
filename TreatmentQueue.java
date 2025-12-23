// FIFO Queue implementation for treatment requests
// First person in line gets treated first, makes sense for a hospital
public class TreatmentQueue {
    
    // points to the first person waiting (front of the line)
    private TreatmentNode frontOfLine;
    // points to the last person who joined (back of the line)
    private TreatmentNode backOfLine;
    // how many requests are waiting
    private int requestCount;
    
    // start with empty queue
    public TreatmentQueue() {
        this.frontOfLine = null;
        this.backOfLine = null;
        this.requestCount = 0;
    }
    
    // adds a new request to the back of the queue
    // this is O(1) because I keep track of the back
    public void enqueue(TreatmentRequest newRequest) {
        // wrap the request in a node
        TreatmentNode freshNode = new TreatmentNode(newRequest);
        
        boolean queueIsEmpty = (frontOfLine == null);
        
        if (queueIsEmpty) {
            // if queue was empty, this node is both front and back
            frontOfLine = freshNode;
            backOfLine = freshNode;
        } else {
            // add to the back and update backOfLine pointer
            backOfLine.setNext(freshNode);
            backOfLine = freshNode;
        }
        
        // increase the counter
        requestCount = requestCount + 1;
    }
    
    // removes and returns the request at the front
    // this is O(1) because I just move the front pointer
    public TreatmentRequest dequeue() {
        boolean queueIsEmpty = (frontOfLine == null);
        
        if (queueIsEmpty) {
            return null;
        }
        
        // grab the request from the front node
        TreatmentRequest removedRequest = frontOfLine.getRequest();
        
        // move front pointer to the next person in line
        frontOfLine = frontOfLine.getNext();
        
        // if the queue is now empty, back should also be null
        boolean nowEmpty = (frontOfLine == null);
        if (nowEmpty) {
            backOfLine = null;
        }
        
        // decrease counter
        requestCount = requestCount - 1;
        
        return removedRequest;
    }
    
    // returns how many requests are waiting
    public int size() {
        return requestCount;
    }
    
    // prints everyone in the queue from front to back
    public void printQueue() {
        boolean queueIsEmpty = (frontOfLine == null);
        
        if (queueIsEmpty) {
            System.out.println("Queue is empty.");
            return;
        }
        
        // start at front and walk through
        TreatmentNode printNode = frontOfLine;
        int positionNumber = 1;
        
        // header for the table
        System.out.printf("%-5s %-12s %-10s%n", "Pos", "Patient ID", "Priority");
        System.out.printf("%-5s %-12s %-10s%n", "---", "----------", "--------");
        
        while (printNode != null) {
            TreatmentRequest currentRequest = printNode.getRequest();
            
            // show YES or No for priority
            String priorityText;
            if (currentRequest.isPriority()) {
                priorityText = "YES";
            } else {
                priorityText = "No";
            }
            
            System.out.printf("%-5d %-12d %-10s%n", 
                positionNumber, 
                currentRequest.getPatientId(), 
                priorityText);
            
            printNode = printNode.getNext();
            positionNumber = positionNumber + 1;
        }
        
        System.out.println("Total Requests: " + requestCount);
    }
    
    // quick check if queue has anyone
    public boolean isEmpty() {
        boolean checkEmpty = (frontOfLine == null);
        return checkEmpty;
    }
    
    // lets me see whos at front without removing them
    public TreatmentRequest peek() {
        boolean queueIsEmpty = (frontOfLine == null);
        
        if (queueIsEmpty) {
            return null;
        }
        
        return frontOfLine.getRequest();
    }
    
    // in case I need direct access to the front node
    public TreatmentNode getFront() {
        return frontOfLine;
    }
}
