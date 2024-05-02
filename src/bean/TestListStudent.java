package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {
	private String subjectName;
	private String subjectCd;
	private int num;
	private int point;



	public String getsubjectName() {
		return subjectName;
	}
	public void setsubjectName(String subjectName) {
		this.subjectName = subjectName;
	}



	public String getsubjectCd() {
		return subjectCd;
	}
	public void setsubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}



	public int getnum() {
		return num;
	}
	public void setnum(int num) {
		this.num = num;
	}


	public int getpoint() {
		return point;
	}
	public void setpoint(int point) {
		this.point = point;
	}

}