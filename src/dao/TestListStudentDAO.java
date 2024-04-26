package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO{
	private String basesql="select * from test where school_cd=?  ";

	private List<TestListStudent> postFilter(ResultSet rSet, School school) throws Exception {
		List<TestListStudent> list=new ArrayList<>();
		try{
			StudentDAO stuDao = new StudentDAO();
			SubjectDAO subDao = new SubjectDAO();
			SchoolDAO schDao = new SchoolDAO();

			while(rSet.next()){
				TestListStudent t = new TestListStudent();
				t.setsubjectName(rSet.getString("subject_name"));
				t.setsubjectCd(rSet.getString("subject_cd"));
			}
		}catch(SQLException | NullPointerException e){
			e.printStackTrace();

	}
		return list;
}}