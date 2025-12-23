// My own linked list implementation for storing patients
// I didnt use java.util because the assignment said to build it manually
public class PatientList {
    
    // this points to the first patient in my list
    private PatientNode firstNode;
    // I keep track of how many patients are in the list
    private int patientCount;
    
    // start with an empty list
    public PatientList() {
        this.firstNode = null;
        this.patientCount = 0;
    }
    
    // adds a new patient to the end of the list
    // I have to walk through the whole list to find the end, so its O(n)
    public void addPatient(Patient patientToAdd) {
        // first I wrap the patient in a node
        PatientNode freshNode = new PatientNode(patientToAdd);
        
        // check if the list is empty
        boolean listIsEmpty = (firstNode == null);
        
        if (listIsEmpty) {
            // if empty, new node becomes the first node
            firstNode = freshNode;
        } else {
            // otherwise I need to find the last node
            PatientNode walkingNode = firstNode;
            
            // keep going until I find a node with no next
            while (walkingNode.getNext() != null) {
                walkingNode = walkingNode.getNext();
            }
            
            // now walkingNode is the last one, connect it to freshNode
            walkingNode.setNext(freshNode);
        }
        
        // dont forget to increase the count
        patientCount = patientCount + 1;
    }
    
    // removes a patient by their id
    // returns true if found and removed, false if not found
    public boolean removePatient(int targetId) {
        // cant remove from empty list
        boolean isEmpty = (firstNode == null);
        if (isEmpty) {
            return false;
        }
        
        // special case: the patient to remove is the first one
        int firstPatientId = firstNode.getPatient().getId();
        boolean isFirstPatient = (firstPatientId == targetId);
        
        if (isFirstPatient) {
            // just move the first pointer to second node
            firstNode = firstNode.getNext();
            patientCount = patientCount - 1;
            return true;
        }
        
        // otherwise I need to search through the list
        PatientNode searchNode = firstNode;
        
        // I look at the NEXT node to see if thats the target
        // this way I can update the link when I find it
        while (searchNode.getNext() != null) {
            PatientNode nextOne = searchNode.getNext();
            int nextPatientId = nextOne.getPatient().getId();
            
            boolean foundTarget = (nextPatientId == targetId);
            
            if (foundTarget) {
                // skip over the target node by linking to the one after it
                PatientNode nodeAfterTarget = nextOne.getNext();
                searchNode.setNext(nodeAfterTarget);
                patientCount = patientCount - 1;
                return true;
            }
            
            // move to next node
            searchNode = searchNode.getNext();
        }
        
        // went through whole list without finding it
        return false;
    }
    
    // searches for a patient by id and returns them
    // returns null if not found
    public Patient findPatient(int targetId) {
        PatientNode searchNode = firstNode;
        
        // walk through the list checking each patient
        while (searchNode != null) {
            Patient currentPatient = searchNode.getPatient();
            int currentId = currentPatient.getId();
            
            boolean isMatch = (currentId == targetId);
            
            if (isMatch) {
                return currentPatient;
            }
            
            // move to the next node
            searchNode = searchNode.getNext();
        }
        
        // didnt find anyone with that id
        return null;
    }
    
    // prints all patients in a nice table format
    public void printList() {
        boolean isEmpty = (firstNode == null);
        
        if (isEmpty) {
            System.out.println("Patient List is empty.");
            return;
        }
        
        // print the header row
        System.out.printf("%-6s %-15s %-10s %-5s%n", "ID", "Name", "Severity", "Age");
        System.out.printf("%-6s %-15s %-10s %-5s%n", "---", "----", "--------", "---");
        
        // go through each patient and print their info
        PatientNode printNode = firstNode;
        
        while (printNode != null) {
            Patient patientToPrint = printNode.getPatient();
            
            // using printf for nice column alignment
            System.out.printf("%-6d %-15s %-10d %-5d%n", 
                patientToPrint.getId(), 
                patientToPrint.getName(), 
                patientToPrint.getSeverity(), 
                patientToPrint.getAge());
            
            printNode = printNode.getNext();
        }
        
        System.out.println("Total Patients: " + patientCount);
    }
    
    // returns how many patients are in the list
    public int getSize() {
        return patientCount;
    }
    
    // checks if the list has no patients
    public boolean isEmpty() {
        boolean checkEmpty = (patientCount == 0);
        return checkEmpty;
    }
    
    // I need this so HospitalSystem can access the list for sorting
    public PatientNode getHead() {
        return firstNode;
    }
}
