package no.hvl.dat107;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "forelesning3_one2many")
public class Karakter {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int karNr;
	private String emnekode;
	private LocalDate eksdato;
	private String bokstav;
	
	@ManyToOne
	@JoinColumn(name = "studnr")
	private Vitnemal vitnemal;

	
	
}
