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

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectListAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		// セッションを取得
		HttpSession session = request.getSession();

		SubjectDAO subjectdao = new SubjectDAO();

		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		// 科目リストを取得
		List<Subject> subject_list = subjectdao.filter(teacher.getSchool());
		System.out.println(subject_list);
		// リクエストに科目リストをセット
		request.setAttribute("subject_list", subject_list);

		// JSPにフォワード
		request.getRequestDispatcher("subject_list.jsp").forward(request, response);

	}

}