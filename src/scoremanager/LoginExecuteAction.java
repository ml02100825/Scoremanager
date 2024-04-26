package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;


public class LoginExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		String id = "";
		String password = "";
		TeacherDAO teacherDAO = new TeacherDAO();
		Teacher teacher = null;

		//リクエストパラメータ―の取得 2
		id = request.getParameter("id");// 教員ID
		password = request.getParameter("password");//パスワード

		//DBからデータ取得 3
		teacher = teacherDAO.login(id, password);//教員データ取得
		System.out.println(teacher);

		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//フォワード 7
		//条件で手順4~7の内容が分岐
		if (teacher != null) {// 認証成功の場合
			// セッション情報を取得
			HttpSession session = request.getSession(true);
			// 認証済みフラグを立てる
			teacher.setAuthenticated(true);
			// セッションにログイン情報を保存
			session.setAttribute("user", teacher);

			//リダイレクト
			url = "main/Menu.action";
			response.sendRedirect(url);
		} else {
			// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<>();
			errors.add("IDまたはパスワードが確認できませんでした");
			request.setAttribute("errors", errors);
			// 入力された教員IDをセット
			request.setAttribute("id", id);
			System.out.print(errors);

			//フォワード
			url = "login.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}

//		req.getRequestDispatcher(url).forward(req, res);
	}

}
