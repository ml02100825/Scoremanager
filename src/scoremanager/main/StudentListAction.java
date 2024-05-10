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

public class StudentListAction extends Action{

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
		int entyear = 0 ;								// 入学年度
		boolean isAttend = false;						// 在学フラグ
		List<Student> students = null;					// 学生リスト
		LocalDate todaysDate = LocalDate.now();		// LocalDateインスタンスを取得
		int year = todaysDate.getYear();				// 現在の年を取得
		StudentDAO sDao = new StudentDAO();			// 学生DAO
		ClassNumDAO cNumDao = new ClassNumDAO();		// クラス番号DAOを初期化
		Map<String, String> errors = new HashMap<>();	// エラーメッセージ

		// リクエストパラメーターの取得
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		isAttendStr = request.getParameter("f3");

		// 在学フラグが送信されていた場合
		if (isAttendStr != null){
			// 在学フラグを立てる
			isAttend = true;
			// リクエストに在学フラグをセット
			request.setAttribute("f3", isAttendStr);
		}


		// DBからデータ取得

		// ログインユーザーの学校コードをもとにクラス番号一覧を表示(その学校に存在するクラスのみ表示させたいから)
		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYearStr != null) {
			// 数値に変換
			entyear = Integer.parseInt(entYearStr);
		}

		if (entyear !=0 && !classNum.equals("0")){
			//  入学年度とクラス番号を指定
			students  = sDao.filter(teacher.getSchool(), entyear, classNum, isAttend);

		}else if(entyear !=0 && classNum.equals("0")){
			// 入学年度のみ指定
			students = sDao.filter(teacher.getSchool(), entyear, isAttend);
		} else if(entyear == 0 && classNum == null || entyear == 0 &&  classNum.equals("0")){
			// 指定なしの場合
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(),  isAttend);

		}else{
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			request.setAttribute("errors", errors);
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
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

		// リクエストに学生リストをセット
		request.setAttribute("students", students);

		// リクエストにデータをセット
		request.setAttribute("class_num_set", list);

		request.setAttribute("ent_year_set", entYearSet);

		// JSPにフォワード
		request.getRequestDispatcher("studentlist.jsp").forward(request, response);
	}

}