// Patient class - stores patient info
public class Patient {

    private int id;
    private String name;
    private int severity;  // 1-10 scale
    private int age;

    // constructor
    public Patient(int id, String name, int severity, int age) {
        this.id = id;
        this.name = name;
        this.severity = severity;
        this.age = age;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSeverity() {
        return severity;
    }

    public int getAge() {
        return age;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient [ID=" + id + ", Name=" + name +
                ", Severity=" + severity + ", Age=" + age + "]";
    }
}