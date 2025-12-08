// Request object that holds patient ID and priority status
public class TreatmentRequest {

    private int patientId;
    private boolean isPriority;

    public TreatmentRequest(int patientId) {
        this.patientId = patientId;
        this.isPriority = false;
    }

    public TreatmentRequest(int patientId, boolean isPriority) {
        this.patientId = patientId;
        this.isPriority = isPriority;
    }

    public int getPatientId() {
        return patientId;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    @Override
    public String toString() {
        return "Request [Patient=" + patientId + ", Priority=" + isPriority + "]";
    }
}