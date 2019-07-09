package gui;

import java.util.ArrayList;
import java.util.Scanner;

import bl.BlAbstractFactory;
import bl.EchequierMang;
import bl.IEchequierMang;
import bo.Animal;
import bo.Echequier;
import bo.Position;

public class Fenetre {

	static IEchequierMang E =BlAbstractFactory.getFactory(BlAbstractFactory.ECHEQUIER).getEMF();
	
	static public void showEchequier(Echequier e) {

		ArrayList<Integer> rline = new ArrayList<Integer>();	
		rline.add(3);
		rline.add(4);
		rline.add(5);
		ArrayList<Integer> rcolumn = new ArrayList<Integer>() ;	
		rcolumn.add(1);
		rcolumn.add(2);
		rcolumn.add(4);
		rcolumn.add(5);
		
		System.out.println("Score joueur1  : "  + e.getJ1().getScore());
		System.out.println("Score joueur2  : "  + e.getJ2().getScore());

		for (int i = 8; i >= 0; i--) 
		{

			System.out.println(" ----------------------------------------------------------------------------------------------------------");
			System.out.print(i);
			for (int j = 0; j < 7; j++) 
			{
				Position P=new Position(j,i);
				Animal a = E.getAnimalAt(e,P);
				

				if (a == null) 
				{
					if((e.getJ1().getSanctuaire().equals(P))||(e.getJ2().getSanctuaire().equals(P))){
						 System.out.print("|s             ");
					}
					else if((e.getJ1().getPiege().contains(P))||(e.getJ2().getPiege().contains(P))){
						 System.out.print("|p             ");
					}
					else if((rcolumn.contains(Integer.valueOf(P.getX())))
							&&(rline.contains(Integer.valueOf(P.getY())))) {
						 System.out.print("|r             ");
					}
					else System.out.print("|              ");
				}
				else 
				{
					if((e.getJ1().getSanctuaire().equals(P))||(e.getJ2().getSanctuaire().equals(P))){
					     System.out.print("|s           " + a.getStringRepresentation());
					}
					else if((e.getJ1().getPiege().contains(P))||(e.getJ2().getPiege().contains(P))){
						 System.out.print("|p           " + a.getStringRepresentation());
					} 
					else if((rcolumn.contains(Integer.valueOf(P.getX())))
							&&(rline.contains(Integer.valueOf(P.getY())))) {
						 System.out.print("|r           " + a.getStringRepresentation());
					}
					else 
						 System.out.print("|            " + a.getStringRepresentation());

				}

			}
			
			if(i==6) {
				System.out.print("|"+i);
				System.out.println("            "+ "entrer q pour quitter");
			}else
				System.out.println("|"+i);
		}

		System.out.println(" ----------------------------------------------------------------------------------------------------------");
		
		for (int j = 0; j < 7; j++) {
			System.out.print("       "+j+"       ");
		}
		System.out.println("\n");
		
		if(E.getJoueurAyantLeTour(e).getC().equals("r")) {
			System.out.println("c'est le tour du joueur Rouge");
		}
		else { 
			System.out.println("c'est le tour du joueur Noire");
		}
		System.out.println("nombre d'iteration ="+ e.getNmbiter());
		System.out.println(" ");

	}

	static public int showMenu() {

		Scanner s = new Scanner(System.in);

		System.out.println("Entrer votre choix");
		System.out.println("1- Machine contre Machine");
		System.out.println("2- Joueur contre Machine");
		System.out.println("3- Joueur contre Joueur");

		return s.nextInt();

	}

	static public int showMenu2() {

		Scanner s = new Scanner(System.in);

		System.out.println("Entrer votre choix");
		System.out.println("1- nouvelle Partie");
		System.out.println("2- continuer une Partie");
		
		return s.nextInt();

	}
	
}
