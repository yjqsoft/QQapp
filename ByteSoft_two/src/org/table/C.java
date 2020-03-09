package org.table;

/**
 * C entity. @author MyEclipse Persistence Tools
 */

public class C implements java.io.Serializable {

	// Fields

	private Integer cno;
	private String cvno;
	private String ccno;
	private Integer c7;
	private Integer c8;
	private Integer c9;
	private Integer c10;
	private Integer c11;
	private Integer c12;
	private Integer c13;
	private Integer c14;
	private Integer c15;
	private Integer c16;
	private Integer c17;
	private Integer c18;
	private Integer c19;
	private Integer c20;
	private Integer c21;

	// Constructors

	/** default constructor */
	public C() {
	}

	/** full constructor */
	public C(String cvno, String ccno, Integer c7, Integer c8, Integer c9, Integer c10, Integer c11, Integer c12,
			Integer c13, Integer c14, Integer c15, Integer c16, Integer c17, Integer c18, Integer c19, Integer c20,
			Integer c21) {
		this.cvno = cvno;
		this.ccno = ccno;
		this.c7 = c7;
		this.c8 = c8;
		this.c9 = c9;
		this.c10 = c10;
		this.c11 = c11;
		this.c12 = c12;
		this.c13 = c13;
		this.c14 = c14;
		this.c15 = c15;
		this.c16 = c16;
		this.c17 = c17;
		this.c18 = c18;
		this.c19 = c19;
		this.c20 = c20;
		this.c21 = c21;
	}

	// Property accessors

	public Integer getCno() {
		return this.cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public String getCvno() {
		return this.cvno;
	}

	public void setCvno(String cvno) {
		this.cvno = cvno;
	}

	public String getCcno() {
		return this.ccno;
	}

	public void setCcno(String ccno) {
		this.ccno = ccno;
	}

	public Integer getC7() {
		return this.c7;
	}

	public void setC7(Integer c7) {
		this.c7 = c7;
	}

	public Integer getC8() {
		return this.c8;
	}

	public void setC8(Integer c8) {
		this.c8 = c8;
	}

	public Integer getC9() {
		return this.c9;
	}

	public void setC9(Integer c9) {
		this.c9 = c9;
	}

	public Integer getC10() {
		return this.c10;
	}

	public void setC10(Integer c10) {
		this.c10 = c10;
	}

	public Integer getC11() {
		return this.c11;
	}

	public void setC11(Integer c11) {
		this.c11 = c11;
	}

	public Integer getC12() {
		return this.c12;
	}

	public void setC12(Integer c12) {
		this.c12 = c12;
	}

	public Integer getC13() {
		return this.c13;
	}

	public void setC13(Integer c13) {
		this.c13 = c13;
	}

	public Integer getC14() {
		return this.c14;
	}

	public void setC14(Integer c14) {
		this.c14 = c14;
	}

	public Integer getC15() {
		return this.c15;
	}

	public void setC15(Integer c15) {
		this.c15 = c15;
	}

	public Integer getC16() {
		return this.c16;
	}

	public void setC16(Integer c16) {
		this.c16 = c16;
	}

	public Integer getC17() {
		return this.c17;
	}

	public void setC17(Integer c17) {
		this.c17 = c17;
	}

	public Integer getC18() {
		return this.c18;
	}

	public void setC18(Integer c18) {
		this.c18 = c18;
	}

	public Integer getC19() {
		return this.c19;
	}

	public void setC19(Integer c19) {
		this.c19 = c19;
	}

	public Integer getC20() {
		return this.c20;
	}

	public void setC20(Integer c20) {
		this.c20 = c20;
	}

	public Integer getC21() {
		return this.c21;
	}

	public void setC21(Integer c21) {
		this.c21 = c21;
	}
	 public String  getString() {
	    	String string =this.c7+""+this.c8+""+this.c9+""+this.c10+""+this.c11+""+this.c12+""+this.c13+""+this.c14+""+this.c15+""+this.c16+""+this.c17+""+this.c18+""+this.c19+""+this.c20+""+this.c21+"";
	        return string;
	    }

}