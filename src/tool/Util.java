/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package tool;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.SubjectDAO;

public class Util implements Serializable  {

	public Teacher getUser(HttpServletRequest request){
		HttpSession session = request.getSession();//セッション
		Teacher teacher=(Teacher)session.getAttribute("user");
		return teacher;
	}

	public void setClassNumSet(HttpServletRequest request) throws Exception	{
		Teacher teacher = getUser(request);
		ClassNumDAO  cNumDao = new ClassNumDAO();
		List<String> list = cNumDao.filter(teacher.getSchool());

	}

	public void setEntYearSet(HttpServletRequest request) throws Exception	{
		LocalDate todaysDate = LocalDate.now();		// LocalDateインスタンスを取得
		int year = todaysDate.getYear();				// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後までをリストに追加
		for (int i =year -10	; i < year + 1; i++){
			entYearSet.add(i);
		}

	}
	public void setSubjects(HttpServletRequest request) throws Exception	{
		Teacher teacher = getUser(request);
		SubjectDAO subDao = new SubjectDAO();
		List<Subject> list = subDao.filter(teacher.getSchool());

	}


	public void setNumSet(HttpServletRequest request) throws Exception	{


	}


}