package scoremanager.main;

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
        String subjectName = request.getParameter("subject_name");
        String subjectCode = request.getParameter("subject_code");

        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setCd(subjectCode);
        Subject s = subDao.get(subjectCode,teacher.getSchool());
        if (s!=null) {
            request.setAttribute("message", "科目が正常に作成されました。");
            request.getRequestDispatcher("SubjectCreateSuccess.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "科目の作成に失敗しました。");
            request.getRequestDispatcher("SubjectCreateError.jsp").forward(request, response);
        }
    }
}
