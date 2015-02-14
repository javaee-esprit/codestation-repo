package org.ug.cs.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="ugcs_user")
@NamedQuery( name="isLoginInUse", query="select case when (count(u)>0) then true else false end from User u where u.login=:x")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String login;
	private String password;

	public User() {
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Id @GeneratedValue( strategy=GenerationType.IDENTITY )    
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getLogin() {
		return this.login;
	}

	@Column( unique=true )
	public void setLogin(String login) {
		this.login = login;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ "]";
	}
	
	
   
}
