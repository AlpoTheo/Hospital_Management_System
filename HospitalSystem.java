import java.util.HashMap;

// This is the main class that connects all the data structures together
// I decided to use two queues for priority handling instead of one, explained below
public class HospitalSystem {
    
    // I keep all active patients here
    private PatientList patientsList; 
    
    // I use two separate queues for the priority system
    // this way I dont have to search through one queue every time
    private TreatmentQueue regularQueue; 
    private TreatmentQueue emergencyQueue;
    
    // discharge records go here, most recent on top
    private DischargeStack dischargeHistory; 
    
    // hashmap for O(1) lookup when I need to find a patient fast
    private HashMap<Integer, Patient> patientLookupTable; 
    
    public HospitalSystem() {
        // just initializing everything empty
        this.patientsList = new PatientList();
        this.regularQueue = new TreatmentQueue();
        this.emergencyQueue = new TreatmentQueue();
        this.dischargeHistory = new DischargeStack();
        this.patientLookupTable = new HashMap<>();
    }
    
    // when a new patient comes in, I add them to both the list and the hashmap
    public void addPatient(Patient p) {
        patientsList.addPatient(p);
        patientLookupTable.put(p.getId(), p);
        System.out.println("New patient: " + p.getName());
    }
    
    // this uses the hashmap so its O(1) instead of searching the whole list
    public Patient lookupPatient(int id) {
        boolean exists = patientLookupTable.containsKey(id);
        if(exists) {
            return patientLookupTable.get(id);
        }
        return null;
    }
    
    // I check the priority flag and put them in the right queue
    public void addTreatmentRequest(TreatmentRequest r) {
        boolean isEmergency = r.isPriority();
        if (isEmergency) {
            emergencyQueue.enqueue(r);
            System.out.println("Priority request: " + r.getPatientId());
        } else {
            regularQueue.enqueue(r);
            System.out.println("Normal request: " + r.getPatientId());
        }
    }
    
    // this is where the two queue trick pays off
    // I always check emergency first, if its empty then I go to regular queue
    // both operations are O(1) this way
    public TreatmentRequest processTreatment() {
        TreatmentRequest currentRequest = null;
        
        // checking if there are emergency patients waiting
        boolean hasEmergency = !emergencyQueue.isEmpty();
        boolean hasRegular = !regularQueue.isEmpty();
        
        if (hasEmergency) {
            // emergency goes first no matter what
            currentRequest = emergencyQueue.dequeue();
        } else if (hasRegular) {
            // no emergency so take from regular queue
            currentRequest = regularQueue.dequeue();
        }
        
        // if we got someone, process them
        if (currentRequest != null) {
            // create discharge record and push to stack
            int patientId = currentRequest.getPatientId();
            DischargeRecord newRecord = new DischargeRecord(patientId);
            dischargeHistory.push(newRecord);
            
            // remove from active patients list and hashmap
            patientsList.removePatient(patientId);
            patientLookupTable.remove(patientId);
            
            return currentRequest;
        }
        
        return null;
    }
    
    // sometimes we need to add discharge records manually
    public void addDischargeRecord(DischargeRecord rec) {
        dischargeHistory.push(rec);
    }
    
    // just returns the top of the stack without removing
    public DischargeRecord peekLastDischarge() {
        return dischargeHistory.peek();
    }
    
    // ========== SORTING METHODS ==========
    // I implemented both merge sort and bubble sort
    // merge sort is O(n log n) which is better for large data
    // bubble sort is O(n^2) but easier to understand
    
    public Patient[] sortPatientsBySeverity() {
        int totalPatients = patientsList.getSize();
        Patient[] patientArray = new Patient[totalPatients];
        
        // first I need to copy all patients from linked list to array
        // because sorting is easier with arrays
        PatientNode currentNode = patientsList.getHead();
        int index = 0;
        while (currentNode != null) {
            patientArray[index] = currentNode.getPatient();
            currentNode = currentNode.getNext();
            index = index + 1;
        }
        
        // now run merge sort on the array
        // I pass 0 as start and totalPatients-1 as end
        runMergeSortRecursive(patientArray, 0, totalPatients - 1);
        return patientArray;
    }
    
    // this is the recursive part of merge sort
    // it keeps splitting the array until we have single elements
    // then merges them back in sorted order
    private void runMergeSortRecursive(Patient[] patientArray, int startIndex, int endIndex) {
        // base case: if start >= end, nothing to sort
        boolean shouldContinue = startIndex < endIndex;
        if (shouldContinue) {
            // find middle point to split
            int middlePoint = (startIndex + endIndex) / 2;
            
            // sort left half first
            runMergeSortRecursive(patientArray, startIndex, middlePoint);
            // then sort right half
            runMergeSortRecursive(patientArray, middlePoint + 1, endIndex);
            // finally merge the two sorted halves
            mergeTheTwoHalves(patientArray, startIndex, middlePoint, endIndex);
        }
    }
    
