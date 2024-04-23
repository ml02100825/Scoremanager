package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteexecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得
        HttpSession session = request.getSession();

        // セッションからログインしている教員情報を取得
     		Teacher teacher = (Teacher) session.getAttribute("user");

        // 科目コードをリクエストから取得
        String cd = request.getParameter("cd");

        // SubjectDAOのインスタンスを作成
        SubjectDAO sDao = new SubjectDAO();

        // 科目オブジェクトを取得
        Subject subject = sDao.get(cd, teacher.getSchool());

        // 科目コードを使って科目を削除
        sDao.delete(subject);

       	// JSPにフォワード
     	request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);

    }
}