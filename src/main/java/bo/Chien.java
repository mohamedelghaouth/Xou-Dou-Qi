package bo;

import javax.persistence.Entity;

@Entity(name = "Chien")

public class Chien  extends Animal {
	public static int pow=4;
	
	public Chien( Position pos, Joueur J) {
		super(pow, pos, J);
	}

	public Chien() {
	
	}

	public void ramenerpouvoir() {
		this.setPower(pow);
	}

	public String getStringRepresentation() {
		return "C"+getJ().getC();
	}

	
}
