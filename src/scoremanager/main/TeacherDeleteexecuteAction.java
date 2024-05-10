package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class TeacherDeleteexecuteAction extends Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// セッションを取得
		HttpSession session = request.getSession();

		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 科目コードをリクエストから取得
		String id = request.getParameter("id");
		Boolean deleteSuccess = false;

		// SubjectDAOのインスタンスを作成
		TeacherDAO tDao = new TeacherDAO();

		// 科目オブジェクトを取得
		Teacher tea = tDao.get(id);

		// 科目が存在しない場合はエラーページにリダイレクト
		if (tea != null) {
			// 科目を削除
			deleteSuccess = tDao.delete(tea);

		}

		request.setAttribute("flag", deleteSuccess);

		// 削除が成功した場合は成功ページにフォワード
		request.getRequestDispatcher("teacher_delete_done.jsp").forward(request, response);


	}
}
