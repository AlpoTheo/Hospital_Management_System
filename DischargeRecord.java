// This class keeps track of when a patient leaves the hospital
// I save the patient id and the exact time they were discharged
public class DischargeRecord {
    
    private int dischargedPatientId;
    private long dischargeTimestamp;  // I use milliseconds from system clock
    
    // when I create a record, it automatically saves current time
    public DischargeRecord(int dischargedPatientId) {
        this.dischargedPatientId = dischargedPatientId;
        // getting the current time in milliseconds
        this.dischargeTimestamp = System.currentTimeMillis();
    }
    
    // sometimes I might need to set a specific time, so I made this constructor too
    public DischargeRecord(int dischargedPatientId, long dischargeTimestamp) {
        this.dischargedPatientId = dischargedPatientId;
        this.dischargeTimestamp = dischargeTimestamp;
    }
    
    // getter for the patient id
    public int getPatID() {
        return dischargedPatientId;
    }
    
    // getter for discharge time
    public long getTime() {
        return dischargeTimestamp;
    }
    
    // setter for patient id if I need to change it
    public void setPatID(int newPatientId) {
        this.dischargedPatientId = newPatientId;
    }
    
    // setter for time
    public void setTime(long newTimestamp) {
        this.dischargeTimestamp = newTimestamp;
    }
    
    // helps me see the record info when printing
    @Override
    public String toString() {
        String recordInfo = "Record [ID=" + dischargedPatientId + ", Time=" + dischargeTimestamp + "]";
        return recordInfo;
    }
}
