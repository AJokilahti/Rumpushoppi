package database;

import java.util.List;

import model.Tuote;

public interface ShoppiDAO {

	public List<Tuote> findAll();
	
	public Tuote findById(int id);
	
	public boolean addTuote(Tuote tuote);
	
	public boolean removeTuote(int id);
	
	public boolean updateTuote(String nimi, String kuvaus, double hinta, int tuoteID);
}
