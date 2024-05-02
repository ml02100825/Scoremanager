package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import dao.TestListStudentDAO;
import tool.Action;

public class TestListStudentExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	String studentno = "";
    	Map<String, String> errors = new HashMap<>();
    	StudentDAO sDao=new StudentDAO();
    	TestDAO tDao = new TestDAO();
    	Test test=new Test();
    	School school=new School();
    	Subject subject=new Subject();
    	Student student = new Student();
    	studentno = request.getParameter("f4");
    	Student s =  sDao.get(studentno);
    	ClassNumDAO cNumDao=new ClassNumDAO();
		SubjectDAO subjectDao = new SubjectDAO();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();				// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後までをリストに追加
		for (int i =year -10	; i < year + 1; i++){
			entYearSet.add(i);
		}
		TestListStudentDAO testliststudent=new TestListStudentDAO();

		List<String> list = cNumDao.filter(teacher.getSchool());
	    // 科目名のリストを取得
	    List<Subject> subjects = subjectDao.filter(teacher.getSchool());
	    request.setAttribute("subject_set", subjects);
	    request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);

    	if(s==null){
    		errors.put("f4", "学生番号が存在しません");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("TestList.action").forward(request, response);
    	}else{
    		List<Test> name=testliststudent.filter(s);
    		request.setAttribute("test", name);
    		System.out.print(name);
    		request.setAttribute("f4", studentno);
        	request.setAttribute("name",sDao.get(studentno).getName());
        	request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    	}
	}
}