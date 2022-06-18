package mydb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    public Integer sno;
    public String sname;
    public String sex;
    public Date bdate;
    public double height;
    public String dorm;

    public Student(Integer sno, String sname, String sex, String bdate, double height, String dorm) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(bdate);
        java.sql.Date datesql = new java.sql.Date(date.getTime());
        this.sno = sno;
        this.sname = sname;
        this.sex = sex;
        this.bdate = datesql;
        this.height = height;
        this.dorm = dorm;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", bdate=" + bdate +
                ", height=" + height +
                ", dorm='" + dorm + '\'' +
                '}';
    }
}
