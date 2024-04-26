package bean;

import java.io.Serializable;
import java.util.Map; // 正しいMapインターフェースのインポート

public class TestListSubject implements Serializable {
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private Map<Integer, Integer> points; // 正しいMapの宣言


	
	// Getter and Setter for entYear
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	// Getter and Setter for studentNo
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	// Getter and Setter for studentName
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	// Getter and Setter for classNum
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	// Getter and Setter for points
	public Map<Integer, Integer> getPoints() {
		return points;
	}
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	// Getter and Setter for a point by key
	public String getPoint(int key) { // 正しいメソッドシグネチャ
		return point.get(key);
	}
	public void putPoint(int key, int value) { // 正しいメソッドシグネチャ
		this.point = point;
	}
}





/*package bean;

import java.io.Serializable;

import com.sun.javafx.collections.MappingChange.Map;

public class TestListSubject implements Serializable {
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private Map<Integer,Integer> points;



	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}



	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}



	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentname) {
		this.studentName = studentname;
	}



	public String getClassNum() {
		return  classNum;
	}
	public void setClassNum(String classnum) {
		this.classNum = classnum;
	}



	public Map<Integer,Integer> getPoints() {
		return points;
	}
	public void setPoints(Map<Integer,Integer> points) {
		this.points = points;
	}



	public String getPoint(key:int) {
		return point;
	}
	public void putPoint(key:int, value:int point) {
		this.point = point;}
	}
	*/

