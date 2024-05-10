package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO{
	private String baseSql="select * from test where student_no=?  ";

	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		List<TestListStudent> list=new ArrayList<>();
		try{
			StudentDAO stuDao = new StudentDAO();
			SubjectDAO subDao = new SubjectDAO();
			SchoolDAO schDao = new SchoolDAO();

			while(rSet.next()){
				TestListStudent t = new TestListStudent();
				Subject sub = new Subject();
				Student stu = new Student();
				stu=stuDao.get(rSet.getString("student_no"));
				sub = subDao.get(rSet.getString("subject_cd"),stu.getSchool());

				t.setSubjectCd(sub.getCd());
				t.setSubjectName(sub.getName());
				t.setNum(rSet.getInt("no"));
				t.setPoint(rSet.getInt("point"));
				// リストに追加
				list.add(t);
			}
		}catch(SQLException | NullPointerException e){
			e.printStackTrace();

		}
		return list;
	}
	public List<TestListStudent> filter(Student student) throws Exception {
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();
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