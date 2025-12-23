// Node class specifically for the treatment queue
// It wraps a TreatmentRequest and has a link to the next node
public class TreatmentNode {

    // the treatment request stored in this node
    private TreatmentRequest storedRequest;
    // pointer to whatever node comes after this one
    private TreatmentNode linkToNextNode;

    // constructor - I give it a request and it starts with no next node
    public TreatmentNode(TreatmentRequest storedRequest) {
        this.storedRequest = storedRequest;
        this.linkToNextNode = null;
    }

    // getter for the request data
    public TreatmentRequest getRequest() {
        return storedRequest;
    }

    // I call this when traversing the queue
    public TreatmentNode getNext() {
        return linkToNextNode;
    }

    // setter for request in case I need to update it
    public void setRequest(TreatmentRequest newRequest) {
        this.storedRequest = newRequest;
    }

    // this connects this node to the next one in line
    public void setNext(TreatmentNode nextNodeReference) {
        this.linkToNextNode = nextNodeReference;
    }
}
