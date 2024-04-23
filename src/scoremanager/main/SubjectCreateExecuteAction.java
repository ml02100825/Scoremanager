package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	SubjectDAO subDao = new SubjectDAO();
        String subjectName = request.getParameter("name");
        String subjectCode = request.getParameter("cd");

        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setCd(subjectCode);
        subject.setSchool(teacher.getSchool());
        Subject s = subDao.get(subjectCode,teacher.getSchool());



        if (s!=null) {
        	List<String>errors1=new ArrayList<>();
			errors1.add("科目コードが重複しています");
			request.setAttribute("errors1",errors1);
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
        }// 科目コードが3文字であるかどうかをチェック
        else if (subjectCode == null || subjectCode.length() != 3) {
        	List<String> errors2 = new ArrayList<>();
            errors2.add("科目コードは3文字で入力してください");
            request.setAttribute("errors2",errors2);
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
        }else {
        	subDao.save(subject);
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
        }
    }
}
