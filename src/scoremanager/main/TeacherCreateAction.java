package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class TeacherCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher=(Teacher)session.getAttribute("user");

		String id="";
		String name="";

		request.setAttribute("id", id);
		request.setAttribute("name", name);

		request.getRequestDispatcher("teacher_create.jsp").forward(request, response);
	}

}