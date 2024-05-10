package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {
	/**
	 * getメソッド 教員IDを指定して教員インスタンスを1件取得する
	 *
	 * @param id:String
	 *            教員ID
	 * @return 教員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Teacher get(String id) throws Exception {
		// 教員インスタンスを初期化
		Teacher teacher = new Teacher();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from teacher where id=?");
			// プリペアードステートメントに教員IDをバインド
			statement.setString(1, id);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			// 学校Daoを初期化
			SchoolDAO schoolDAO = new SchoolDAO();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// 教員インスタンスに検索結果をセット
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				// 学校フィールドには学校コードで検索した学校インスタンスをセット
				teacher.setSchool(schoolDAO.get(rSet.getString("school_cd")));
				teacher.setAdmin(rSet.getBoolean("admin"));
				teacher.setActive(rSet.getBoolean("active"));
			} else {
				// リザルトセットが存在しない場合
				// 教員インスタンスにnullをセット
				teacher = null;
			}
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

		return teacher;
	}

	/**
	 * loginメソッド 教員IDとパスワードで認証する
	 *
	 * @param id:String
	 *            教員ID
	 * @param password:String
	 *            パスワード
	 * @return 認証成功:教員クラスのインスタンス, 認証失敗:null
	 * @throws Exception
	 */
	public Teacher login(String id, String password) throws Exception {
		// 教員クラスのインスタンスを取得
		Teacher teacher = get(id);
		// 教員がnullまたはパスワードが一致しない場合
		if (teacher == null || !teacher.getPassword().equals(password)) {
			return null;
		}
		return teacher;
	}
	private List<Teacher> postFilter(ResultSet rSet, School school) throws Exception {
		// リストの初期化
		List<Teacher> list = new ArrayList<>();
		try{

			SchoolDAO sDao = new SchoolDAO();
			// リザルトを全権走査
			while(rSet.next()){
				// 学生インスタンスを初期化
				Teacher t = new Teacher();
				// 学生インスタンスを初期化
				t.setId(rSet.getString("id"));
				t.setName(rSet.getString("name"));
				t.setPassword(rSet.getString("password"));
				t.setSchool(sDao.get(rSet.getString("school_cd")));
				t.setAdmin(rSet.getBoolean("admin"));
				// リストに追加
				list.add(t);
			}}catch(SQLException | NullPointerException e){
				e.printStackTrace();
			}
		// listを返す
		return list;
	}
	public List<Teacher> filter(School school) throws Exception {
		// リストを初期化
		List<Teacher> list = new ArrayList<>();

		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement  = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String sql = " select * from teacher where school_cd = ?  and active = true";
		// SQL文のソート
		// SQL文の在学フラグ条件
		String conditionIsAttend = "";
		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(sql);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
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



	public boolean save(Teacher teacher)throws Exception{
		Connection connection  = getConnection();

		PreparedStatement statement  = null;

		int  count = 0;
		try{

			Teacher old = get(teacher.getId());
			if (old == null){
				statement = connection.prepareStatement(
						"insert into teacher(id, name, password, active, admin,  school_cd) values(?, ? ,'password', true , ?, ?)");

				statement.setString(1, teacher.getId());
				statement.setString(2, teacher.getName());
				statement.setBoolean(3, teacher.getAdmin());
				statement.setString(4, teacher.getSchool().getCd());
			}else{
				statement = connection.prepareStatement("update teacher set name = ?, admin=? where id = ?");

				statement.setString(1, teacher.getName());
				statement.setBoolean(2, teacher.getAdmin());

				statement.setString(3, teacher.getId());



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
	public boolean delete(Teacher teacher) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement("update teacher set active = false where id = ? and admin = false");
			// プリペアードステートメントに学生番号をバインド
			statement.setString(1, teacher.getId());
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
