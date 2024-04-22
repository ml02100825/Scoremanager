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
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateexecuteAction extends Action{

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
		entYearStr = request.getParameter("f1");
		studentno = request.getParameter("f2");
		name = request.getParameter("f3");
		classNum = request.getParameter("f4");
		isAttendStr = request.getParameter("f5");

		// もし名前が入力されてなかったら
		if (name == null || name.isEmpty()) {
		    // 名前が未入力の場合のエラー処理

		    List<String> error = new ArrayList<>();
		    error.add("名前が未入力です。");
		    request.setAttribute("error", error);
			List<String> list = cNumDao.filter(teacher.getSchool());
			request.setAttribute("class_num_set", list);
			Student stu = sDao.get(studentno);
		    request.setAttribute("student", stu);
		    request.getRequestDispatcher("studentupdate.jsp").forward(request, response);
		}else{

		if (isAttendStr != null){
			isAttend = true;
			}

		// DBからデータ取得

		// ログインユーザーの学校コードをもとにクラス番号一覧を表示(その学校に存在するクラスのみ表示させたいから)

		if (entYearStr != null) {
			// 数値に変換
			entyear = Integer.parseInt(entYearStr);
		}

		student.setNo(studentno);
		student.setName(name);
		student.setEntyear(entyear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		student.setSchool(teacher.getSchool());





		// 名前とクラス番号が入力されてたら
		if (name !=null && !classNum.equals("0")){

			update  = sDao.save(student);



		}else{
			errors.put("f1", "このフィールドを入力してください");
			request.setAttribute("errors", errors);
			// 全学生情報を取得

			errors.put("f2", "このフィールドを入力してください");
			request.setAttribute("errors", errors);
			// 全学生情報を取得

		}


		// レスポンス値をセット
		// リクエストに入学年度をセット
		request.setAttribute("f1", entyear);
		// リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);


		request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
		}







	}

}