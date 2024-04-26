/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestRegistAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		// セッションを取得
		HttpSession session = request.getSession();


		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		String entYearStr="";							// 入力された年度
		String classNum = "";							// 入力されたクラス年度
		int entyear = 0 ;								// 入学年度
		String subject = "";
		String numStr = "";
		LocalDate todaysDate = LocalDate.now();		// LocalDateインスタンスを取得
		int year = todaysDate.getYear();
		int num = 0;// 現在の年を取得
		StudentDAO sDao = new StudentDAO();			// 学生DAO
		SubjectDAO subDao = new SubjectDAO();
		ClassNumDAO cNumDao = new ClassNumDAO();// クラス番号DAOを初期化
		TestDAO testDao = new TestDAO();
		Map<String, String> errors = new HashMap<>();	// エラーメッセージ
		List<Test> tests = null;
		List<Student> students = null;
		// リクエストパラメーターの取得
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		subject = request.getParameter("f3");
		numStr = request.getParameter("f4");
		System.out.println("1：" + entYearStr);
		System.out.println("2：" + classNum);
		System.out.println("3：" + subject);
		System.out.println("4：" + numStr);
		System.out.println(subject);
		Subject sub = subDao.get(subject, teacher.getSchool());





		// DBからデータ取得

		// ログインユーザーの学校コードをもとにクラス番号一覧を表示(その学校に存在するクラスのみ表示させたいから)
		List<String> cNumList = cNumDao.filter(teacher.getSchool());
		List<Subject> SubList = subDao.filter(teacher.getSchool());


		if (entYearStr != null) {
			// 数値に変換
			entyear = Integer.parseInt(entYearStr);
		}

		if(numStr != null){
			num = Integer.parseInt(numStr);
		}
		if (entyear !=0 && !classNum.equals("0") && !subject.equals("0") && num != 0){
			//  入学年度とクラス番号を指定
			request.setAttribute("entYear", entyear);
			request.setAttribute("subject", subject);
			request.setAttribute("sub", sub);

			request.setAttribute("num", numStr);
			request.setAttribute("classnum", classNum);
			tests = testDao.filter(teacher.getSchool(), entyear, classNum, sub , num );
			if(tests == null || tests.size() == 0){
			students = sDao.filter(teacher.getSchool(),entyear ,classNum, true);
			}

		}

		// ビジネスロジック
		if (entYearStr != null){
			// 数値に変換
			entyear = Integer.parseInt(entYearStr);
		}
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後までをリストに追加
		for (int i =year -10	; i < year + 1; i++){
			entYearSet.add(i);
		}

		// レスポンス値をセット
		// リクエストに入学年度をセット
		request.setAttribute("f1", entyear);
		// リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);

		request.setAttribute("f3", subject);
		request.setAttribute("f4", numStr);


		// リクエストに学生リストをセット
		request.setAttribute("tests", tests);



		// リクエストにデータをセット
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", cNumList);
		request.setAttribute("subject_set", SubList);

		request.setAttribute("ent_year_set", entYearSet);


		// JSPにフォワード
		request.getRequestDispatcher("test_regist.jsp").forward(request, response);







	}
	private void SetRequestData(HttpServletRequest request, HttpServletResponse response){

	}

}