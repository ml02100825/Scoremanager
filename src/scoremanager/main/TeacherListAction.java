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

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class TeacherListAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		// セッションを取得
		HttpSession session = request.getSession();

		TeacherDAO teaDAO = new TeacherDAO();

		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		// 科目リストを取得
		List<Teacher> TeaList = teaDAO.filter(teacher.getSchool());

		// リクエストに科目リストをセット
		request.setAttribute("teacher_list", TeaList);

		// JSPにフォワード
		request.getRequestDispatcher("teacher_list.jsp").forward(request, response);
	}

}