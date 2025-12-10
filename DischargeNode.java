// Node for the stack
public class DischargeNode {
    
    private DischargeRecord data;
    private DischargeNode next;
    
    public DischargeNode(DischargeRecord data) {
        this.data = data;
        this.next = null;
    }
    
    public DischargeRecord getData() {
        return data;
    }
    
    public DischargeNode getNext() {
        return next;
    }
    
    public void setData(DischargeRecord data) {
        this.data = data;
    }
    
    public void setNext(DischargeNode next) {
        this.next = next;
    }
}