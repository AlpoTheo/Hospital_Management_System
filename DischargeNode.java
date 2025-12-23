// Node class for the discharge stack
// Each node holds a DischargeRecord and points to the node below it
public class DischargeNode {
    
    // the discharge record stored here
    private DischargeRecord storedRecord;
    // link to the node underneath (in stack terms, the previous one pushed)
    private DischargeNode linkToNextNode;
    
    // I create a node with a record, starts with no link
    public DischargeNode(DischargeRecord storedRecord) {
        this.storedRecord = storedRecord;
        this.linkToNextNode = null;
    }
    
    // returns the discharge record inside this node
    public DischargeRecord getData() {
        return storedRecord;
    }
    
    // gets the next node reference for stack traversal
    public DischargeNode getNext() {
        return linkToNextNode;
    }
    
    // setter for the record
    public void setData(DischargeRecord newRecord) {
        this.storedRecord = newRecord;
    }
    
    // I use this when pushing new nodes onto the stack
    public void setNext(DischargeNode nextNodeReference) {
        this.linkToNextNode = nextNodeReference;
    }
}
