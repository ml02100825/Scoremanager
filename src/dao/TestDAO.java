package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
public class TestDAO extends DAO{
	private String baseSql = " select * from test where school_cd=?  ";
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
				t.setClassNum(rSet.getString("class_num"));
				t.setSubject(subDao.get(rSet.getString("subject_cd"),school));
				t.setSchool(schDao.get(rSet.getString("school_cd")));
				t.setNo(rSet.getInt("no"));
				t.setPoint(rSet.getInt("point"));
				// リストに追加
				list.add(t);
			}}catch(SQLException | NullPointerException e){
				e.printStackTrace();
			}
		// listを返す
		return list;
	}
	public List<Test> filter(School school, int entYear, String classNum, Subject subject, int num) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		String year = String.valueOf(entYear);
		String year2 = year.substring(2,4);
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement  = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = " and class_num = ? and subject_cd = ? and no = ? and student_no = ?";
		// SQL文のソート
		String order = " order by student_no asc ";
		// SQL文の在学フラグ条件
		String conditionIsAttend = "";
		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend	 + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントに入学年度をバインド
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setInt(4, num);
			statement.setString(5, year2+"%");
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
	public Test get(Student student, Subject subject, School school, int no) throws Exception{
		Connection  connection = getConnection();
		PreparedStatement statement = null;
		Test test = new Test();


		try{

			statement = connection.prepareStatement("select * from test where no = ? and student_no = ? and subject_cd = ? and school_cd = ? ");
			statement.setInt(1, no);
			statement.setString(2, student.getNo());
			statement.setString(3, subject.getCd());
			statement.setString(4, school.getCd());
			ResultSet rSet = statement.executeQuery();
			if(rSet.next()){
				StudentDAO stuDao = new StudentDAO();
				SubjectDAO subDao = new SubjectDAO();
				SchoolDAO schDao = new SchoolDAO();
				test.setStudent(stuDao.get(rSet.getString("student_no")));
				test.setClassNum(rSet.getString("class_num"));
				test.setSubject(subDao.get(rSet.getString("subject_cd"),school));
				test.setSchool(schDao.get(rSet.getString("school_cd")));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));


			}else{

				test = null;
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
			return test;
	}

	private boolean save(Test test, Connection connection)throws Exception{





		PreparedStatement statement  = null;
		int  count = 0;
		try{

			Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());


			if ( old== null){
				statement = connection.prepareStatement("insert into test(student_no, subject_cd, school_cd, point, no,class_num) values(?, ? ,?, ? ,?,?)");

				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getSubject().getCd());
				statement.setString(3, test.getSchool().getCd());
				statement.setInt(4, test.getPoint());
				statement.setInt(5, test.getNo());
				statement.setString(6, test.getClassNum());
			}else{
				statement = connection.prepareStatement("update test set point = ? where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getNo());
				statement.setString(3, test.getSubject().getCd());
				statement.setString(4, test.getSchool().getCd());
				statement.setInt(5, test.getNo());
			}
			count =statement.executeUpdate();
		}catch(Exception e){
			throw e;
		}
		if (count > 0) {
			return true;
		}else {
			return false;
		}

	}


	public boolean save(List<Test> list) throws Exception {
		// リストを初期化

		int count = 0;
		// コネクションを確率
		Connection connection = getConnection();

		// プリペアードステートメント
		PreparedStatement statement  = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and ent_year = ?  and class_num = ? and subject_cd = ? and no = ?";
		// SQL文のソート
		String order = " order by no asc ";
		// SQL文の在学フラグ条件
		String conditionIsAttend = "";
		boolean t = false;
		try{
			int size = list.size();
			for(int i = 0; i < size; i++){
				t = save(list.get(i), connection);
				if (t = true){
					count++;
				}
			}


			// リストへの格納処理を実行
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



		if(count > 0){
		return true;
		}else{
			return false;
		}
	}

	private boolean delete(Test test, Connection connection)throws Exception{


		PreparedStatement statement  = null;

		int  count = 0;
		try{

			Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());

			if ( old!= null){
				statement = connection.prepareStatement(
						"delete from test where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");

				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getSubject().getCd());
				statement.setString(3, test.getSchool().getCd());
				statement.setInt(4, test.getNo());

			}
			count =statement.executeUpdate();
		}catch(NullPointerException e){
			return false;
		}catch(Exception e){
			throw e;
		}
		// listを返す
		if(count > 0){
		return true;
		}else{
			return false;
		}
	}


public boolean delete(List<Test> list) throws Exception {
	// リストを初期化
			int count = 0;
			// コネクションを確率
			Connection connection = getConnection();

			// プリペアードステートメント
			PreparedStatement statement  = null;
			// リザルトセット
			ResultSet rSet = null;
			// SQL文の条件
			String condition = "and ent_year = ?  and class_num = ? and subject_cd = ? and no = ?";
			// SQL文のソート
			String order = " order by no asc ";

			// SQL文の在学フラグ条件
			String conditionIsAttend = "";
			boolean t = false;

			try{
				int size = list.size();
				for(int i = 0; i < size; i++){
					t = delete(list.get(i), connection);
					if (t = true){
						count++;
					}
				}



				// リストへの格納処理を実行

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
			if(count > 0){
			return true;
			}else{
				return false;
			}
		}



}