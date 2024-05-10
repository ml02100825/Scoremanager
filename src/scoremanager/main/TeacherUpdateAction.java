/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDAO;
import dao.TeacherDAO;
import tool.Action;

public class TeacherUpdateAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{

		HttpSession session = request.getSession();		// クラス番号DAOを初期化
		String id = request.getParameter("id");
		TeacherDAO tDao = new TeacherDAO();


		Teacher tea = tDao.get(id);

		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		request.setAttribute("teacher", tea);

		// JSPにフォワード
		request.getRequestDispatcher("teacherupdate.jsp").forward(request, response);







	}

}