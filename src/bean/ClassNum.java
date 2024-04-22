/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package bean;
public class ClassNum implements java.io.Serializable {

	private String  school_cd;
	private String class_num;




	public String getSchoolCd(){
		return  school_cd;
	}

	public String getClassNum(){
		return class_num;
	}



	public void setSchoolCd(String school_cd){
		this.school_cd= school_cd;
	}


	public void setClassNum(String class_num){
		this.class_num = class_num;
	}

}