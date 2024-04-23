package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDAO;
import tool.Action;

public class SubjectCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher=(Teacher)session.getAttribute("user");
		ClassNumDAO cNumDao = new ClassNumDAO();
		List<String> list = cNumDao.filter(teacher.getSchool());

		String cd="";
		String name="";

		request.setAttribute("cd", cd);
		request.setAttribute("name", name);
		request.setAttribute("list", list);

		request.getRequestDispatcher("subject_create.jsp").forward(request, response);
	}

}