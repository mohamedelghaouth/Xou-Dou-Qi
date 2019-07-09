package bo;

import javax.persistence.Entity;

@Entity(name = "Loup")

public class Loup extends Animal {
 
	public static int pow=3;

	public Loup(Position pos, Joueur J) {
		super(pow, pos, J);
	}


	
	public Loup() {
	
	}



	public void ramenerpouvoir() {
		this.setPower(pow);
	}

	public String getStringRepresentation() {
		return "l"+getJ().getC();
	}

	
	
}
