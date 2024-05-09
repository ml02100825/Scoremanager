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

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class TeacherDeleteAction extends Action {

    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher) session.getAttribute("user");
        // 科目コードを受け取る
        String id = request.getParameter("id");

        TeacherDAO tDao = new TeacherDAO();

        // 科目オブジェクトを取得
        Teacher tea = tDao.get(id);

        // 科目オブジェクトから名前を取得し、リクエストにセット
        if (tea != null) {
            request.setAttribute("id", tea.getId());
            request.setAttribute("name", tea.getName());
        }

        // JSPにフォワード
        request.getRequestDispatcher("teacherdelete.jsp").forward(request, response);
    }
}