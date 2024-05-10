package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;


public class TestListSubjectDAO extends DAO{
	private String baseSql = " select * from test";
	// フィルター後のリストへの格納処理をするメソッド
	private List<TestListSubject> postFilter(ResultSet rSet, School school) throws Exception {
		// リストの初期化
		List<TestListSubject> list = new ArrayList<>();
		String studentno = null;
		try{
			StudentDAO stuDao = new StudentDAO();
			SubjectDAO subDao = new SubjectDAO();
			SchoolDAO schDao = new SchoolDAO();

			// リザルトを全権走査
			while(rSet.next()){
				// 学生インスタンスを初期化
				// 学生番号が変わった場合に新しいインスタンスを作成
				if ( studentno == null ||  !studentno.equals(rSet.getString("student_no")) ) {
					TestListSubject t = new TestListSubject();
					t.setStudentNo(rSet.getString("student_no"));
					t.setStudentName(stuDao.get(rSet.getString("student_no")).getName());
					t.setEntYear(stuDao.get(rSet.getString("student_no")).getEntyear());
					t.setClassNum(rSet.getString("class_num"));

					int cnt = 1;
					t.putPoint(cnt, rSet.getInt("point"));
					list.add(t);
				}
				else{
					// リストに追加された最後のインスタンスを取得
					TestListSubject lastStudent = list.get(list.size() - 1);

					// ポイントを追加
					int cnt = lastStudent.getPoints().size() + 1;
					lastStudent.putPoint(cnt, rSet.getInt("point"));
				}

				// 学生番号を更新
				studentno = rSet.getString("student_no");
			}}catch(SQLException | NullPointerException e){
				e.printStackTrace();
			}


		// listを返す
		return list;
	}
	public List<TestListSubject> filter(Subject subject, int ent_year, School school, String class_num) throws Exception {
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement  = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = " where school_cd = ? and subject_cd = ? and student_no LIKE ? and class_num = ?";
		// SQL文のソート
		String order = " order by student_no asc ";

		String year = String.valueOf(ent_year);
		String year2 = year.substring(2,4);



		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql + condition + order);

			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());

			// プリペアードステートメントにクラス番号をバインド
			statement.setString(2, subject.getCd());

			statement.setString(3, year2+"%");

			statement.setString(4, class_num);

			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet, school);
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
		// listを返す
		return list;
	}
}