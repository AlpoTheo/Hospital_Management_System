# Hospital Patient Management System

This is my assignment project for **CENG 201 - Data Structures** course. I had to build a hospital simulation using linked list, queue and stack from scratch. The rule was no using java.util stuff like ArrayList or LinkedList, so I wrote everything manually with nodes and pointers.

## What does it do?

Basically simulates a hospital where:
- Patients get admitted (added to a list)
- They wait for treatment (queue system)
- After treatment they get discharged (saved in a stack)

## Files in the project

| File | What it does |
|------|-------------|
| `Patient.java` | Basic patient info - id, name, severity (1-10), age |
| `PatientNode.java` | Node wrapper for linked list |
| `PatientList.java` | My linked list implementation for storing patients |
| `TreatmentRequest.java` | Holds patient id and if they're priority or not |
| `TreatmentNode.java` | Node for the queue |
| `TreatmentQueue.java` | FIFO queue for treatment waiting line |
| `DischargeRecord.java` | Stores discharge info with timestamp |
| `DischargeNode.java` | Node for the stack |
| `DischargeStack.java` | LIFO stack for discharge history |
| `HospitalSystem.java` | Main class that connects everything + sorting |
| `Main.java` | Test scenarios (Task 1-4) |

## Big O Complexity stuff

I tried to make operations as fast as possible but some things are just O(n), cant avoid it.

**PatientList (Linked List):**
- `addPatient()` - O(n) because I loop to the end. Could be O(1) if I kept a tail pointer but didnt bother for this hw
- `removePatient()` - O(n) worst case, have to find the patient first
- `findPatient()` - O(n) same reason, might be at the end

**TreatmentQueue:**
- `enqueue()` - O(1) I keep a rear pointer so adding is instant
- `dequeue()` - O(1) just move the front pointer

**DischargeStack:**
- `push()` - O(1) just update top
- `pop()` - O(1) same thing
- `peek()` - O(1) just return top without removing

**Sorting:**
- Merge Sort - O(n log n) always, thats why I used it
- Bubble Sort - O(n²) added it as backup but its slow for big data

## Priority Queue trick

The assignment wanted priority patients to go first. I could search the queue every time but thats O(n) which is slow. So I used 2 separate queues:
- `priorityQueue` for emergencies
- `normalQueue` for regular patients

When processing, check priority queue first. If empty, go to normal queue. This way both enqueue and dequeue stay O(1). 

If there were like 5 priority levels I would need a heap (O(log n)) but for just 2 levels this works better.

## How to run

1. Clone this repo
2. Open in IntelliJ or any IDE
3. Run `Main.java`

Or from terminal:
```
javac *.java
java Main
```

The Main class runs 4 test scenarios that show everything working.

## Author

**Alp Doruk Sengun**  
CENG 201 - Fall 2025
