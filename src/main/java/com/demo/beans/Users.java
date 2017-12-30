package com.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author	Ruchir Gupta
 * @since	31 dec 2017
 * @contact	erruchirgupta@gmail.com
 */
@Entity
@Table(name = "users", catalog = "customerdemo")
public class Users implements java.io.Serializable {

	private String name;
	private String pass;

	public Users() {
	}

	public Users(String name) {
		this.name = name;
	}

	public Users(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	@Id

	@Column(name = "name", unique = true, nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pass", length = 20)
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
