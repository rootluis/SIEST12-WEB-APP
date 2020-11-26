package com.edu.mx.web.est.siest12.model;

public class Allergy {

	private int cdAlergia;
	private String nbAlergia;

	public Allergy() {
	}

	public Allergy(int cdAlergia, String nbAlergia) {
		this.cdAlergia = cdAlergia;
		this.nbAlergia = nbAlergia;
	}

	public int getCdAlergia() {
		return cdAlergia;
	}

	public void setCdAlergia(int cdAlergia) {
		this.cdAlergia = cdAlergia;
	}

	public String getNbAlergia() {
		return nbAlergia;
	}

	public void setNbAlergia(String nbAlergia) {
		this.nbAlergia = nbAlergia;
	}

	@Override
	public String toString() {
		return "Allergy [cdAlergia=" + cdAlergia + ", nbAlergia=" + nbAlergia + "]";
	}

}
