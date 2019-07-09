package bl;


public abstract class BlAbstractFactory {
	
	
	public static final String PARTIE = "Partie";
	public static final String ECHEQUIER = "Echequier";
	public static final String JOUEUR = "Joueur";
	public static final String ANIMAL = "animal";
	

	public static BlAbstractFactory getFactory(String s) {

		
		if (PARTIE.equals(s)) {
			return new PartieMangFactory();
		}
		if (ECHEQUIER.equals(s)) {

			return new EchequierMangFactory();
		}
		if (JOUEUR.equals(s)) {
			return new JoueurMangFactory();
		}
		if (ANIMAL.equals(s)) {
			return new AnilmalMangFactory();
		}
		return null;


	}
	
	public abstract  IAnimalMang getAMF();
	public abstract  IJoueyrMang getJMF();
	public  abstract IEchequierMang getEMF();
	public abstract IPartieMang getPMF();

	

}
