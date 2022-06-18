package generator;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import mydb.Course;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CourseGenerator {

    private final static Random r = new Random();

    private final List<Course> _course = new ArrayList<>();

    public CourseGenerator(int course_num) throws IOException {
        String raw = GetCoursesJson(1, course_num + 20);
        JSONObject object = JSON.parseObject(raw);
        JSONArray array = object.getJSONObject("result").getJSONArray("list");
        int total = 0;
        int i = 0;
        while (total < course_num) {
            JSONObject obj = array.getJSONObject(i);
            Integer id = obj.getInteger("id");
            String name = obj.getJSONObject("mocCourseBaseCardVo").getString("name");
            String teacherName = obj.getJSONObject("mocCourseBaseCardVo").getString("teacherName");
            Course course = new Course(id.toString(), name, RandomPeroid(), RandomCredit(), teacherName);
            i++;
            if (course.teacher.length() <= 3) {
                total++;
                _course.add(course);
            }
        }
    }

    static int RandomPeroid() {
        return 30 + 5 * r.nextInt(10);
    }

    public Course Random() {
        int idx = r.nextInt(_course.size());
        return _course.get(idx);
    }

    public List<Course> get_course() {
        return _course;
    }

    public Course GetNext() {
        return _course.get(1);
    }

    private double RandomCredit() {
        return 0.5 + 0.5 * r.nextInt(10);
    }

    private String GetCoursesJson(int page_num, int course_number) throws IOException {
        URL url = new URL("https://www.icourse163.org/web/j/mocSearchBean.searchCourseCardByChannelAndCategoryId.rpc?csrfKey=99ecda87520e4ad5a71e233ab9d9c3bf");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("authority", "www.icourse163.org");
        httpConn.setRequestProperty("accept", "*/*");
        httpConn.setRequestProperty("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        httpConn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpConn.setRequestProperty("cookie", "EDUWEBDEVICE=c76a7bc2f3ae414a82fd716d5006722b; WM_TID=lnC4oyywoI9AERFEUFM%2BuVMlLC395q2D; __yadk_uid=dRNilkc4K2k40Xsuo7QX6GVGjnpftBZ7; NTESSTUDYSI=99ecda87520e4ad5a71e233ab9d9c3bf; utm=\"eyJjIjoiIiwiY3QiOiIiLCJpIjoiIiwibSI6IiIsInMiOiIiLCJ0IjoiIn0=|aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8=\"; hb_MA-A976-948FFA05E931_source=www.google.com; Hm_lvt_77dc9a9d49448cf5e629e5bebaa5500b=1653877516,1655514971; close_topBar=1; WM_NI=liEoA3SykmxJAEigM0jNhFmgXRKm5gCKOMgm8YIvPxWj5udkEmlDYLdZOJIvxUlCwXxIq%2B8qWBGOHkXLfIt%2FHV2jRMlZ5dBeV%2Fw2BRZguAmf2aIPdiFCV9azI60%2FjV4cWXg%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee93e27093b08f99c552bcef8bb6d45e828b9b86d14af1eba486d16497b3faa7f22af0fea7c3b92ab0b78b90c43991a7a58efb42839ba788d03caa92a8a7f06d9c8e858eb86d819581aee667fc878797d37495b6fb96e76187b3a4a5dc48a88681abcc7ebc8ba4a4f654bc97bfccbb7b959e89b0cb619c94aaaaf7258c8afe8bd147b086fb85c16f96f1b9d3c15a9ab9bba2f06ef2bf87b1e763869d9adae4728bea00d0e95ef8bd99b9e637e2a3; Hm_lpvt_77dc9a9d49448cf5e629e5bebaa5500b=1655515002");
        httpConn.setRequestProperty("edu-script-token", "99ecda87520e4ad5a71e233ab9d9c3bf");
        httpConn.setRequestProperty("origin", "https://www.icourse163.org");
        httpConn.setRequestProperty("referer", "https://www.icourse163.org/channel/2001.htm");
        httpConn.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Microsoft Edge\";v=\"102\"");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
        httpConn.setRequestProperty("sec-fetch-dest", "empty");
        httpConn.setRequestProperty("sec-fetch-mode", "cors");
        httpConn.setRequestProperty("sec-fetch-site", "same-origin");
        httpConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.41");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("mocCourseQueryVo=%7B%22categoryId%22%3A-1%2C%22categoryChannelId%22%3A2001%2C%22orderBy%22%3A0" +
                "%2C%22stats%22%3A30%2C%22pageIndex%22%3A" + page_num + "%2C%22pageSize%22%3A" + course_number + "%7D");
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
