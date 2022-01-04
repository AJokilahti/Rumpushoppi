package model;

public class Tuote {

	
	private int id;
	private String kuvaus;
	private String nimi;
	private double hinta;
	
	public Tuote() {
		super();
		this.id = 0;
		this.nimi = null;
		this.kuvaus = null;
		this.hinta = 0.0;
	}
	
	public Tuote(int id, String nimi, String kuvaus, double hinta) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
	}
	
	public Tuote(String nimi, String kuvaus, double hinta) {
		super();
		this.id = 0;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
	}

	public int getId() {
		return id;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public String getNimi() {
		return nimi;
	}

	public double getHinta() {
		return hinta;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	
	@Override
	public String toString() {
		return id + " " + nimi + " (" + kuvaus + ") " + hinta + " euroa";
	}
	
}
