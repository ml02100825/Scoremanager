package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;


public class StudentDAO extends DAO{
	private String baseSql = " select * from student where school_cd=? and is_active = true ";
	// フィルター後のリストへの格納処理をするメソッド
	public List<Student> postFilter(ResultSet rSet, School school) throws Exception {
		// リストの初期化
		List<Student> list = new ArrayList<>();
		try{

			// リザルトを全権走査
			while(rSet.next()){
				// 学生インスタンスを初期化
				Student s = new Student();
				// 学生インスタンスを初期化
				s.setNo(rSet.getString("no"));
				s.setName(rSet.getString("name"));
				s.setEntyear(rSet.getInt("ent_year"));
				s.setClassNum(rSet.getString("class_num"));
				s.setAttend(rSet.getBoolean("is_attend"));
				s.setSchool(school);
				// リストに追加
				list.add(s);
			}}catch(SQLException | NullPointerException e){
				e.printStackTrace();
			}


		// listを返す
		return list;
	}
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement  = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and ent_year = ?  and class_num = ? ";
		// SQL文のソート
		String order = " order by no asc ";

		// SQL文の在学フラグ条件
		String conditionIsAttend = "";

		// 在学フラグがTrueの場合
		if (isAttend){
			conditionIsAttend = " and is_attend = true ";
		}
		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend	 + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());

			// プリペアードステートメントに入学年度をバインド
			statement.setInt(2,  entYear);

			// プリペアードステートメントにクラス番号をバインド
			statement.setString(3, classNum);

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
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		// リストの生成
		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();

		PreparedStatement statement  = null;
		ResultSet rSet = null;

		String condition = "and ent_year = ? ";

		String order = "order by no asc ";

		String conditionIsAttend = "";

		if (isAttend){
			conditionIsAttend = " and is_attend = true ";
		}
		try{
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend	 + order);
			statement.setString(1, school.getCd());
			statement.setInt(2,  entYear);
			rSet = statement.executeQuery();
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
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		// リストの生成
		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();

		PreparedStatement statement  = null;
		ResultSet rSet = null;


		String order = "order by no asc ";

		String conditionIsAttend = "";

		if (isAttend){
			conditionIsAttend = " and is_attend = true ";
		}
		try{
			statement = connection.prepareStatement(baseSql + conditionIsAttend	 + order);
			statement.setString(1, school.getCd());
			rSet = statement.executeQuery();
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
	public Student get(String no) throws Exception{
		Student student = new Student();

		Connection  connection = getConnection();

		PreparedStatement statement = null;

		try{

			statement = connection.prepareStatement("select * from student where no = ? ");

			statement.setString(1, no);

			ResultSet rSet = statement.executeQuery();

			SchoolDAO school = new SchoolDAO();

			if(rSet.next()){

				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntyear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				student.setActive(rSet.getBoolean("is_active"));
				student.setSchool(school.get(rSet.getString("school_cd")));


			}else{
				student = null;
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
			return student;


	}

	public boolean save(Student student)throws Exception{
		Connection connection  = getConnection();

		PreparedStatement statement  = null;

		int  count = 0;
		try{

			Student old = get(student.getNo());
			if (old == null){
				statement = connection.prepareStatement(
						"insert into student(no, name, ent_year, class_num, is_attend,is_active,  school_cd) values(?, ? ,?, ? ,? , true, ?)");

				statement.setString(1, student.getNo());
				statement.setString(2, student.getName());
				statement.setInt(3, student.getEntyear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.getIsAttend());

				statement.setString(6, student.getSchool().getCd());
			}else{
				statement = connection.prepareStatement("update student set name = ?, ent_year=?, class_num=? , is_attend = ? where no = ?");

				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntyear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.getIsAttend());

				statement.setString(5, student.getNo());



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
	public boolean delete(Student student) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement("update student set is_active = false where no = ?");
			// プリペアードステートメントに学生番号をバインド
			statement.setString(1, student.getNo());
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