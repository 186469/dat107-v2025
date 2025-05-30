package no.hvl.dat107;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main1_PlainOldJDBC {

	static final String JDBC_DRIVER = "org.postgresql.Driver";

	static final String DATABASE = "h186469";   // Endre til din databae = ditt brukernavn
	static final String BRUKERNAVN = "h186469"; // Endre til ditt brukernavn
	static final String PASSORD = "LFCliverpool2302";    // Endre til ditt passord
	
	static final String TJENER_OG_PORT = "ider-database.westeurope.cloudapp.azure.com:5433";
	static final String DB_URL = "jdbc:postgresql://" + TJENER_OG_PORT + "/" + DATABASE;

	public static void main(String[] args) {

		String sql = "SELECT id, navn FROM forelesning1.person";
		
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, BRUKERNAVN, PASSORD);

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Result:");
			while (rs.next()) {
				int id = rs.getInt("id");
				String navn = rs.getString("navn");

				System.out.print("\tID: " + id);
				System.out.println(", Navn: " + navn);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		System.out.println("Finished!");
	}

}
