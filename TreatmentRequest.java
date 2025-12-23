// This class represents a treatment request in the queue
// I store which patient wants treatment and if its an emergency or not
public class TreatmentRequest {

    private int requestPatientId;
    private boolean emergencyFlag;

    // simple constructor when its not an emergency
    public TreatmentRequest(int requestPatientId) {
        this.requestPatientId = requestPatientId;
        this.emergencyFlag = false;  // default is not emergency
    }

    // this constructor lets me specify if its emergency
    public TreatmentRequest(int requestPatientId, boolean emergencyFlag) {
        this.requestPatientId = requestPatientId;
        this.emergencyFlag = emergencyFlag;
    }

    // I need this to know which patient the request belongs to
    public int getPatientId() {
        return requestPatientId;
    }

    // checking if this is an emergency case
    public boolean isPriority() {
        return emergencyFlag;
    }

    // in case I need to change the priority later
    public void setPriority(boolean newPriority) {
        emergencyFlag = newPriority;
    }

    // for printing and debugging purposes
    @Override
    public String toString() {
        String output = "Request [Patient=" + requestPatientId + ", Priority=" + emergencyFlag + "]";
        return output;
    }
}
