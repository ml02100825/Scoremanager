
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDAO extends DAO{


	// テーブルの情報をlistに入れるメソッド
	public List<String> filter(School school) throws Exception {
		// リストの生成
		List<String> list = new ArrayList<>();

		Connection con = getConnection();
		PreparedStatement st  =con.prepareStatement(
				"select class_num from class_num where school_cd = ? order by class_num");

		st.setString(1, school.getCd());
		ResultSet rs = st.executeQuery();
		// とってきたデータの数分ループ
		while(rs.next()){
			// リストに追加
			list.add(rs.getString("class_num"));
		}
		st.close();
		con.close();

		// listを返す
		return list;
	}



}