package bo;

import javax.persistence.Entity;

@Entity(name = "chat")

public class chat extends Animal {

	public static int pow=2;
	
	public chat( Position pos, Joueur J) {
		super(pow, pos, J);
	}

	public chat() {
		
	}

	public void ramenerpouvoir() {
		this.setPower(pow);
	}
	
	public String getStringRepresentation() {
		return "c"+getJ().getC();
	}

	
	
}