    // this method combines two sorted portions into one sorted portion
    // its the tricky part of merge sort
    private void mergeTheTwoHalves(Patient[] patientArray, int startIndex, int middlePoint, int endIndex) {
        // calculate sizes of left and right portions
        int leftSideSize = middlePoint - startIndex + 1;
        int rightSideSize = endIndex - middlePoint;
        
        // create temporary arrays to hold the two halves
        Patient[] leftPortion = new Patient[leftSideSize];
        Patient[] rightPortion = new Patient[rightSideSize];
        
        // copy data to left temporary array
        int copyIndex = 0;
        while (copyIndex < leftSideSize) {
            leftPortion[copyIndex] = patientArray[startIndex + copyIndex];
            copyIndex = copyIndex + 1;
        }
        
        // copy data to right temporary array
        copyIndex = 0;
        while (copyIndex < rightSideSize) {
            rightPortion[copyIndex] = patientArray[middlePoint + 1 + copyIndex];
            copyIndex = copyIndex + 1;
        }
        
        // now merge them back
        // I use three counters: one for left, one for right, one for main array
        int leftCounter = 0;
        int rightCounter = 0;
        int mainCounter = startIndex;
        
        // compare elements from both sides and pick the bigger one
        // because I want descending order (highest severity first)
        while (leftCounter < leftSideSize && rightCounter < rightSideSize) {
            int leftSeverity = leftPortion[leftCounter].getSeverity();
            int rightSeverity = rightPortion[rightCounter].getSeverity();
            
            // I want higher severity to come first, so I check >=
            if (leftSeverity >= rightSeverity) {
                patientArray[mainCounter] = leftPortion[leftCounter];
                leftCounter = leftCounter + 1;
            } else {
                patientArray[mainCounter] = rightPortion[rightCounter];
                rightCounter = rightCounter + 1;
            }
            mainCounter = mainCounter + 1;
        }
        
        // if left side has remaining elements, copy them
        while (leftCounter < leftSideSize) {
            patientArray[mainCounter] = leftPortion[leftCounter];
            leftCounter = leftCounter + 1;
            mainCounter = mainCounter + 1;
        }
        
        // if right side has remaining elements, copy them
        while (rightCounter < rightSideSize) {
            patientArray[mainCounter] = rightPortion[rightCounter];
            rightCounter = rightCounter + 1;
            mainCounter = mainCounter + 1;
        }
    }
    
    // bubble sort as alternative - simpler but slower O(n^2)
    // I compare adjacent elements and swap if needed
    public Patient[] bubbleSortBySeverity() {
        int totalSize = patientsList.getSize();
        Patient[] patientArray = new Patient[totalSize];
        
        // copy from linked list to array first
        PatientNode walker = patientsList.getHead();
        int position = 0;
        while(walker != null) {
            patientArray[position] = walker.getPatient();
            position = position + 1;
            walker = walker.getNext();
        }
        
        // now do the bubble sort
        // outer loop for passes
        int passNumber = 0;
        while (passNumber < totalSize - 1) {
            // inner loop for comparisons
            int compareIndex = 0;
            while (compareIndex < totalSize - passNumber - 1) {
                // get severity values to compare
                int currentSeverity = patientArray[compareIndex].getSeverity();
                int nextSeverity = patientArray[compareIndex + 1].getSeverity();
                
                // if current is less than next, swap them (for descending order)
                if (currentSeverity < nextSeverity) {
                    // swap using a holder variable
                    Patient holder = patientArray[compareIndex];
                    patientArray[compareIndex] = patientArray[compareIndex + 1];
                    patientArray[compareIndex + 1] = holder;
                }
                compareIndex = compareIndex + 1;
            }
            passNumber = passNumber + 1;
        }
        return patientArray;
    }
    
    // prints everything so I can see whats going on
    public void printSystemState() {
        System.out.println("\n--- SYSTEM STATUS ---");
        
        System.out.println("Patients in system:");
        patientsList.printList();
        
        System.out.println("\nPriority Queue:");
        emergencyQueue.printQueue();
        
        System.out.println("\nNormal Queue:");
        regularQueue.printQueue();
        
        System.out.println("\nDischarged:");
        dischargeHistory.printStack();
        
        System.out.println("---------------------");
    }
    
    // getters so Main.java can access stuff if needed
    public PatientList getPatientList() { return patientsList; }
    public TreatmentQueue getPriorityQueue() { return emergencyQueue; }
    public TreatmentQueue getNormalQueue() { return regularQueue; }
    public DischargeStack getDischargeStack() { return dischargeHistory; }
    public HashMap<Integer, Patient> getPatientMap() { return patientLookupTable; }
}
