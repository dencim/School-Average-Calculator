package sample;

public class StudentRecord {

    private String studentid;
    private float midterm;
    private float assignments;
    private float finalexam;
    private float finalmark;
    private String lettergrade;

    public StudentRecord() {
        this.studentid = "";
        this.midterm = 0.0f;
        this.assignments = 0.0f;
        this.finalexam = 0.0f;
        this.finalmark = 0.0f;
        this.lettergrade = "";
    }

    public StudentRecord(String studentid, float assignments, float midterm, float finalexam) {

        this.studentid = studentid;
        this.midterm = midterm;
        this.assignments = assignments;
        this.finalexam = finalexam;
        finalmark = (float) (0.2 * getAssignments() + 0.3 * getMidterm() + 0.5 * getFinalexam());


        if (getFinalmark() >= 0.0f && getFinalmark() <= 49.0f) {
            lettergrade = "F";
        }
        else if (getFinalmark() >= 50.0f && getFinalmark() <= 59.0f) {
            lettergrade = "D";
        }
        else if (getFinalmark() >= 60.0f && getFinalmark() <= 69.0f) {
            lettergrade = "C";
        }
        else if (getFinalmark() >= 70.0f && getFinalmark() <= 79.0f) {
            lettergrade = "B";
        }
        else if (getFinalmark() >= 80.0f && getFinalmark() <= 100.0f) {
            lettergrade = "A";
        }
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid() {
        this.studentid = studentid;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm() {
        this.midterm = midterm;
    }

    public float getAssignments() {
        return assignments;
    }

    public void setAssignments() {
        this.assignments = assignments;
    }

    public float getFinalexam() {
        return finalexam;
    }

    public void setFinalexam() {
        this.finalexam = finalexam;
    }

    public float getFinalmark() {
        return finalmark;
    }

    public String getLettergrade() {
        return lettergrade;
    }

    public float finalmark() {
        finalmark = (float) (0.2*getAssignments() + 0.3*getMidterm() + 0.5*getFinalexam());
        return finalmark;
    }

    public String lettergrade() {
        if (finalmark <= 0.0f && finalmark <= 49.0f) {
            lettergrade = "F";
            return "F";
        } else if (finalmark >= 50.0f && finalmark <= 59.0f) {
            lettergrade = "D";
            return "D";
        } else if (finalmark >= 60.0f && finalmark <= 69.0f) {
            lettergrade = "C";
            return "C";
        } else if (finalmark >= 70.0f && finalmark <= 79.0f) {
            lettergrade = "B";
            return "B";
        } else if (finalmark >= 80.0f && finalmark <= 100.0f) {
            lettergrade = "A";
            return "A";
        }
        else return "F";
    }
}

