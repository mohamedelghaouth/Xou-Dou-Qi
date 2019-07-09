package bl;

public class JoueurMangFactory extends BlAbstractFactory {

	
	public IAnimalMang getAMF() {
		return null;
	}

	
	public IJoueyrMang getJMF() {
		return JoueurMang.getInstance();
	}

	
	public IEchequierMang getEMF() {
		return null;
	}

	
	public IPartieMang getPMF() {
		return null;
	}

}
