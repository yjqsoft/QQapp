package org.table;

/**
 * AbstractGymnasium entity provides the base persistence definition of the
 * Gymnasium entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGymnasium implements java.io.Serializable {

	// Fields

	private String vno;
	private String vname;
	private String vaddress;
	private String vfunctioinary;
	private String vphone;
	private String vpicture;
	private Integer vscore;
	private String vball;
	private String vserve;
	private String vintroduce;
	private Integer vsum;
	private String vfloor;
	private String vlighting;
	private String vrest;
	private String vsell;
	private String vsellsport;
	private String vlocation;

	// Constructors

	/** default constructor */
	public AbstractGymnasium() {
	}

	/** full constructor */
	public AbstractGymnasium(String vno, String vname, String vaddress, String vfunctioinary, String vphone,
			String vpicture, Integer vscore, String vball, String vserve, String vintroduce, Integer vsum,
			String vfloor, String vlighting, String vrest, String vsell, String vsellsport, String vlocation) {
		this.vno = vno;
		this.vname = vname;
		this.vaddress = vaddress;
		this.vfunctioinary = vfunctioinary;
		this.vphone = vphone;
		this.vpicture = vpicture;
		this.vscore = vscore;
		this.vball = vball;
		this.vserve = vserve;
		this.vintroduce = vintroduce;
		this.vsum = vsum;
		this.vfloor = vfloor;
		this.vlighting = vlighting;
		this.vrest = vrest;
		this.vsell = vsell;
		this.vsellsport = vsellsport;
		this.vlocation = vlocation;
	}

	// Property accessors

	public String getVno() {
		return this.vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVaddress() {
		return this.vaddress;
	}

	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}

	public String getVfunctioinary() {
		return this.vfunctioinary;
	}

	public void setVfunctioinary(String vfunctioinary) {
		this.vfunctioinary = vfunctioinary;
	}

	public String getVphone() {
		return this.vphone;
	}

	public void setVphone(String vphone) {
		this.vphone = vphone;
	}

	public String getVpicture() {
		return this.vpicture;
	}

	public void setVpicture(String vpicture) {
		this.vpicture = vpicture;
	}

	public Integer getVscore() {
		return this.vscore;
	}

	public void setVscore(Integer vscore) {
		this.vscore = vscore;
	}

	public String getVball() {
		return this.vball;
	}

	public void setVball(String vball) {
		this.vball = vball;
	}

	public String getVserve() {
		return this.vserve;
	}

	public void setVserve(String vserve) {
		this.vserve = vserve;
	}

	public String getVintroduce() {
		return this.vintroduce;
	}

	public void setVintroduce(String vintroduce) {
		this.vintroduce = vintroduce;
	}

	public Integer getVsum() {
		return this.vsum;
	}

	public void setVsum(Integer vsum) {
		this.vsum = vsum;
	}

	public String getVfloor() {
		return this.vfloor;
	}

	public void setVfloor(String vfloor) {
		this.vfloor = vfloor;
	}

	public String getVlighting() {
		return this.vlighting;
	}

	public void setVlighting(String vlighting) {
		this.vlighting = vlighting;
	}

	public String getVrest() {
		return this.vrest;
	}

	public void setVrest(String vrest) {
		this.vrest = vrest;
	}

	public String getVsell() {
		return this.vsell;
	}

	public void setVsell(String vsell) {
		this.vsell = vsell;
	}

	public String getVsellsport() {
		return this.vsellsport;
	}

	public void setVsellsport(String vsellsport) {
		this.vsellsport = vsellsport;
	}

	public String getVlocation() {
		return this.vlocation;
	}

	public void setVlocation(String vlocation) {
		this.vlocation = vlocation;
	}

}