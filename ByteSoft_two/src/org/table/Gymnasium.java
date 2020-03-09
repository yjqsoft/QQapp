package org.table;

/**
 * Gymnasium entity. @author MyEclipse Persistence Tools
 */
public class Gymnasium extends AbstractGymnasium implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Gymnasium() {
	}

	/** full constructor */
	public Gymnasium(String vno, String vname, String vaddress, String vfunctioinary, String vphone, String vpicture,
			Integer vscore, String vball, String vserve, String vintroduce, Integer vsum, String vfloor,
			String vlighting, String vrest, String vsell, String vsellsport, String vlocation) {
		super(vno, vname, vaddress, vfunctioinary, vphone, vpicture, vscore, vball, vserve, vintroduce, vsum, vfloor,
				vlighting, vrest, vsell, vsellsport, vlocation);
	}

}
