/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package bean;

import java.time.LocalDate;

public class Student implements java.io.Serializable {

	private String  no;
	private String name;
	private int entyear;
	private String classNum;
	private boolean isAttend;
	private boolean isActive;
	private School school;





	public String getNo(){
		return  no;
	}

	public String getName(){
		return name;
	}

	public int getEntyear(){
		return entyear;
	}

	public String getClassNum(){
		return classNum;
	}

	public boolean getIsAttend(){
		return isAttend;
	}

	public boolean getIsActive(){
		return isActive;
	}
	public School getSchool(){
		return school;
	}


	public void setNo(String no){
		this.no= no;
	}


	public void setName(String name){
		this.name = name;
	}

	public void setEntyear(int entyear){
		this.entyear = entyear;
	}

	public void setAttend(boolean isAttend){
		this.isAttend = isAttend;
	}

	public void setActive(boolean isActive){
		this.isActive = isActive;
	}


	public void setSchool(School school){
		this.school = school;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;

	}
	public boolean isAttend() {
		return isAttend;
	}

	public boolean isActive() {
		return isActive;
	}

	public int getSchoolYear() {
//		LocalDateインスタンスを取得
		LocalDate todaysDate = LocalDate.now();
//		現在の月と年を取得
		int month = todaysDate.getMonthValue();
		int year = todaysDate.getYear();
//		現在の月が１月から３月までの場合
		if (1 <= month && month <= 3) {
//			現在の年を1減らす
			year--;
		}
//		現在の年と入学年度から算出した現在の学年を返却
		return year - entyear + 1;
	}
}