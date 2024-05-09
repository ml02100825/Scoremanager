package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class TeacherUpdateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		HttpSession session = request.getSession();

		String id="";							// 入力された科目コード
		String name = "";							// 入力された科目名
		TeacherDAO tDao = new TeacherDAO();			// 学生DAO
		Teacher tea = new Teacher();
		Teacher existtea = new Teacher();
		List<String> errors = new ArrayList<>();	// エラーメッセージ
		boolean update = false;
		Teacher teacher = (Teacher)session.getAttribute("user");

		// リクエストパラメーターの取得
		id = request.getParameter("f1");
		name = request.getParameter("f2");
	    String ad = request.getParameter("f3");
        Boolean admin = false;
        if(ad == null || ad.equals("")  ){
        	admin = false;
        }else{
        	admin = true;
        }


        tea.setId(id);
        tea.setName(name);
        tea.setSchool(teacher.getSchool());
        tea.setAdmin(admin);
		existtea = tDao.get(id);

		if(existtea == null){
			errors.add("教員が存在していません");
			request.setAttribute("errors", errors);
			request.setAttribute("subject", tea);
			request.getRequestDispatcher("subject_update.jsp").forward(request, response);

		}else{
			update  = tDao.save(tea);
		}

		// レスポンス値をセット
		request.setAttribute("f1", id);
		request.setAttribute("f2", name);

		request.getRequestDispatcher("teacher_update_done.jsp").forward(request, response);
	}
}