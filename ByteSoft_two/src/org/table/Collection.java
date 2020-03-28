package org.table;

/**
 * Collection entity. @author MyEclipse Persistence Tools
 */

public class Collection implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String gymnasiumId;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** full constructor */
	public Collection(String userId, String gymnasiumId) {
		this.userId = userId;
		this.gymnasiumId = gymnasiumId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGymnasiumId() {
		return this.gymnasiumId;
	}

	public void setGymnasiumId(String gymnasiumId) {
		this.gymnasiumId = gymnasiumId;
	}

}