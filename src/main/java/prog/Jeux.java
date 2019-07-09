package prog;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bl.AnimalMang;
import bl.BlAbstractFactory;
import bl.EchequierMang;
import bl.IAnimalMang;
import bl.IEchequierMang;
import bl.IJoueyrMang;
import bl.IPartieMang;
import bl.ImpossibleDeplacementException;
import bl.JoueurMang;
import bl.PartieMang;
import bo.Animal;
import bo.Echequier;
import bo.Partie;
import bo.Joueur;
import bo.Position;
import gui.Fenetre;


public class Jeux {
	
	static IPartieMang P =BlAbstractFactory.getFactory(BlAbstractFactory.PARTIE).getPMF();
	static IEchequierMang E =BlAbstractFactory.getFactory(BlAbstractFactory.ECHEQUIER).getEMF();
	static IJoueyrMang J =BlAbstractFactory.getFactory(BlAbstractFactory.JOUEUR).getJMF();
	static IAnimalMang A =BlAbstractFactory.getFactory(BlAbstractFactory.ANIMAL).getAMF();

	
	// initialiser l'échequier
		
		
		public static void playWithMachine(Partie p) throws Exception {
			Scanner s = new Scanner(System.in);

			  Echequier echequier = p.getEchequier();


			Fenetre.showEchequier(echequier);

			while (P.gamenotover(p)) 
			{

				// Lire la commande au clavier
				String commande = s.nextLine();
				boolean correctMove = false;

				// Véifier que la commande a été correctement saisie
				Matcher m = Pattern.compile("^([R,c,l,C,P,T,L,E][rn]) (\\d{1}) (\\d{1})$").matcher(commande);

				if (m.matches()) 
				{

					String pieceString = m.group(1);
					int X=Integer.parseInt(m.group(2));
					int Y=Integer.parseInt(m.group(3));
					Position P=new Position(X,Y);
					
					Joueur j=E.getJoueurAyantLeTour(echequier);
					Animal piece = J.getAnimalWithLabel(j,pieceString);
					
					if (piece != null) 
					{
						try
						{
							A.sedeplace(piece,P);
							E.incrementer(echequier);
							correctMove = true;
							
						} 
						catch (ImpossibleDeplacementException e) 
						{
							correctMove = false;
							System.out.println(e.getMessage());
						}
					} 
					else
					{
						System.out.println("La piece n'existe plus");

					}

				}
				else if ("exit".equals(commande))
				{
					P.afficherresultat(p);
					System.exit(0);
				}
				
				else if ("q".equals(commande))
				{
					System.out.println("voulez vous saugarder le jeux: ");
					do {
					System.out.println("entrer: o/n");
					if(s.next().equals("o")) {
					P.savePartie(p);
					P.afficherresultat(p);
					System.exit(0);
					}
					else if(s.next().equals("n"))	System.exit(0);
					
					}while((!s.next().equals("o"))|(!s.next().equals("n")));
				}
				
				else 
				{
					System.out.println("commande incorrecte");
				}
				Fenetre.showEchequier(echequier);
				if (P.gamenotover(p)==false) 
				{
					P.afficherresultat(p);
					System.exit(0);
				}
				if (correctMove) 
				{
					// Attendre un peu
					Thread.sleep(4000);
					E.randomDeplacement(echequier);

					Fenetre.showEchequier(echequier);
				}
				 
			}
			P.afficherresultat(p);
			System.exit(0);
			
		}

