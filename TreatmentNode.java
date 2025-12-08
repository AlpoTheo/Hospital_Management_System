// Node for treatment queue
public class TreatmentNode {

    private TreatmentRequest request;
    private TreatmentNode next;

    public TreatmentNode(TreatmentRequest request) {
        this.request = request;
        this.next = null;
    }

    public TreatmentRequest getRequest() {
        return request;
    }

    public TreatmentNode getNext() {
        return next;
    }

    public void setRequest(TreatmentRequest request) {
        this.request = request;
    }

    public void setNext(TreatmentNode next) {
        this.next = next;
    }
}