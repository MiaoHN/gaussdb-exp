package generator;

import mydb.Student;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StudentGenerator {

    private static final Random r = new Random();
    static private final List<String> first_name = Arrays.asList("王", "李", "张", "刘", "陈", "杨", "黄", "赵", "吴", "周", "徐",
            "孙", "马", "朱", "胡", "郭", "何", "高", "林", "郑", "谢", "罗", "梁", "宋", "唐", "许", "韩", "冯", "邓", "曹",
            "彭", "曾", "萧", "田", "董", "袁", "潘", "于", "蒋", "蔡", "余", "杜", "叶", "程", "苏", "魏", "吕", "丁", "任",
            "沈", "姚", "卢", "姜", "崔", "钟", "谭", "陆", "汪", "范", "金", "石", "廖", "贾", "夏", "韦", "付", "方", "白",
            "邹", "孟", "熊", "秦", "邱", "江", "尹", "薛", "闫", "段", "雷", "侯", "龙", "史", "陶", "黎", "贺", "顾", "毛",
            "郝", "龚", "邵", "万", "钱", "严", "覃", "武", "戴", "莫", "孔", "向", "汤"
    );
    static private final List<String> girl_name = Arrays.asList(
            "秀英", "桂英", "玉兰", "桂兰", "秀珍", "凤英", "玉珍", "玉英", "兰英", "秀英", "桂英", "英", "玉兰", "萍", "秀兰",
            "玉梅", "红", "敏", "丽", "艳", "敏", "芳", "霞", "红梅", "燕", "红", "英", "静", "丽", "娟", "艳", "燕", "敏",
            "娜", "芳", "丹", "玲", "婷", "婷婷", "丹", "倩", "婷", "欣怡", "婷婷", "悦", "敏", "佳怡", "雪", "颖", "雨欣",
            "欣怡", "梓涵", "诗涵", "梓宣", "子涵", "紫涵", "佳怡", "雨涵", "雨欣", "一诺"
    );
    static private final List<String> boy_name = Arrays.asList(
            "建国", "建华", "国华", "和平", "明", "建平", "军", "平", "志明", "徳明", "军", "勇", "强", "斌", "军", "伟", "强",
            "刚", "建军", "斌", "波", "辉", "伟", "磊", "勇", "超", "强", "鹏", "军", "波", "杰", "超", "涛", "杰", "鹏", "磊",
            "强", "鑫", "涛", "浩", "杰", "鑫", "俊杰", "磊", "帅", "宇", "鹏", "浩宇", "浩然", "宇轩", "子轩",
            "宇航", "皓轩", "子豪", "浩轩", "俊杰", "子涵"
    );
    private static Integer sno_base = 4040001;
    static private String sex = "";
    private final List<Student> _students = new ArrayList<>();

    public StudentGenerator(int sno_start, int number) throws ParseException {
        sno_base = sno_start;
        for (int i = 0; i < number; ++i) {
            Student student = GetRandom();
            _students.add(student);
        }
    }

    private static String RandomName() {
        int first_idx = r.nextInt(first_name.size());
        int flag = r.nextInt() % 2;
        String name = "";

        if (flag == 1) {
            sex = "男";
            int name_idx = r.nextInt(boy_name.size());
            name = boy_name.get(name_idx);
        } else {
            sex = "女";
            int name_idx = r.nextInt(girl_name.size());
            name = girl_name.get(name_idx);
        }
        return first_name.get(first_idx) + name;
    }

    public List<Student> get_students() {
        return _students;
    }

    public Student Random() {
        int idx = r.nextInt(_students.size());
        return _students.get(idx);
    }

    private Student GetRandom() throws ParseException {
        String name = RandomName();
        return new Student(RandomSno(), name, sex, RandomDate(), RandomHeight(), RandomDorm());
    }

    private String RandomDorm() {
        String direction = "";
        switch (r.nextInt() % 4) {
            case 0:
                direction = "东";
                break;
            case -3:
            case 1:
                direction = "西";
                break;
            case -2:
            case 2:
                direction = "南";
                break;
            case -1:
            case 3:
                direction = "北";
                break;
            default:
                break;
        }
        int bno = r.nextInt(16) + 1;
        int no = (r.nextInt(8) + 1) * 100 + r.nextInt(35) + 1;
        return direction + bno + "舍" + no;
    }

    private double RandomHeight() {
        int height = 150 + r.nextInt(40);
        return (double) height / 100;
    }

    private Integer RandomSno() {
        Integer sno = sno_base;
        sno_base += 1;
        return sno;
    }

    private String RandomDate() {
        int year = 1996 + r.nextInt(7);
        int month = r.nextInt(12) + 1;
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = r.nextInt(31) + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = r.nextInt(30) + 1;
                break;
            case 2: {
                if (year % 4 == 0) {
                    day = r.nextInt(29) + 1;
                } else {
                    day = r.nextInt(28) + 1;
                }
            }
            default:
                break;
        }
        return year + "-" + month + "-" + day;
    }
}
