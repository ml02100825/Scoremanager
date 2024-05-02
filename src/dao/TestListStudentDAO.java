package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Test;

public class TestListStudentDAO extends DAO{
	private String baseSql="select * from test where student_no=?  ";

	private List<Test> postFilter(ResultSet rSet) throws Exception {
		List<Test> list=new ArrayList<>();
		try{
			StudentDAO stuDao = new StudentDAO();
			SubjectDAO subDao = new SubjectDAO();
			SchoolDAO schDao = new SchoolDAO();

			while(rSet.next()){
				Test t = new Test();
				Student stu=stuDao.get(rSet.getString("student_no"));
				t.setStudent(stuDao.get(rSet.getString("student_no")));
				t.setClassNum(rSet.getString("class_num"));
				t.setSubject(subDao.get(rSet.getString("subject_cd"),stu.getSchool()));
				t.setSchool(schDao.get(rSet.getString("school_cd")));
				t.setNo(rSet.getInt("no"));
				t.setPoint(rSet.getInt("point"));
				// リストに追加
				list.add(t);
			}
		}catch(SQLException | NullPointerException e){
			e.printStackTrace();

		}
		return list;
	}
	public List<Test> filter(Student student) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement  = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		// SQL文のソート
		String order = " order by subject_cd asc ";

		// SQL文の在学フラグ条件



		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql  + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, student.getNo());

			// プリペアードステートメントに入学年度をバインド


			// プリペアードステートメントにクラス番号をバインド

			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet);
		}catch(Exception e){
			throw e;
		} finally{
			if(statement != null){
				try{
					statement.close();

				} catch(SQLException sqle){
					throw sqle;
				}
			}
			if (connection != null){
				try{
					connection.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		// とってきたデータの数分ループ




		// listを返す
		return list;
	}

}