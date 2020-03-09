package org.table;

import java.sql.Timestamp;

/**
 * Gorder entity. @author MyEclipse Persistence Tools
 */

public class Gorder implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String ouserId;
	private String ono;
	private String oplace;
	private String otime;
	private String oclass;
	private Timestamp oappointment;
	private String osite;
	private Float omony;
	private String opay;

	// Constructors

	/** default constructor */
	public Gorder() {
	}

	/** full constructor */
	public Gorder(String ouserId, String ono, String oplace, String otime, String oclass, Timestamp oappointment,
			String osite, Float omony, String opay) {
		this.ouserId = ouserId;
		this.ono = ono;
		this.oplace = oplace;
		this.otime = otime;
		this.oclass = oclass;
		this.oappointment = oappointment;
		this.osite = osite;
		this.omony = omony;
		this.opay = opay;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getOuserId() {
		return this.ouserId;
	}

	public void setOuserId(String ouserId) {
		this.ouserId = ouserId;
	}

	public String getOno() {
		return this.ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	public String getOplace() {
		return this.oplace;
	}

	public void setOplace(String oplace) {
		this.oplace = oplace;
	}

	public String getOtime() {
		return this.otime;
	}

	public void setOtime(String otime) {
		this.otime = otime;
	}

	public String getOclass() {
		return this.oclass;
	}

	public void setOclass(String oclass) {
		this.oclass = oclass;
	}

	public Timestamp getOappointment() {
		return this.oappointment;
	}

	public void setOappointment(Timestamp oappointment) {
		this.oappointment = oappointment;
	}

	public String getOsite() {
		return this.osite;
	}

	public void setOsite(String osite) {
		this.osite = osite;
	}

	public Float getOmony() {
		return this.omony;
	}

	public void setOmony(Float omony) {
		this.omony = omony;
	}

	public String getOpay() {
		return this.opay;
	}

	public void setOpay(String opay) {
		this.opay = opay;
	}

}