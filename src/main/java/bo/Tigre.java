package bo;

import java.util.ArrayList;

import javax.persistence.Entity;

import bl.EchequierMang;
import bl.ImpossibleDeplacementException;

@Entity(name = "Tigre")

public class Tigre extends Animal {
	 
		public static int pow=6;

		public Tigre(Position pos, Joueur J) {
			super(pow, pos, J);
		}
		
		public Tigre() {
			
		}

		public void ramenerpouvoir() {
			this.setPower(pow);
		}

		public String getStringRepresentation() {
			return "T"+getJ().getC();
		}

		
		public  void isPossibleMove(Position P) throws ImpossibleDeplacementException{
			//ces deux liste contiennent respectivement les indices des lignes 
			// et des colonnes des rivieres
			ArrayList<Integer> rline = new ArrayList<Integer>();	
			rline.add(3);
			rline.add(4);
			rline.add(5);
			ArrayList<Integer> rcolumn = new ArrayList<Integer>() ;	
			rcolumn.add(1);
			rcolumn.add(2);
			rcolumn.add(4);
			rcolumn.add(5);
			ArrayList<Integer> rfrontiere = new ArrayList<Integer>() ;	
			rfrontiere.add(0);
			rfrontiere.add(3);
			rfrontiere.add(6);
			//interdir le deplacement diagonale 
			if(((Math.abs(P.getX()-pos.getX())!=0))&&(Math.abs(P.getY()-pos.getY())!=0)){
				throw new ImpossibleDeplacementException("le deplacement diagonale est interdit");
			}
			//interdir un deplacement superieure a un carreau sauf pour le saut des rivieres et en abssence d'un rat sur le chemin
			if(((Math.abs(P.getX()-pos.getX())>1))|(Math.abs(P.getY()-pos.getY())>1))
			{
				if(((!rfrontiere.contains(Integer.valueOf(pos.getX())))
						|(!rline.contains(Integer.valueOf(P.getY())))
						|(Math.abs(P.getY()-pos.getY())!=0))
						&&((Math.abs(P.getX()-pos.getX())!=0))|
						(!rcolumn.contains(Integer.valueOf(P.getX())))|
						((pos.getY())!=2)&(pos.getY())!=6)
				{
					throw new ImpossibleDeplacementException("sauf pour un saut du riviere,le deplacement superieure a un carreau est interdit");
				}
				else 
				{
					Position p,p1,p2;
					if ((Math.abs(P.getY()-pos.getY())==0))
					{
						int Y=P.getY();
						if((P.getX()-pos.getX())<0)
						{
							p=new Position(pos.getX()-1,Y);
							 p1=new Position(pos.getX()-2,Y);
						}
						else 
						{
							p=new Position(pos.getX()+1,Y);
							 p1=new Position(pos.getX()+2,Y);
						}
						if((EchequierMang.getInstance().getAnimalAt(j.getE(),p)!=null)||(EchequierMang.getInstance().getAnimalAt(j.getE(),p1)!=null)) 
						{
							throw new ImpossibleDeplacementException("on peut pas sauter la riviere si on a un Rat sur le chemin");
						}
					}
					if(Math.abs(P.getX()-pos.getX())==0) 
					{
						int X=pos.getX();
						p=new Position(X,3);
						p1=new Position(X,4);
						p2=new Position(X,5);
						if((EchequierMang.getInstance().getAnimalAt(j.getE(),p)!=null)||(EchequierMang.getInstance().getAnimalAt(j.getE(),p1)!=null)||(EchequierMang.getInstance().getAnimalAt(j.getE(),p2)!=null)) 
						{
							throw new ImpossibleDeplacementException("on peut pas sauter la riviere si on a un Rat sur le chemin");
						}
					}
				}
			}
			//interdir le deplacement a vers la riviere 
			if((rcolumn.contains(Integer.valueOf(P.getX())))&&(rline.contains(Integer.valueOf(P.getY())))) {
				throw new ImpossibleDeplacementException("le deplacement a traver la riviere est interdit");
			}
			//interdir le deplacement  vers  le sanctaire
			if((j.getSanctuaire().equals(P))) {
				throw new ImpossibleDeplacementException("le deplacement  vers son propre le sanctaire est interdit");
			}
			}
	}
