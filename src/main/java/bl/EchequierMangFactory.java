package bl;

public class EchequierMangFactory extends BlAbstractFactory {

	
	public IAnimalMang getAMF() {
		return null;
	}

	
	public IJoueyrMang getJMF() {
		return null;
	}

	
	public IEchequierMang getEMF() {
		return EchequierMang.getInstance();
	}

	public IPartieMang getPMF() {
		return null;
	}

}
