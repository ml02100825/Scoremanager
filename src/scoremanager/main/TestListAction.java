package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import tool.Action;

public class TestListAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		ClassNumDAO cNumDao=new ClassNumDAO();
		SubjectDAO subjectDao = new SubjectDAO();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();				// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後までをリストに追加
		for (int i =year -10	; i < year + 1; i++){
			entYearSet.add(i);
		}
		Teacher teacher = (Teacher)session.getAttribute("user");
		List<String> list = cNumDao.filter(teacher.getSchool());
	    // 科目名のリストを取得
	    List<Subject> subjects = subjectDao.filter(teacher.getSchool());
	    request.setAttribute("subject_set", subjects);
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);

		System.out.print(subjects);
		System.out.print(list);
		System.out.print(entYearSet);
		request.getRequestDispatcher("test_list.jsp").forward(request, response);
	}

}