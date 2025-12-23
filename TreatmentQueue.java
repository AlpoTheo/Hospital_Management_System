// Queue for treatment requests (FIFO)
public class TreatmentQueue {
    
    private TreatmentNode front;
    private TreatmentNode rear;
    private int size;
    
    public TreatmentQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    // Add to rear
    public void enqueue(TreatmentRequest request) {
        TreatmentNode newNode = new TreatmentNode(request);
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }
    
    // Remove from front
    public TreatmentRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        TreatmentRequest request = front.getRequest();
        front = front.getNext();
        
        // If queue becomes empty, rear must also be null
        if (front == null) {
            rear = null;
        }
        
        size--;
        return request;
    }
    
    public int size() {
        return size;
    }
    
    // Print queue from front to rear
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        
        TreatmentNode current = front;
        int count = 1;
        
        // Header with fixed widths
        System.out.printf("%-5s %-12s %-10s%n", "Pos", "Patient ID", "Priority");
        System.out.printf("%-5s %-12s %-10s%n", "---", "----------", "--------");
        
        while (current != null) {
            TreatmentRequest req = current.getRequest();
            String priorityStr = req.isPriority() ? "YES" : "No";
            
            System.out.printf("%-5d %-12d %-10s%n", count, req.getPatientId(), priorityStr);
            
            current = current.getNext();
            count++;
        }
        System.out.println("Total Requests: " + size);
    }
    
    public boolean isEmpty() {
        return front == null;
    }
    
    // Return front request without removing
    public TreatmentRequest peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getRequest();
    }
    
    public TreatmentNode getFront() {
        return front;
    }
}