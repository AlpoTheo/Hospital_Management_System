// Stores discharge event data
public class DischargeRecord {
    
    private int patID; // Patient ID
    private long time; // Time of discharge (timestamp)
    
    public DischargeRecord(int patID) {
        this.patID = patID;
        this.time = System.currentTimeMillis();
    }
    
    public DischargeRecord(int patID, long time) {
        this.patID = patID;
        this.time = time;
    }
    
    public int getPatID() {
        return patID;
    }
    
    public long getTime() {
        return time;
    }
    
    public void setPatID(int patID) {
        this.patID = patID;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "Record [ID=" + patID + ", Time=" + time + "]";
    }
}