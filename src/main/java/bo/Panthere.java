package bo;

import javax.persistence.Entity;

@Entity(name = "Panthere")

public class Panthere extends Animal {
	public static int pow=5;
	
	public Panthere( Position pos, Joueur J) {
		super(pow, pos, J);
	}

	public Panthere() {
		
	}

	public void ramenerpouvoir() {
		this.setPower(pow);
	}

	public String getStringRepresentation() {
		return "P"+getJ().getC();
	}

	
}
