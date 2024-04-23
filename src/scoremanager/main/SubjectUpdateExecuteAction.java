package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		HttpSession session = request.getSession();

		String cd="";							// 入力された科目コード
		String name = "";							// 入力された科目名
		SubjectDAO sDao = new SubjectDAO();			// 学生DAO
		Subject subject = new Subject();
		boolean update = false;

		// リクエストパラメーターの取得
		cd = request.getParameter("f1");
		name = request.getParameter("f2");

		subject.setCd(cd);
		subject.setName(name);

		update  = sDao.save(subject);

		// レスポンス値をセット
		request.setAttribute("f1", cd);
		request.setAttribute("f2", name);

		request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
	}
}