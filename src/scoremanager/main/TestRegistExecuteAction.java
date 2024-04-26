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
import java.util.List;

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
		String StudentNo = null;
		StudentDAO sDao = new StudentDAO();			// 学生DAO
		SubjectDAO subDao = new SubjectDAO();
		ClassNumDAO cNumDao = new ClassNumDAO();// クラス番号DAOを初期化
		TestDAO testDao = new TestDAO();
		List<String> errors = new ArrayList<>();	// エラーメッセージ
		List<Test> tests = null;
		List<Student> students = null;
		int count = 0;
		int p = 0;
		boolean error  = true;
		boolean flag = false;
		boolean TS = false;



		// リクエストパラメーターの取得
		entYearStr= request.getParameter("f1");
		classNum = request.getParameter("f2");
		subject = request.getParameter("f3");
		numStr = request.getParameter("f4");


		Subject sub = subDao.get(subject, teacher.getSchool());

		System.out.println("numStr :" + numStr);
		if(numStr != null){
			num = Integer.parseInt(numStr);
		}
		tests = testDao.filter(teacher.getSchool(), entyear, classNum, sub , num );
		int size = tests.size();
		if(size != 0){
		for(int i = 0; i < size; i++){
			String pointStr = request.getParameter("point_" + tests.get(i).getStudent().getNo());
			System.out.print(pointStr);
			if(pointStr.equals("") == false){
				p = Integer.parseInt(pointStr);
				}else{
					p = 0;
				}
			if(p > 100 || p < 0){
				error = false;
				TS = true;
				StudentNo = tests.get(i).getStudent().getNo();
				break;
			}
			tests.get(i).setPoint(p);
			count++;
		}
		}else{
			if(entYearStr != null ){
				entyear = Integer.parseInt(entYearStr);
			}
			students = sDao.filter(teacher.getSchool(),entyear ,classNum, true);
			int stusize = students.size();
			for(int i = 0; i < stusize; i++){
				Test test = new Test();

				String pointStr = request.getParameter("point_" + students.get(i).getNo());
				if(pointStr.equals("") == false){
				p = Integer.parseInt(pointStr);
				}else{
					p = 0;
				}
				if(p > 100 || p < 0){
					error = false;
					StudentNo = students.get(i).getNo();
					break;
				}
				test.setSchool(teacher.getSchool());
				test.setClassNum(classNum);
				test.setNo(num);
				test.setPoint(p);
				test.setStudent(students.get(i));
				test.setSubject(sub);
				tests.add(test);


			}

		}

		if(error == true){
		flag = testDao.save(tests);
		}
		else{
			errors.add("0～100の範囲で入力してください");
			request.setAttribute("pointerrors", errors);
			if(TS == true){
				tests = testDao.filter(teacher.getSchool(), entyear, classNum, sub , num );
				request.setAttribute("tests", tests);
			}else{
				students = sDao.filter(teacher.getSchool(),entyear ,classNum, true);
				request.setAttribute("students", students);
			}
			request.setAttribute("entYear", entYearStr);
			request.setAttribute("classnum", classNum);
			request.setAttribute("subject", subject);
			request.setAttribute("sub", sub);
			request.setAttribute("num", numStr);
			request.setAttribute("StudentNo", StudentNo);

			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		}




		if(flag == true){
		request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
		}
		else{
			System.out.print("エラー");
		}






	}

}