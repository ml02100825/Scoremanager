package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // リクエストパラメータから入力された科目情報を取得
        int ent_year = Integer.parseInt(request.getParameter("f1"));
        String class_num = request.getParameter("f2");
        String cd = request.getParameter("f3");

        // セッションを取得
        HttpSession session = request.getSession();
        // セッションからログインしている教員情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 科目コードから科目名を取得
        SubjectDAO subjectdao = new SubjectDAO();
        Subject subject = subjectdao.get(cd, teacher.getSchool());

        // テストリストを取得するためのDAOを作成
        TestListSubjectDAO testListSubjectDAO = new TestListSubjectDAO();

        // テストリストを取得
        List<Test> testList =testListSubjectDAO.filter(subject, ent_year, teacher.getSchool(),class_num);

        // リクエスト属性にテスト情報と科目情報を設定
        request.setAttribute("tests", testList);
        request.setAttribute("subject",subject);
        System.out.println(testList);

        // フォワード先の JSP に遷移
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }
}

