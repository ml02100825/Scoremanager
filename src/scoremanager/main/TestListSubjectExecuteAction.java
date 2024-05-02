package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear(); // 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();
		ClassNumDAO cNumDao = new ClassNumDAO();
		Map<String, String> errors = new HashMap<>();

		// セッションを取得
		HttpSession session = request.getSession();
		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher) session.getAttribute("user");
		List<String> list = cNumDao.filter(teacher.getSchool());

		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// 科目名のリストを取得
		SubjectDAO subjectDao = new SubjectDAO();
		List<Subject> subjects = subjectDao.filter(teacher.getSchool());
		request.setAttribute("subject_set", subjects);

		// リクエストパラメータから入力された科目情報を取得
		int ent_year = Integer.parseInt(request.getParameter("f1"));
		String class_num = request.getParameter("f2");
		String cd = request.getParameter("f3");
		System.out.print("科目：" + cd);

		if (ent_year == 0 || class_num.equals("0") || cd.equals("0")) {
		    // エラーメッセージを設定してフォワード
		    errors.put("f3", "入学年度、科目、クラスを選択してください");
		    // リクエスト属性にエラーメッセージと必要な情報を設定
		    request.setAttribute("errors", errors);
		    // 科目一覧
		    request.setAttribute("subject_set", subjects);
		    // クラス
		    request.setAttribute("class_num_set", list);
		    // 入学年度
		    request.setAttribute("ent_year_set", entYearSet);

		    request.getRequestDispatcher("test_list.jsp").forward(request, response);
		    return;
		}

		Subject subject = subjectDao.get(cd, teacher.getSchool());

		// テストリストを取得するためのDAOを作成
		TestListSubjectDAO testListSubDAO = new TestListSubjectDAO();

		// テストリストを取得
		List<TestListSubject> testList = testListSubDAO.filter(subject, ent_year, teacher.getSchool(), class_num);

		// リクエスト属性にテスト情報と科目情報を設定
		request.setAttribute("tests", testList);
		request.setAttribute("subject", subject);
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);

		System.out.println(testList);

		// フォワード先の JSP に遷移
		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
	}
}

