package scoremanager.main;

/**
*
*/
/**
* @author h_mitukawa
*
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject; // Subjectクラスをインポート
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteAction extends Action {

    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher) session.getAttribute("user");
        // 科目コードを受け取る
        String cd = request.getParameter("cd");

        SubjectDAO sDao = new SubjectDAO();

        // 科目オブジェクトを取得
        Subject subject = sDao.get(cd, teacher.getSchool());

        // 科目オブジェクトから名前を取得し、リクエストにセット
        if (subject != null) {
            request.setAttribute("cd", subject.getCd());
            request.setAttribute("name", subject.getName());
        }

        // JSPにフォワード
        request.getRequestDispatcher("subjectdelete.jsp").forward(request, response);
    }
}
