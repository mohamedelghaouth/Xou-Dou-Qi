package bl;

public class AnilmalMangFactory extends BlAbstractFactory {

	
	public IAnimalMang getAMF() {
		return AnimalMang.getInstance(EchequierMang.getInstance());
	}

	public IJoueyrMang getJMF() {
	
		return null;
	}

	public IEchequierMang getEMF() {
		return null;
	}

	public IPartieMang getPMF() {
		return null;
	}

}
