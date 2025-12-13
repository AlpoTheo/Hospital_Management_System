public class Main {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   HOSPITAL MANAGEMENT SYSTEM - DEMO");
        System.out.println("   Student: Alp Doruk Sengun");
        System.out.println("==========================================\n");

        // Execute tasks sequentially
        testTask1();
        testTask2();
        testTask3();
        testTask4();
    }

    // TASK 1: Linked List Operations
    // Requirement: Add 5 patients, remove 1, search 1.
    public static void testTask1() {
        System.out.println("--- TASK 1: Patient List Tests ---");
        PatientList list = new PatientList();

        // 1. Add 5 patients
        list.addPatient(new Patient(101, "Ali Yilmaz", 7, 25));
        list.addPatient(new Patient(102, "Ayse Demir", 3, 30));
        list.addPatient(new Patient(103, "Mehmet Kaya", 9, 65));
        list.addPatient(new Patient(104, "Fatma Sahin", 5, 40));
        list.addPatient(new Patient(105, "Mustafa Celik", 8, 55));

        System.out.println("Step 1: Added 5 patients");
        list.printList();

        // 2. Remove patient with ID 102
        System.out.println("\nStep 2: Removing patient 102...");
        boolean removed = list.removePatient(102);
        System.out.println(" > Remove status: " + removed);

        // 3. Search for patient 103
        System.out.println("\nStep 3: Searching for ID 103...");
        Patient found = list.findPatient(103);
        if(found != null) {
            System.out.println(" > Found: " + found.getName() + " (Severity: " + found.getSeverity() + ")");
        } else {
            System.out.println(" > Not found.");
        }

        System.out.println("\n[Task 1 Final List]");
        list.printList();
        System.out.println("------------------------------------------\n");
    }

    // TASK 2: Queue Operations
    // Requirement: Add 8 requests, process 3.
    public static void testTask2() {
        System.out.println("--- TASK 2: Treatment Queue Tests ---");
        TreatmentQueue queue = new TreatmentQueue();

        // 1. Add 8 requests
        // IDs: 201-208. Mixed priority.
        queue.enqueue(new TreatmentRequest(201, true));
        queue.enqueue(new TreatmentRequest(202, false));
        queue.enqueue(new TreatmentRequest(203, true));
        queue.enqueue(new TreatmentRequest(204, false));
        queue.enqueue(new TreatmentRequest(205, false));
        queue.enqueue(new TreatmentRequest(206, true));
        queue.enqueue(new TreatmentRequest(207, false));
        queue.enqueue(new TreatmentRequest(208, true));

        System.out.println("Step 1: Queue filled with 8 requests");
        queue.printQueue();

        // 2. Process (Dequeue) 3 requests
        System.out.println("\nStep 2: Processing next 3 requests...");
        System.out.println(" > Processed: " + queue.dequeue());
        System.out.println(" > Processed: " + queue.dequeue());
        System.out.println(" > Processed: " + queue.dequeue());

        System.out.println("\n[Task 2 Remaining Queue]");
        queue.printQueue();
        System.out.println("------------------------------------------\n");
    }

    // TASK 3: Stack Operations
    // Requirement: Add 5 records, pop 2.
    public static void testTask3() {
        System.out.println("--- TASK 3: Discharge Stack Tests ---");
        DischargeStack stack = new DischargeStack();

        // 1. Add 5 discharge records
        stack.push(new DischargeRecord(301));
        stack.push(new DischargeRecord(302));
        stack.push(new DischargeRecord(303));
        stack.push(new DischargeRecord(304));
        stack.push(new DischargeRecord(305));

        System.out.println("Step 1: Stack state (5 records)");
        stack.printStack();

        // 2. Pop top 2 records
        System.out.println("\nStep 2: Popping top 2 records...");
        System.out.println(" > Popped: " + stack.pop());
        System.out.println(" > Popped: " + stack.pop());

        System.out.println("\n[Task 3 Final Stack]");
        stack.printStack();
        System.out.println("------------------------------------------\n");
    }

    // TASK 4: Integrated System
    // Requirement: 10 patients, 5 normal/3 priority requests, 2 manual discharges.
    // Process 4 requests (priority first), then sort.
    public static void testTask4() {
        System.out.println("--- TASK 4: Full System Integration ---");
        HospitalSystem hs = new HospitalSystem();

        // 1. Add 10 Patients
        System.out.println("Step 1: Admitting 10 Patients...");
        hs.addPatient(new Patient(1, "Ahmet", 5, 20));
        hs.addPatient(new Patient(2, "Zeynep", 9, 70));
        hs.addPatient(new Patient(3, "Can", 2, 25));
        hs.addPatient(new Patient(4, "Elif", 8, 50));
        hs.addPatient(new Patient(5, "Cem", 4, 33));
        hs.addPatient(new Patient(6, "Deniz", 7, 29));
        hs.addPatient(new Patient(7, "Ege", 1, 19));
        hs.addPatient(new Patient(8, "Bora", 6, 45));
        hs.addPatient(new Patient(9, "Selin", 10, 80));
        hs.addPatient(new Patient(10, "Kaan", 3, 22));

        // 2. Add Treatment Requests (5 Normal, 3 Priority)
        System.out.println("\nStep 2: Creating Treatment Requests...");

        // Priority Requests (Emergency)
        hs.addTreatmentRequest(new TreatmentRequest(2, true)); // Zeynep
        hs.addTreatmentRequest(new TreatmentRequest(9, true)); // Selin
        hs.addTreatmentRequest(new TreatmentRequest(4, true)); // Elif

        // Normal Requests
        hs.addTreatmentRequest(new TreatmentRequest(1, false));
        hs.addTreatmentRequest(new TreatmentRequest(3, false));
        hs.addTreatmentRequest(new TreatmentRequest(5, false));
        hs.addTreatmentRequest(new TreatmentRequest(6, false));
        hs.addTreatmentRequest(new TreatmentRequest(7, false));

        // Add 2 manual discharge records (as per requirements)
        hs.addDischargeRecord(new DischargeRecord(99));
        hs.addDischargeRecord(new DischargeRecord(98));

        System.out.println("\n[System State Before Processing]");
        hs.printSystemState();

        // 3. Process 4 Requests
        // Expectation: Priority queue should be emptied first.
        System.out.println("\nStep 3: Processing next 4 treatments...");
        for(int i=0; i<4; i++) {
            TreatmentRequest req = hs.processTreatment();
            String pType = req.isPriority() ? "PRIORITY" : "Normal";
            System.out.println(" > Treated: Patient " + req.getPatientId() + " [" + pType + "]");
        }

        // 4. Sorting Test
        System.out.println("\nStep 4: Testing Merge Sort (By Severity)...");
        Patient[] sorted = hs.sortPatientsBySeverity();
        for(Patient p : sorted) {
            if(p != null)
                System.out.println(" - Severity " + p.getSeverity() + ": " + p.getName());
        }

        System.out.println("\n[Final System State]");
        hs.printSystemState();
        System.out.println("==========================================");
    }
}