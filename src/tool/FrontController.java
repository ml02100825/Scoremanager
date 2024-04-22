package tool;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet	{
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws  ServletException, IOException{
		PrintWriter out =response.getWriter();
		try{
			String path = request.getServletPath().substring(1);
			String name = path.replace(".a", "A").replace('/', '.');
			System.out.println("★ sevlet path ->" + request.getServletPath());
			System.out.println("★ class name ->" + name);

			Action action = (Action)Class.forName(name).
					getDeclaredConstructor().newInstance();
				action.execute(request, response);
			request.setCharacterEncoding("utf-8");


			}catch (Exception e){
				e.printStackTrace(out);
		}

	}

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			)throws  ServletException, IOException{
		doPost(request, response);
	}
}

