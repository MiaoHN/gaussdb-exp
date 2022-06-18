package generator;

import mydb.Course;
import mydb.Grade;
import mydb.Student;

import java.util.*;

public class GradeGenerator {

    private final List<Grade> _grade = new ArrayList<>();

    private final Random r = new Random();
    Set<String> _gard = new HashSet<>();

    public GradeGenerator(StudentGenerator stu, CourseGenerator cor, int grade_num) {
        for (int i = 0; i < grade_num; ++i) {
            Course course = cor.Random();
            Student student = stu.Random();
            String key = course.cno + student.sno;
            while (_gard.contains(key)) {
                course = cor.Random();
                student = stu.Random();
                key = course.cno + student.sno;
            }
            _gard.add(key);

            Grade grade = new Grade(student.sno, course.cno, RandomGrade());
            _grade.add(grade);
        }
    }

    public List<Grade> get_grade() {
        return _grade;
    }

    private double RandomGrade() {
        int grade = r.nextInt(120) * 5 + 400;
        return (double) grade / 10;
    }
}
