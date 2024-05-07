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
		String stuNo = "" ;
		LocalDate todaysDate = LocalDate.now();		// LocalDateインスタンスを取得
		int year = todaysDate.getYear();
		int num = 0;// 現在の年を取得
		String StudentNo = null;
		Student stu = new Student();
		StudentDAO sDao = new StudentDAO();			// 学生DAO
		SubjectDAO subDao = new SubjectDAO();
		ClassNumDAO cNumDao = new ClassNumDAO();// クラス番号DAOを初期化
		TestDAO testDao = new TestDAO();
		List<String> errors = new ArrayList<>();	// エラーメッセージ
		List<String> delStu = new ArrayList<>();
		List<Test> tests = null;
		List<Student> students = null;
		List<Student> delstudents = new ArrayList<>();
		List<Test> deltests = new ArrayList<>();
		int count = 0;
		int p = 0;
		boolean error  = true;
		boolean flag = false;
		boolean TS = false;
		boolean deleteflag = false;



		// リクエストパラメーターの取得
		entYearStr= request.getParameter("f1");
		classNum = request.getParameter("f2");
		subject = request.getParameter("f3");
		numStr = request.getParameter("f4");


		Subject sub = subDao.get(subject, teacher.getSchool());

		System.out.println("numStr :" + numStr);
		try{
		if(numStr != null){
			num = Integer.parseInt(numStr);
		}
		if(entYearStr != null){
			entyear = Integer.parseInt(	entYearStr);
		}
		System.out.println("ENTYEAR = " + entyear);
		tests = testDao.filter(teacher.getSchool(), entyear, classNum, sub , num );
		int size = tests.size();
		if(size != 0){
			for(int i = 0; i < size; i++){
				stuNo = request.getParameter("del_" + tests.get(i).getStudent().getNo());
				if (stuNo != null){
					delStu.add(stuNo);
				}
			}
			System.out.println("delStu：" +  delStu);
			if(delStu.size() != 0){
				for(int i = 0; i < delStu.size(); i++){
					stu = sDao.get(delStu.get(i));
					System.out.println("Student：" +  stu);

					delstudents.add(stu);
				}
				for(int i = 0; i < delstudents.size(); i++){
					deltests.add(testDao.get(delstudents.get(i), sub, teacher.getSchool(), num));
				}
				deleteflag = testDao.delete(deltests);
			}


			tests = testDao.filter(teacher.getSchool(), entyear, classNum, sub , num );
			size = tests.size();
			for(int i = 0; i < size; i++){

				String pointStr = request.getParameter("point_" + tests.get(i).getStudent().getNo());
				System.out.print(pointStr);
				boolean isAttend = tests.get(i).getStudent().getIsAttend();
				if(isAttend == false){
					continue;
				}
				if(pointStr.equals("") == false){
					StudentNo = tests.get(i).getStudent().getNo();
					p = Integer.parseInt(pointStr);
				}else{
					p = 0;
				}
				if(p > 100 || p < 0){
					error = false;
					TS = true;

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
				stuNo = request.getParameter("del_" + students.get(i).getNo());
				if (stuNo != null){
					delStu.add(stuNo);
				}
			}
			System.out.println("delStu：" +  delStu);
			if(delStu.size() != 0){
				for(int i = 0; i < delStu.size(); i++){
					stu = sDao.get(delStu.get(i));
					System.out.println("Student：" +  stu);

					delstudents.add(stu);
				}
				for(int i = 0; i < delstudents.size(); i++){
					deltests.add(testDao.get(delstudents.get(i), sub, teacher.getSchool(), num));
				}
				deleteflag = testDao.delete(deltests);
			}


			students = sDao.filter(teacher.getSchool(),entyear ,classNum, true);
			stusize = students.size();
			for(int i = 0; i < stusize; i++){
				boolean isAttend = students.get(i).getIsAttend();
				if(isAttend == false){
					continue;
				}
				Test test = new Test();

				String pointStr = request.getParameter("point_" + students.get(i).getNo());
				if(pointStr.equals("") == false){
					StudentNo = students.get(i).getNo();
					p = Integer.parseInt(pointStr);
				}else{
					p = 0;
				}
				if(p > 100 || p < 0){
					error = false;

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
				System.out.println("tests:" + tests);
				request.setAttribute("tests", tests);
				request.setAttribute("students", students);
			}
			// リクエストに入学年度をセット
			request.setAttribute("f1", entyear);
			// リクエストにクラス番号をセット
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subject);
			request.setAttribute("f4", numStr);
			request.setAttribute("entYear", entYearStr);
			request.setAttribute("classnum", classNum);
			request.setAttribute("subject", subject);
			request.setAttribute("sub", sub);
			request.setAttribute("num", numStr);
			request.setAttribute("StudentNo", StudentNo);
			List<String> cNumList = cNumDao.filter(teacher.getSchool());
			List<Subject> SubList = subDao.filter(teacher.getSchool());
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後までをリストに追加
			for (int i =year -10	; i < year + 1; i++){
				entYearSet.add(i);
			}
			request.setAttribute("class_num_set", cNumList);
			request.setAttribute("subject_set", SubList);

			request.setAttribute("ent_year_set", entYearSet);

			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		}




		if(flag == true || deleteflag == true){
			request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
		}
		else{
			System.out.print("エラー");
		}


		}catch(NullPointerException e){
			request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
		}catch(NumberFormatException e){
			errors.add("0～100の範囲で入力してください");
			request.setAttribute("pointerrors", errors);
			if(TS == true){
				tests = testDao.filter(teacher.getSchool(), entyear, classNum, sub , num );
				request.setAttribute("tests", tests);
			}else{
				students = sDao.filter(teacher.getSchool(),entyear ,classNum, true);
				System.out.println("tests:" + tests);
				request.setAttribute("tests", tests);
				request.setAttribute("students", students);
			}
			// リクエストに入学年度をセット
			request.setAttribute("f1", entyear);
			// リクエストにクラス番号をセット
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subject);
			request.setAttribute("f4", numStr);
			request.setAttribute("entYear", entYearStr);
			request.setAttribute("classnum", classNum);
			request.setAttribute("subject", subject);
			request.setAttribute("sub", sub);
			request.setAttribute("num", numStr);
			request.setAttribute("StudentNo", StudentNo);
			List<String> cNumList = cNumDao.filter(teacher.getSchool());
			List<Subject> SubList = subDao.filter(teacher.getSchool());
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後までをリストに追加
			for (int i =year -10	; i < year + 1; i++){
				entYearSet.add(i);
			}
			request.setAttribute("class_num_set", cNumList);
			request.setAttribute("subject_set", SubList);

			request.setAttribute("ent_year_set", entYearSet);
			System.out.println("StudentNo:" + StudentNo);

			request.getRequestDispatcher("test_regist.jsp").forward(request, response);

		}



	}

}