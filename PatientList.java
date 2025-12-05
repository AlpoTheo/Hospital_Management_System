// Custom linked list for managing patients
public class PatientList {
    
    private PatientNode head;
    private int size;
    
    public PatientList() {
        this.head = null;
        this.size = 0;
    }
    
    // Adds patient to end of list
    public void addPatient(Patient p) {
        PatientNode newNode = new PatientNode(p);
        
        // If list is empty, new node is the head
        if (head == null) {
            head = newNode;
        } else {
            // Traverse to the last node
            PatientNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // Link last node to new node
            current.setNext(newNode);
        }
        size++;
    }
    
    // Removes patient by id
    public boolean removePatient(int id) {
        if (head == null) {
            return false; // List is empty
        }
        
        // Case 1: Head is the target
        if (head.getPatient().getId() == id) {
            head = head.getNext();
            size--;
            return true;
        }
        
        // Case 2: Target is somewhere else
        PatientNode current = head;
        while (current.getNext() != null) {
            // Check the next node to see if it's the target
            if (current.getNext().getPatient().getId() == id) {
                // Skip the target node
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        
        return false; // Not found
    }
    
    // Finds patient by id
    public Patient findPatient(int id) {
        PatientNode current = head;
        
        while (current != null) {
            if (current.getPatient().getId() == id) {
                return current.getPatient();
            }
            current = current.getNext();
        }
        
        return null; // Not found
    }
    
    // Prints all patients
    public void printList() {
        if (head == null) {
            System.out.println("Patient List is empty.");
            return;
        }
        
        System.out.println("ID\tName\t\tSeverity\tAge");
        System.out.println("--\t----\t\t--------\t---");
        
        PatientNode current = head;
        while (current != null) {
            Patient p = current.getPatient();
            System.out.println(p.getId() + "\t" + p.getName() + "\t" + p.getSeverity() + "\t\t" + p.getAge());
            current = current.getNext();
        }
        System.out.println("Total Patients: " + size);
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public PatientNode getHead() {
        return head;
    }
}