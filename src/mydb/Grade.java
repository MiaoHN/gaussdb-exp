package mydb;

public class Grade {
    public Integer sno;
    public String cno;
    public double grade;

    public Grade(Integer sno, String cno, double grade) {
        this.sno = sno;
        this.cno = cno;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "sno=" + sno +
                ", cno='" + cno + '\'' +
                ", grade=" + grade +
                '}';
    }
}
