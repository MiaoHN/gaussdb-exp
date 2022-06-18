import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import generator.CourseGenerator;
import generator.GradeGenerator;
import generator.StudentGenerator;
import mydb.Course;
import mydb.Grade;
import mydb.MyDb;
import mydb.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, IOException {
        String conf = new String(Files.readAllBytes(Paths.get("src/conf.json")));
        JSONObject object = JSON.parseObject(conf);
        String url = object.getString("url");
        int port = object.getInteger("port");
        String dbname = object.getString("dbname");
        String username = object.getString("username");
        String passwd = object.getString("passwd");

        MyDb mydb = new MyDb(url, port, dbname, username, passwd);
        StudentGenerator studentGenerator = new StudentGenerator(4040001, 5000 - 11);
        CourseGenerator courseGenerator = new CourseGenerator(1000 - 8);
        GradeGenerator gradeGenerator = new GradeGenerator(studentGenerator, courseGenerator, 30000 - 26);
        System.out.println("Generate done");

        List<Student> studentList = studentGenerator.get_students();
        List<Course> courseList = courseGenerator.get_course();
        List<Grade> gradeList = gradeGenerator.get_grade();

        for (Student student : studentList) {
            mydb.AddStudent(student);
        }
        for (Course course : courseList) {
            mydb.AddCourse(course);
        }
        for (Grade grade : gradeList) {
            mydb.AddGrade(grade);
        }
        System.out.println("Insert done.");
    }
}
