package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class TeacherCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	TeacherDAO TeaDAO = new TeacherDAO();
    	String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ad = request.getParameter("admin");
        Boolean admin = false;
        if(ad == null || ad.equals("")  ){
        	admin = false;
        }else{
        	admin = true;
        }

        Teacher tea = new Teacher();
        tea.setId(id);
        tea.setName(name);
        tea.setSchool(teacher.getSchool());
        tea.setAdmin(admin);
        Teacher t = TeaDAO.get(id);

        if (t!=null) {
        	List<String>errors1=new ArrayList<>();
			errors1.add("教員コードが重複しています");
			request.setAttribute("errors1",errors1);
			request.setAttribute("id",id);
			request.setAttribute("name",name);
            request.getRequestDispatcher("teacher_create.jsp").forward(request, response);
        }// 科目コードが3文字であるかどうかをチェック
       else {
        	TeaDAO.save(tea);
			request.getRequestDispatcher("teacher_create_done.jsp").forward(request, response);
        }
    }
}