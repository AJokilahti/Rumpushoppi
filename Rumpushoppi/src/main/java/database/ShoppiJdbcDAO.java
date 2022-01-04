package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Tuote;

public class ShoppiJdbcDAO implements ShoppiDAO{

	/**
	 * Hakee tietokannan taulusta kaikki tuotteet ja luo ja palauttaa tiedot Tuote-tyyppisten olioiden listana (ArrayList) 
	 * 
	 * @return ArrayList<Tuote> Listaa Tuotteet
	 */
	public ArrayList<Tuote> findAll() {
		Connection connection = null;  // tietokantayhteys
		PreparedStatement statement = null;  // sql-lause
		ResultSet resultset = null;   // select-lauseen tulostaulu
		ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();  // tyhjä asiakaslista
		Tuote tuote = null; 
		
		try {
			connection = Database.getDBConnection();
			String sqlSelect = "SELECT id, nimi, kuvaus, hinta " + "FROM tuote;";
			statement = connection.prepareStatement(sqlSelect);
			resultset = statement.executeQuery();
			while (resultset.next()) {
				tuote = readTuote(resultset);
				// lisätään asiakas listaan
				tuotteet.add(tuote);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Database.closeDBConnection(resultset, statement, connection);
		}
		return tuotteet;
	}
	
	/**
	 * Hakee tietokannan taulusta kaikki tuotteet ja luo ja palauttaa int id -tietoa vastaavan tuotteen tiedot Tuote-oliona 
	 * 
	 * @return Tuote tuote
	 */
	public Tuote findById(int id) {
		Connection connection = null;  // tietokantayhteys
		PreparedStatement statement = null;  // sql-lause
		ResultSet resultset = null;   // select-lauseen tulostaulu
		Tuote tuote1 = null; 
		Tuote tuote2 = null;
		try {
			connection = Database.getDBConnection();
			String sqlSelect = "SELECT id, nimi, kuvaus, hinta FROM tuote WHERE id = ?;";
			statement = connection.prepareStatement(sqlSelect);
			statement.setInt(1, id);
			System.out.println(statement);
			resultset = statement.executeQuery();
			while (resultset.next()) {
				tuote1 = readTuote(resultset);
				if (tuote1.getId() == id) {
				tuote2 = tuote1;
				}
			}
			
			System.out.println(tuote2.toString());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.closeDBConnection(resultset, statement, connection);
		}
		
		return tuote2;
	}

	/**
	 * Lukee tietotaulusta aktiiviselta tietoriviltä tuotteen tiedot
	 * Luo ja palauttaa niistä koostuvan Tuote-olion
	 * @param resultset
	 * 			Tietokannasta kyselyllä haettu tulostaulu
	 * @return Tuote tuote-olio
	 */
	
	private Tuote readTuote(ResultSet resultset) {
		
		try {
			int id = resultset.getInt("id");
			String nimi = resultset.getString("nimi");
			String kuvaus = resultset.getString("kuvaus");
			Double hinta = resultset.getDouble("hinta");
			System.out.println(new Tuote(id, nimi, kuvaus, hinta));
			return new Tuote(id, nimi, kuvaus, hinta);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Lisää tuotteen tiedot tietokantaan
	 * @param tuote
	 * 		Tuote-luokan olio
	 * 
	 */
	
	public boolean addTuote(Tuote tuote) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		boolean updateSuccesful = false;
		
		try {
			//tietokantayhteyden muodostaminen
			connection = Database.getDBConnection();
			//Komento, jolla luodaan uusi tuote tietokannan tauluun
			String sqlInsert =
					"INSERT INTO tuote (nimi, kuvaus,hinta)"
					+ "VALUES(?, ?, ?);";
			//valmistellaan komento
			stmtInsert = connection.prepareStatement(sqlInsert);
			//Asetetaan parametrisoidun komennon parametrit yksi kerrallaan
			//id-sarake auto-increment-tyyppinen = ei mukana insertissä
			stmtInsert.setString(1, tuote.getNimi());
			stmtInsert.setString(2, tuote.getKuvaus());
			stmtInsert.setDouble(3, tuote.getHinta());
			//Lähetetään INSERT suoritettavaksi tietokantapalvelimelle
			int rowAffected = stmtInsert.executeUpdate();
			if (rowAffected==1) {
				updateSuccesful = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.closeDBConnection(stmtInsert, connection); //Suljetaan tatement ja yhteys
		}
		
	
	return updateSuccesful;
	}
	
	/**
	 * Poistaa tuotteen tiedot tietokannasta
	 * 
	 * @param id
	 *            poistettavan tuotteen id-arvo
	 */
	public boolean removeTuote(int id) {
		Connection connection = null;
		PreparedStatement stmtDelete = null;
		boolean updateSuccessed = false;

		try {
			connection = Database.getDBConnection();
			//Poistetaan tuote tietokantasta:
			String sql = "DELETE FROM tuote WHERE id = ?";
			stmtDelete = connection.prepareStatement(sql);
			// Asetetaan parametrisoidun delete-komennon parametri 
			stmtDelete.setInt(1, id);
			System.out.println(stmtDelete);
			//Lähetetään DELETE-komento suoritettavaksi tietokantapalvelimelle
			int rowAffected = stmtDelete.executeUpdate();
			if (rowAffected == 1) updateSuccessed = true;
		} catch (SQLException e) {
		e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
		throw new RuntimeException(e);
		} finally {
		Database.closeDBConnection(stmtDelete, connection); // Suljetaan statement ja yhteys
		}
	return updateSuccessed;
		
	}

	/**
	 * Päivittää tuotteen tiedot tietokannassa
	 * 
	 * @param nimi, kuvaus, hinta, id
	 *            päivitetty nimi, kuvaus, hinta
	 *            päivitettävän tuotteen id
	 */
	public boolean updateTuote(String nimi, String kuvaus, double hinta, int tuoteId) {
		Connection connection = null;
		PreparedStatement stmtUpdate = null;
		boolean updateSuccessed = false;
		System.out.println(tuoteId);

		try {
			connection = Database.getDBConnection();
			//Poistetaan tuote tietokantasta:
			String sql = "UPDATE tuote " +
							"SET nimi = ?, kuvaus = ?, hinta = ? "+
							"WHERE id = ?";
			stmtUpdate = connection.prepareStatement(sql);
			// Asetetaan parametrisoidun update-komennon parametri 
			stmtUpdate.setString(1, nimi);
			stmtUpdate.setString(2, kuvaus);
			stmtUpdate.setDouble(3, hinta);
			stmtUpdate.setInt(4, tuoteId);
			System.out.println(stmtUpdate);
			//Lähetetään DELETE-komento suoritettavaksi tietokantapalvelimelle
			int rowAffected = stmtUpdate.executeUpdate();
			if (rowAffected == 1) updateSuccessed = true;
		} catch (SQLException e) {
		e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
		throw new RuntimeException(e);
		} finally {
		Database.closeDBConnection(stmtUpdate, connection); // Suljetaan statement ja yhteys
		}
	return updateSuccessed;
		
	}
	
}
