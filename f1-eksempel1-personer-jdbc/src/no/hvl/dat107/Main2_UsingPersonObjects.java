package no.hvl.dat107;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main2_UsingPersonObjects {

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	
	static final String DATABASE = "h186469";   // Endre til din databae = ditt brukernavn
	static final String BRUKERNAVN = "h186469"; // Endre til ditt brukernavn
	static final String PASSORD = "LFCliverpool2302";    // Endre til ditt passord
	
	static final String TJENER_OG_PORT = "ider-database.westeurope.cloudapp.azure.com:5433";
	static final String DB_URL = "jdbc:postgresql://" + TJENER_OG_PORT + "/" + DATABASE;
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		List<Person> personer = new ArrayList<>();
		
		String sql = "SELECT id, navn FROM forelesning1.person";

		Class.forName(JDBC_DRIVER);
		
		System.out.println("Kobler til database...");
		try (Connection conn = DriverManager.getConnection(DB_URL, BRUKERNAVN, PASSORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setNavn(rs.getString("navn"));
				personer.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Resultat:");
		for (Person p : personer) {
			System.out.println(p);
		}
		
		System.out.println("Ferdig!");
	}

}
