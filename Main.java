public class Main {
    
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  HOSPITAL MANAGEMENT SYSTEM - TEST RUN");
        System.out.println("===========================================\n");
        
        // Run tests step by step
        testList();
        testQueue();
        testStack();
        testFullSystem();
    }
    
    // Test 1: Linked List
    public static void testList() {
        System.out.println("--- TASK 1: Testing PatientList ---");
        PatientList list = new PatientList();
        
        list.addPatient(new Patient(101, "Ali Yilmaz", 7, 25));
        list.addPatient(new Patient(102, "Ayse Demir", 3, 30));
        list.addPatient(new Patient(103, "Mehmet Kaya", 9, 65));
        
        System.out.println("Added 3 patients:");
        list.printList();
        
        System.out.println("\nRemoving patient 102...");
        boolean isRemoved = list.removePatient(102);
        System.out.println("Removed status: " + isRemoved);
        
        System.out.println("Searching for 103: " + list.findPatient(103));
        
        System.out.println("\nFinal List:");
        list.printList();
        System.out.println("-----------------------------------\n");
    }
    
    // Test 2: Queue
    public static void testQueue() {
        System.out.println("--- TASK 2: Testing TreatmentQueue ---");
        TreatmentQueue q = new TreatmentQueue();
        
        q.enqueue(new TreatmentRequest(201, true)); // priority
        q.enqueue(new TreatmentRequest(202, false));
        q.enqueue(new TreatmentRequest(203, true));
        
        System.out.println("Queue after 3 additions:");
        q.printQueue();
        
        System.out.println("\nProcessing one (Dequeue): " + q.dequeue());
        
        System.out.println("Queue state:");
        q.printQueue();
        System.out.println("-----------------------------------\n");
    }
    
    // Test 3: Stack
    public static void testStack() {
        System.out.println("--- TASK 3: Testing DischargeStack ---");
        DischargeStack s = new DischargeStack();
        
        s.push(new DischargeRecord(301));
        s.push(new DischargeRecord(302));
        
        System.out.println("Stack state:");
        s.printStack();
        
        System.out.println("\nPopping top: " + s.pop());
        
        System.out.println("Stack after pop:");
        s.printStack();
        System.out.println("-----------------------------------\n");
    }
    
    // Test 4: Integrated System
    public static void testFullSystem() {
        System.out.println("--- TASK 4: Full Hospital System ---");
        HospitalSystem hs = new HospitalSystem();
        
        // 1. Add Patients
        hs.addPatient(new Patient(1, "Ahmet", 5, 20));
        hs.addPatient(new Patient(2, "Zeynep", 9, 70)); // Critical
        hs.addPatient(new Patient(3, "Can", 2, 25));
        hs.addPatient(new Patient(4, "Elif", 8, 50));
        
        // 2. Add Requests
        System.out.println("\n[Adding Requests...]");
        hs.addTreatmentRequest(new TreatmentRequest(1, false));
        hs.addTreatmentRequest(new TreatmentRequest(2, true)); // Priority!
        hs.addTreatmentRequest(new TreatmentRequest(4, true)); // Priority!
        
        hs.printSystemState();
        
        // 3. Process Treatments
        System.out.println("\n[Processing Treatments...]");
        System.out.println("Treated: " + hs.processTreatment()); // Should be 2 (Priority)
        System.out.println("Treated: " + hs.processTreatment()); // Should be 4 (Priority)
        
        // 4. Test Sorting (Merge Sort)
        System.out.println("\n[Testing Merge Sort by Severity]");
        Patient[] sorted = hs.sortPatientsBySeverity();
        for(Patient p : sorted) {
            if(p != null) System.out.println("Severity " + p.getSeverity() + " - " + p.getName());
        }
        
        hs.printSystemState();
        System.out.println("-----------------------------------");
    }
}