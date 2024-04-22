/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class MenuAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{





		// セッションからログインしている教員情報を取得




		// JSPにフォワード
		request.getRequestDispatcher("menu.jsp").forward(request, response);







	}

}