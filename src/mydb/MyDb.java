package mydb;

import connector.Connector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MyDb {
    private Connector connector;

    public MyDb(String url, Integer port, String dbname, String username, String passwd) throws SQLException,
            ClassNotFoundException {
        connector = new Connector(url, port, dbname, username, passwd);
    }

    public void AddStudent(Student student) throws SQLException {
        String add_student_sql = "insert into s057(sno,sname,sex,bdate,height,dorm) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connector.conn.prepareStatement(add_student_sql);
        preparedStatement.setInt(1, student.sno);
        preparedStatement.setString(2, student.sname);
        preparedStatement.setString(3, student.sex);
        preparedStatement.setDate(4, (Date) student.bdate);
        preparedStatement.setDouble(5, student.height);
        preparedStatement.setString(6, student.dorm);

        preparedStatement.executeUpdate();
    }

    public List<Student> GetAllStudents() throws SQLException, ParseException {
        ResultSet result = connector.ExecSql("select * from s057");
        List<Student> res = new ArrayList<>();

        while (result.next()) {
            Integer sno = result.getInt("sno");
            String name = result.getString("sname");
            String sex = result.getString("sex");
            Date bdate = result.getDate("bdate");
            double height = result.getDouble("height");
            String dorm = result.getString("dorm");
            Student student = new Student(sno, name, sex, bdate.toString(), height, dorm);
            res.add(student);
        }

        return res;
    }

    public void AddCourse(Course course) throws SQLException {
        String add_course_sql = "insert into c057(cno,cname,peroid, credit, teacher) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connector.conn.prepareStatement(add_course_sql);
        preparedStatement.setString(1, course.cno);
        preparedStatement.setString(2, course.cname);
        preparedStatement.setInt(3, course.peroid);
        preparedStatement.setDouble(4, course.credit);
        preparedStatement.setString(5, course.teacher);

        preparedStatement.executeUpdate();
    }

    public List<Course> GetAllCourses() throws SQLException {
        ResultSet result = connector.ExecSql("select * from c057");
        List<Course> res = new ArrayList<>();

        while (result.next()) {
            String cno = result.getString("cno");
            String cname = result.getString("cname");
            Integer peroid = result.getInt("peroid");
            double credit = result.getDouble("credit");
            String teacher = result.getString("teacher");
            Course course = new Course(cno, cname, peroid, credit, teacher);
            res.add(course);
        }

        return res;
    }

    public List<Grade> GetAllGrades() throws SQLException {
        ResultSet result = connector.ExecSql("select * from sc057");
        List<Grade> res = new ArrayList<>();

        while (result.next()) {
            Integer sno = result.getInt("sno");
            String cno = result.getString("cno");
            double grade = result.getDouble("grade");

            Grade g = new Grade(sno, cno, grade);
            res.add(g);
        }

        return res;
    }

    public void AddGrade(Grade grade) throws SQLException {
        String add_grade_sql = "insert into sc057(sno, cno, grade) values (?,?,?)";
        PreparedStatement preparedStatement = connector.conn.prepareStatement(add_grade_sql);
        preparedStatement.setInt(1, grade.sno);
        preparedStatement.setString(2, grade.cno);
        preparedStatement.setDouble(3, grade.grade);

        preparedStatement.executeUpdate();
    }
}
