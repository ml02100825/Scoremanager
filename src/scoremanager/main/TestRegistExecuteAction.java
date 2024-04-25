/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package scoremanager.main;

import java.time.LocalDate;
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

public class TestRegistExecuteAction extends Action{

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
		int p = 0;
		System.out.print(subject);

		// リクエストパラメーターの取得
		subject = request.getParameter("f1");
		classNum = request.getParameter("f2");
		numStr = request.getParameter("f3");
		Subject sub = subDao.get(subject, teacher.getSchool());
		System.out.println(sub);
		if(numStr != null){
			num = Integer.parseInt(numStr);
		}
		tests = testDao.filter(teacher.getSchool(), entyear, classNum, sub , num );
		int size = tests.size();
		for(int i = 0; i < size; i++){
			String pointStr = request.getParameter("point_" + tests.get(i).getStudent().getNo());
			System.out.print(pointStr);
			p = Integer.parseInt(pointStr);
			tests.get(i).setPoint(p);
		}

		testDao.save(tests);




		request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);


		System.out.println(p);




	}

}