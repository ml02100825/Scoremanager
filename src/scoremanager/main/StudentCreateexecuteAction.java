/**
 * 学生情報を作成するアクションクラス
 */
package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateexecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得
        HttpSession session = request.getSession();

        // セッションからログインしている教員情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");
        String entYearStr = "";         // 入力された年度
        String classNum = "";           // 入力されたクラス年度
        String isAttendStr = "";        // 入力された在学フラグ
        String studentno = "";
        String name = "";
        int entyear = 0;    // 入学年度
        Student student = new Student();
        boolean isAttend = false;       // 在学フラグ
        boolean create = false;         // 学生リスト
        LocalDate todaysDate = LocalDate.now();        // LocalDateインスタンスを取得

        StudentDAO sDao = new StudentDAO();            // 学生DAO
        Map<String, String> errors = new HashMap<>();   // エラーメッセージ

        // リクエストパラメーターの取得
        entYearStr = request.getParameter("f1");
        studentno = request.getParameter("f2");
        name = request.getParameter("f3");
        classNum = request.getParameter("f4");
        // リクエストにクラス番号をセット
        request.setAttribute("f4", classNum);

        // 入力された年度があれば数値に変換
        if (entYearStr != null) {
            entyear = Integer.parseInt(entYearStr);
        }

        // とってきたデータをセット
        student.setNo(studentno);
        student.setName(name);
        student.setEntyear(entyear);
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setActive(true);
        student.setSchool(teacher.getSchool());

        // 入力された学生番号をもとにすでに同じ学生番号の学生が登録されていないか検索
        Student s = sDao.get(studentno);
        // もしすでに学生が登録されていたら
        if (s != null) {
            errors.put("f2", "学生番号が重複しています");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("StudentCreate.action").forward(request, response);
        } else if (entyear != 0 && !classNum.equals("0") && !name.equals("0") && !studentno.equals("0")
                && !studentno.isEmpty() && !name.isEmpty()) {
            // 入学年度とクラス番号が選択されていてかつ学生番号と氏名が空白でない場合
            create = sDao.save(student);
        } else {
            // 入力が不完全な場合
            if (entyear == 0) {
                errors.put("f1", "入学年度を選択してください");
            }
            if (studentno.equals("0") || studentno.isEmpty()) {
                errors.put("f2", "学生番号を入力してください");
            }
            if (name.equals("0") || name.isEmpty()) {
                errors.put("f3", "氏名を入力してください");
            }
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("StudentCreate.action").forward(request, response);
        }

        // レスポンス値をセット
        // リクエストに入学年度をセット
        request.setAttribute("f1", entyear);
        // リクエストにクラス番号をセット
        request.setAttribute("f4", classNum);

        // JSPにフォワード
        request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
    }
}
