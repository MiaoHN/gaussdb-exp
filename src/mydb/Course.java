package mydb;

public class Course {
    public String cno;
    public String cname;
    public Integer peroid;
    public double credit;
    public String teacher;

    public Course() {
    }

    public Course(String cno, String cname, Integer peroid, double credit, String teacher) {
        this.cno = cno;
        this.cname = cname;
        this.peroid = peroid;
        this.credit = credit;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", peroid=" + peroid +
                ", credit=" + credit +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
