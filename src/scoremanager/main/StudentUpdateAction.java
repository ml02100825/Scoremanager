/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{

		HttpSession session = request.getSession();
		ClassNumDAO cNumDao = new ClassNumDAO();		// クラス番号DAOを初期化
		String no = request.getParameter("no");
		StudentDAO sDao = new StudentDAO();

		Student student = sDao.get(no);

		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		List<String> list = cNumDao.filter(teacher.getSchool());
		request.setAttribute("class_num_set", list);

		request.setAttribute("student", student);

		// JSPにフォワード
		request.getRequestDispatcher("studentupdate.jsp").forward(request, response);
	}

}