package org.table;

/**
 * Login entity. @author MyEclipse Persistence Tools
 */

public class Login implements java.io.Serializable {

	// Fields

	private String account;
	private String password;
	private String validation;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** minimal constructor */
	public Login(String account, String password) {
		this.account = account;
		this.password = password;
	}

	/** full constructor */
	public Login(String account, String password, String validation) {
		this.account = account;
		this.password = password;
		this.validation = validation;
	}

	// Property accessors

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

}