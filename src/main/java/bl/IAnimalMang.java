package bl;

import java.util.List;

import bo.Animal;
import bo.Position;

public interface IAnimalMang {

	void sedeplace(Animal A, Position P) throws ImpossibleDeplacementException;

	List<Position> getPossibleMoves(Animal A);

	void randomDeplacement(Animal A);

}