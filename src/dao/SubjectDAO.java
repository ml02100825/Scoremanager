package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDAO extends DAO{

	// フィルター後のリストへの格納処理をするメソッド

	public List<Subject> filter(School school) throws Exception {
		 String baseSql = " select * from subject where school_cd=? ";
		 Subject subject = new Subject();
		// リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement  = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文のソート
		String order = " order by cd asc ";


		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());


			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			if(rSet.next()){
				subject.setCd(rSet.getString("cd"));
				subject.setSchool(school);
				subject.setName(rSet.getString("name"));
				list.add(subject);
			}
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

	public Subject get(String cd, School school) throws Exception{
		Subject subject = new Subject();

		Connection  connection = getConnection();

		PreparedStatement statement = null;

		try{

			statement = connection.prepareStatement("select * from subject where cd = ? and school_cd = ?");

			statement.setString(1, cd);
			statement.setString(2, school.getCd());

			ResultSet rSet = statement.executeQuery();

			SchoolDAO sDao = new SchoolDAO();

			if(rSet.next()){

				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));

				subject.setSchool(sDao.get(rSet.getString("school_cd")));


			}else{
				subject = null;
			}
		}catch (Exception e){
			throw e;
		}finally{
			if (statement != null){
				try{
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			if (connection != null){
				try{
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}
			return subject;


	}

	public boolean save(Subject subject)throws Exception{
		Connection connection  = getConnection();
		School school = new School();
		SchoolDAO sDao = new SchoolDAO();

		school = sDao.get(subject.getSchool().getCd());
		PreparedStatement statement  = null;

		int  count = 0;
		try{

			Subject old = get(subject.getCd(), 	school);
			if (old == null){
				statement = connection.prepareStatement(
						"insert into subject(cd, name, school_cd) values(?, ? , ?)");

				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
				statement.setString(3, subject.getSchool().getCd());
			}else{
				statement = connection.prepareStatement("update subject set name = ? where cd = ?");

				statement.setString(1, subject.getName());

				statement.setString(2, subject.getCd());



			}
			count =statement.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			if (statement != null){
				try{
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			if (connection != null){
				try{
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}

		}
		if (count > 0) {
			return true;
		}else {
			return false;
		}

	}


	public boolean delete(Subject subject) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement("delete from subject where cd=?");
			// プリペアードステートメントに学生番号をバインド
			statement.setString(1, subject.getCd());
			// プリペアードステートメントを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}


}