package bl;

public class PartieMangFactory extends BlAbstractFactory {

	
	public IAnimalMang getAMF() {
		return null;
	}

	
	public IJoueyrMang getJMF() {
		return null;
	}

	
	public IEchequierMang getEMF() {
		return null;
	}

	public IPartieMang getPMF() {
		return PartieMang.getInstance();
	}

}
