package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{

		HttpSession session = request.getSession();
		String cd = request.getParameter("cd");
		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		SubjectDAO sDao = new SubjectDAO();

		Subject subject = sDao.get(cd, teacher.getSchool());

		request.setAttribute("subject", subject);

		// JSPにフォワード
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);

	}

}