// This is a wrapper class that holds a Patient and points to the next node
// I use this to build the linked list structure
public class PatientNode {

    // the actual patient data stored in this node
    private Patient storedPatient;
    // reference to the next node in the list (null if this is the last one)
    private PatientNode linkToNextNode;

    // when I create a node, I give it a patient and set next to null
    public PatientNode(Patient storedPatient) {
        this.storedPatient = storedPatient;
        this.linkToNextNode = null;  // no next node yet
    }

    // I need this to access the patient inside
    public Patient getPatient() {
        return storedPatient;
    }

    // this returns the reference to next node so I can traverse the list
    public PatientNode getNext() {
        return linkToNextNode;
    }

    // setter for the patient if I need to replace it
    public void setPatient(Patient newPatient) {
        this.storedPatient = newPatient;
    }

    // I use this to connect nodes together
    public void setNext(PatientNode nextNodeReference) {
        this.linkToNextNode = nextNodeReference;
    }
}
