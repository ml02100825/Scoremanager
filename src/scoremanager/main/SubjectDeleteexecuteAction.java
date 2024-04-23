package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteexecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得
        HttpSession session = request.getSession();

        // セッションからログインしている教員情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 科目コードをリクエストから取得
        String cd = request.getParameter("cd");

        // SubjectDAOのインスタンスを作成
        SubjectDAO sDao = new SubjectDAO();

        // 科目オブジェクトを取得
        Subject subject = sDao.get(cd, teacher.getSchool());

        // 科目が存在しない場合はエラーページにリダイレクト
        if (subject == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        // 科目を削除
        boolean deleteSuccess = sDao.delete(subject);

        // 削除が成功したかどうかを確認し、エラーページにリダイレクト
        if (!deleteSuccess) {
            response.sendRedirect("error.jsp");
            return;
        }

        // 削除が成功した場合は成功ページにフォワード
        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}