		public static void playWithHuman(Partie p) throws Exception {
			Scanner s = new Scanner(System.in);
			  Echequier echequier = p.getEchequier();

			Fenetre.showEchequier(echequier);

			while (P.gamenotover(p)) 
			{

				// Lire la commande au clavier
				String commande = s.nextLine();

				// Véifier que la commande a été correctement saisie
				Matcher m = Pattern.compile("^([R,c,l,C,P,T,L,E][rn]) (\\d{1}) (\\d{1})$").matcher(commande);

				if (m.matches()) 
				{

					String pieceString = m.group(1);
					int X=Integer.parseInt(m.group(2));
					int Y=Integer.parseInt(m.group(3));
					Position P=new Position(X,Y);

					try 
					{
						Joueur j=E.getJoueurAyantLeTour(echequier);
						Animal piece = J.getAnimalWithLabel(j,pieceString);
						
						if (piece != null) 
						{
							A.sedeplace(piece,P);
							E.incrementer(echequier);

						} 
						else 
						{
							System.out.println("La piece n'existe plus");

						}
					} 
					catch (ImpossibleDeplacementException e) 
					{
						System.out.println(e.getMessage());
					}

				} 
				else if ("exit".equals(commande))
				{
					P.afficherresultat(p);
					System.exit(0);
				}
				else if ("q".equals(commande))
				{
					String ent=null;
					System.out.println("voulez vous saugarder le jeux: ");
					do {
					System.out.println("entrer: o/n");
					 ent=s.next();
					if(ent.equals("o")) {
					P.savePartie(p);
					P.afficherresultat(p);
					System.exit(0);
					}
					else if(ent.equals("n"))	System.exit(0);
					
					}while((!ent.equals("o"))|(!ent.equals("n")));
				}
				else 
				{
					System.out.println("commande incorrecte");
				}

				Fenetre.showEchequier(echequier);
				
			}
			P.afficherresultat(p);
			System.exit(0);
		}

		public static void playRadomGame(Partie p) throws Exception {
			  Echequier echequier = p.getEchequier();

			Fenetre.showEchequier(echequier);

			while (P.gamenotover(p))
			{
				Thread.sleep(4000);
				List<Animal> lPieces =E.getJoueurAyantLeTour(echequier).getL();
				E.randomDeplacement(echequier,lPieces);

				Fenetre.showEchequier(echequier);

				// Attendre un peu
				Thread.sleep(3000);
				
				if (P.gamenotover(p)==false) 
				{
					P.afficherresultat(p);
					System.exit(0);
				}
				
				
				
				 lPieces =E.getJoueurAyantLeTour(echequier).getL();
				 E.randomDeplacement(echequier,lPieces);

					Fenetre.showEchequier(echequier);
			
			}
			P.afficherresultat(p);
			System.exit(0);
		}
		
		public static void main(String[] args) throws Exception {
			
			
			List<Partie>l=P.getALL();
			int choix1 = Fenetre.showMenu2();
			Scanner s = new Scanner(System.in);
			
			switch (choix1) {
			
			case 1:
				String nomj=null;
				boolean b=true;
				do {
					System.out.println("entrer le nom du nouvelle jeux");
					b=true;
					nomj=s.nextLine();
					for(Partie g:l)
					{
						if(g.getNom().equals(nomj)) {
							System.out.println("ce nom existe deja");
							b=false;
							System.out.println("voila les noms des partie qui existe deja existe deja");
							for(Partie q:l)
							{System.out.println(q.getId()+"- "+q.getNom());}
							
							break;
						}
					}
					
					if((nomj.equals(""))) {
						System.out.println("donner un nom ; le nom est obligatoire");
						b=false;
					}	
					
					}while(b==false);
				Partie p=new Partie(nomj);

					int choix = Fenetre.showMenu();
					p.setType(choix);
					switch (choix) 
						{
							case 1:
									playRadomGame(p);
									break;
							case 2:
								playWithMachine(p);
								break;
							case 3:
								playWithHuman(p);

								break;
						}
			   break;
			case 2:
				Partie P=null;
				boolean B=true;
				do {
					for(Partie g:l)
					{System.out.println(g.getId()+"- "+g.getNom());}
					
					System.out.println("entrer l'id du jeux");
					int id=s.nextInt();

					for(Partie g:l)
					{
						if(g.getId()==id) {
							P=g;
							break;
						}
					}
					if(P==null) B=false;
					
					}while(B==false);
								
				switch (P.getType()) 
				{
					case 1:
							playRadomGame(P);
							break;
					case 2:
						playWithMachine(P);
						break;
					case 3:
						playWithHuman(P);

						break;
				}
				break;
			}

		}

		
		
		

}
