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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentDeleteexecuteAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{

		// セッションを取得
		HttpSession session = request.getSession();


		// セッションからログインしている教員情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		String entYearStr="";							// 入力された年度
		String classNum = "";							// 入力されたクラス年度
		String isAttendStr= "";						// 入力された在学フラグ
		String studentno = "";							// 入力された学生番号
		String name = "";								// 入力された学生名
		int entyear = 0 ;								// 入学年度
		Student student = new Student();
		boolean isAttend = false;						// 在学フラグ
		boolean update = false;
		LocalDate todaysDate = LocalDate.now();		// LocalDateインスタンスを取得
		StudentDAO sDao = new StudentDAO();			// 学生DAO
		ClassNumDAO cNumDao = new ClassNumDAO();		// クラス番号DAOを初期化
		Map<String, String> errors = new HashMap<>();	// エラーメッセージ

		// リクエストパラメーターの取得
		studentno = request.getParameter("f1");
		System.out.print(studentno);
		Student s =  sDao.get(studentno);

		// 名前とクラス番号が入力されてたら
		if (s != null){

			update  = sDao.delete(s);
		}

		if(update == true){
		request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
		}
	}

}