import java.util.HashMap;

public class HospitalSystem {
    
    private PatientList patients; 
    private TreatmentQueue normalQ; 
    private TreatmentQueue priorityQ;
    private DischargeStack discharged; 
    private HashMap<Integer, Patient> map; 
    
    public HospitalSystem() {
        this.patients = new PatientList();
        this.normalQ = new TreatmentQueue();
        this.priorityQ = new TreatmentQueue();
        this.discharged = new DischargeStack();
        this.map = new HashMap<>();
    }
    
    public void addPatient(Patient p) {
        patients.addPatient(p);
        map.put(p.getId(), p);
        System.out.println("New patient: " + p.getName());
    }
    
    public Patient lookupPatient(int id) {
        if(map.containsKey(id)) {
            return map.get(id);
        }
        return null;
    }
    
    public void addTreatmentRequest(TreatmentRequest r) {
        if (r.isPriority()) {
            priorityQ.enqueue(r);
            System.out.println("Priority request: " + r.getPatientId());
        } else {
            normalQ.enqueue(r);
            System.out.println("Normal request: " + r.getPatientId());
        }
    }
    
    public TreatmentRequest processTreatment() {
        TreatmentRequest req = null;
        
        // check priority first
        if (!priorityQ.isEmpty()) {
            req = priorityQ.dequeue();
        } else if (!normalQ.isEmpty()) {
            req = normalQ.dequeue();
        }
        
        if (req != null) {
            // move to discharge stack
            DischargeRecord rec = new DischargeRecord(req.getPatientId());
            discharged.push(rec);
            
            // cleanup
            patients.removePatient(req.getPatientId());
            map.remove(req.getPatientId());
            
            return req;
        }
        
        return null;
    }
    
    public void addDischargeRecord(DischargeRecord rec) {
        discharged.push(rec);
    }
    
    public DischargeRecord peekLastDischarge() {
        return discharged.peek();
    }
    
    // --- Sorting Logic ---
    
    public Patient[] sortPatientsBySeverity() {
        int n = patients.getSize();
        Patient[] arr = new Patient[n];
        
        // manuel copy
        PatientNode temp = patients.getHead();
        int i = 0;
        while (temp != null) {
            arr[i] = temp.getPatient();
            temp = temp.getNext();
            i++;
        }
        
        mergeSort(arr, 0, n - 1);
        return arr;
    }
    
    // Standard merge sort implementation
    private void mergeSort(Patient[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    
    private void merge(Patient[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        
        Patient[] L = new Patient[n1];
        Patient[] R = new Patient[n2];
        
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];
            
        int i = 0, j = 0;
        int k = l;
        
        while (i < n1 && j < n2) {
            // Sort by severity (descending)
            if (L[i].getSeverity() >= R[j].getSeverity()) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // simple bubble sort
    public Patient[] bubbleSortBySeverity() {
        int size = patients.getSize();
        Patient[] arr = new Patient[size];
        
        PatientNode node = patients.getHead();
        int k = 0;
        while(node != null) {
            arr[k++] = node.getPatient();
            node = node.getNext();
        }
        
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j].getSeverity() < arr[j + 1].getSeverity()) {
                    Patient tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }
    
    public void printSystemState() {
        System.out.println("\n--- SYSTEM STATUS ---");
        
        System.out.println("Patients in system:");
        patients.printList();
        
        System.out.println("\nPriority Queue:");
        priorityQ.printQueue();
        
        System.out.println("\nNormal Queue:");
        normalQ.printQueue();
        
        System.out.println("\nDischarged:");
        discharged.printStack();
        
        System.out.println("---------------------");
    }
    
    // getters for main test
    public PatientList getPatientList() { return patients; }
    public TreatmentQueue getPriorityQueue() { return priorityQ; }
    public TreatmentQueue getNormalQueue() { return normalQ; }
    public DischargeStack getDischargeStack() { return discharged; }
    public HashMap<Integer, Patient> getPatientMap() { return map; }
}