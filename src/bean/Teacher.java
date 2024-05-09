/**
 *
 */
/**
 * @author h_mitukawa
 *
 */
package bean;

import java.io.Serializable;

public class Teacher extends User implements Serializable  {

	private String  id;
	private String password;
	private String name;
	private School school;
	private boolean admin;
	private boolean active;






	public String getId(){
		return  id;
	}

	public String getPassword(){
		return password;
	}

	public String getName(){
		return name;
	}
	public School getSchool(){
		return school;
	}
	public boolean getAdmin(){
		return admin;
	}
	public boolean getActive(){
		return active;
	}
	public void setId(String id){
		this.id= id;
	}
	public void setPassword(String password){
		this.password= password;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setSchool(School school){
		this.school = school;
	}

	public void setAdmin(boolean admin){
		this.admin = admin;
	}

	public void setActive(boolean active){
		this.active = active;
	}
}