// I created this class to store basic information about each patient
// Every patient has an id, name, how serious their condition is, and their age
public class Patient {

    // these are the main attributes I need for each patient
    private int patientId;
    private String patientName;
    private int severityLevel;  // goes from 1 to 10, higher means more serious
    private int patientAge;

    // this constructor takes all the info when creating a new patient
    public Patient(int patientId, String patientName, int severityLevel, int patientAge) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.severityLevel = severityLevel;
        this.patientAge = patientAge;
    }

    // I need these getters so other classes can read the patient info
    public int getId() {
        return patientId;
    }

    public String getName() {
        return patientName;
    }

    public int getSeverity() {
        return severityLevel;
    }

    public int getAge() {
        return patientAge;
    }

    // setters in case I need to update something later
    public void setId(int newId) {
        this.patientId = newId;
    }

    public void setName(String newName) {
        this.patientName = newName;
    }

    public void setSeverity(int newSeverity) {
        this.severityLevel = newSeverity;
    }

    public void setAge(int newAge) {
        this.patientAge = newAge;
    }

    // this helps me see the patient info when debugging
    @Override
    public String toString() {
        String result = "Patient [ID=" + patientId + ", Name=" + patientName +
                ", Severity=" + severityLevel + ", Age=" + patientAge + "]";
        return result;
    }
}
