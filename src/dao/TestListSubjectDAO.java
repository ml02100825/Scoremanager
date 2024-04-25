package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestListSubjectDAO extends DAO {
    public List<Test> filter(int ent_year, String class_num, String cd) throws Exception {
        List<Test> testList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // コネクションを取得
            connection = getConnection();

            // SQLクエリを準備
            String sql = "SELECT * FROM test " +
                         "INNER JOIN student ON test.student_no = student.no " +
                         "INNER JOIN subject ON test.subject_cd = subject.cd " +
                         "WHERE student.ent_year = ? AND student.class_num = ? AND subject.cd = ?";
            statement = connection.prepareStatement(sql);

            // パラメータを設定
            statement.setInt(1, ent_year);
            System.out.println("入学年度:" + ent_year);
            statement.setString(2, class_num);
            System.out.println("クラス:" + class_num);
            statement.setString(3, cd);
            System.out.println("科目コード:" + cd);

            // クエリを実行
            rs = statement.executeQuery();

            // 結果セットを処理
            while (rs.next()) {
                Test test = new Test();
                // 結果セットから Test オブジェクトにデータを設定
                test.setNo(rs.getInt("no"));
                test.setPoint(rs.getInt("point"));
                // 他のプロパティも設定
                testList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ResultSet、Statement、Connection を逆順にクローズ
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return testList;
    }
}
