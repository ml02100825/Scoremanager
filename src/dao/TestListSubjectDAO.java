package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Test;


public class TestListSubjectDAO extends DAO{
	private String baseSql = " select * from test";
	// フィルター後のリストへの格納処理をするメソッド
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		// リストの初期化
		List<Test> list = new ArrayList<>();
		try{
			StudentDAO stuDao = new StudentDAO();
			SubjectDAO subDao = new SubjectDAO();
			SchoolDAO schDao = new SchoolDAO();
			// リザルトを全権走査
			while(rSet.next()){
				// 学生インスタンスを初期化
				Test t = new Test();
				// 学生インスタンスを初期化
				t.setStudent(stuDao.get(rSet.getString("student_no")));
				t.setSubject(subDao.get(rSet.getString("subject_cd"),school));
				t.setSchool(schDao.get(rSet.getString("school_cd")));
				t.setPoint(rSet.getInt("point"));
				t.setNo(rSet.getInt("no"));
				t.setClassNum(rSet.getString("class_num"));
				// リストに追加
				list.add(t);
			}}catch(SQLException | NullPointerException e){
				e.printStackTrace();
			}


		// listを返す
		return list;
	}
	public List<Test> filter(Subject subject, int ent_year, School school, String class_num) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
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
			System.out.println("year2:" + year2);

		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			System.out.println(statement);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());

			// プリペアードステートメントにクラス番号をバインド
			statement.setString(2, subject.getCd());

			statement.setString(3, year2+"%");

			statement.setString(4, class_num);

			System.out.println(school.getCd() + subject.getCd() + year2+"%" + class_num);
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
		// とってきたデータの数分ループ




		// listを返す
		return list;
	}
}