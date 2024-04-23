package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{

		HttpSession session = request.getSession();
		String cd = request.getParameter("cd");
		School school = (School) session.getAttribute("school");
		SubjectDAO sDao = new SubjectDAO();

		Subject subject = sDao.get(cd, school);

		request.setAttribute("subject", subject);

		// JSPにフォワード
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);

	}

}