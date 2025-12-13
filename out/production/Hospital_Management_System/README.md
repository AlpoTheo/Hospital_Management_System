# Hospital Patient Management System

This project is a Java-based simulation of a hospital management system developed for the **CENG 201 - Data Structures** course. 

The main goal of this project is to implement core data structures (**Linked List, Queue, Stack**) manually from scratch without using Java's built-in `java.util` collections (like ArrayList, LinkedList, etc.).

## 📂 Project Structure

The system simulates a patient's journey through admission, treatment, and discharge using the following custom structures:

* **PatientList (Linked List):** A singly linked list to manage active patients. It handles insertions, deletions, and searching manually using node references.
* **TreatmentQueue (FIFO Queue):** Manages treatment requests. It supports a priority mechanism where critical patients are processed before normal requests.
* **DischargeStack (LIFO Stack):** Stores records of discharged patients. The most recently discharged patient is at the top of the stack.
* **HospitalSystem:** The main controller class that integrates the list, queue, and stack. It also includes sorting algorithms.

## 🚀 Features & Algorithms

* **Custom Data Structures:** Implementation of `Node` classes and manual pointer management (`next`, `head`, `tail`, `top`).
* **Priority Handling:** The queue logic checks for priority flags to determine the order of treatment.
* **Sorting Algorithms:**
    * **Merge Sort:** Implemented recursively to sort patients by severity (critical condition first).
    * **Bubble Sort:** Added as an alternative sorting method.
* **Complexity Management:**
    * Stack Push/Pop: **O(1)**
    * Queue Enqueue/Dequeue: **O(1)**
    * List Search: **O(n)**

## 🛠️ How to Run

1.  Clone the repository:
    ```bash
    git clone [https://github.com/alpdoruk/Hospital_Management_System.git](https://github.com/alpdoruk/Hospital_Management_System.git)
    ```
2.  Open the project in IntelliJ IDEA (or any Java IDE).
3.  Run the `Main.java` file.

The `Main` class includes comprehensive test scenarios (Task 1 to Task 4) that verify the functionality of all data structures.

## 👤 Author

**Alp Doruk Şengün**
*Computer Engineering Student*

