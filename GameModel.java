package comp303.mod3;

import java.util.Stack;

import comp303.mod2.SuitStackManager;
import comp303.mod3.WorkingStackManager.StackIndex;

/**
 * This class is responsible for managing all the necessary state for a game of solitaire.
 * It's implements the Singleton design pattern
 * */
public final class GameModel {
	
	/**
	 * In keeping with the Singleton Design Pattern, the field where the
	 * instance of the class is instantiated, is static and final
	 * */
	private static final GameModel INSTANCE = new GameModel();
	
	private SuitStackManager aSuitStacks = new SuitStackManager();
	private WorkingStackManager aWorkingStacks = new WorkingStackManager();
	private Deck aDeck = new Deck();
	private Stack<CardFly> aDiscard = new Stack<CardFly>();
	
	/**
	 * Indicates the all the suit stack indices that exist
	 * */
	public enum SuitStackIndex implements Location {
		FIRST, SECOND, THIRD, FOURTH
	}
	
	public enum WorkingStackIndex implements Location {
		FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH
	}
	
	/**
	 * private constructor, so that no outsider can instantiate an instance of this class
	 * */
	private GameModel() {}

	
	/**
	 * This interface exists for the sole purpose of accommodating the polymorphic
	 * needs of the Location parameter of the move(Card, Location) method, that can either
	 * take in a WorkingStackIndex of a SuitStackIndex
	 * */
	public interface Location {
		
	}
	/**
	 * Having this method means that the class controls who gets to create an
	 * instance of it
	 * @return the instance of the class
	 * */
	public static GameModel getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Initialises a new game by clearing all the working stacks, shuffling the deck,
	 * and placing cards on the working stack according to the rules of solitaire with the top of each stack
	 * visible
	 * */
	public void reset() {
		
		aDeck.shuffle();
		aDiscard.clear();
		aWorkingStacks.initialize(aDeck);
		aSuitStacks.initialize();
	}
	
	public void discard() {
		assert !aDeck.isEmpty();
		aDiscard.push(aDeck.draw());
	}
	
	/**
	 * Moves the card pCard from assumed legal position to the specified location pStack
	 * @param pCard the card we're interested in moving
	 * @param pStack where we want to move the card to
	 * */
	public void move(CardFly pCard, Location pStack) {
		/** 
		 * We need an exact type of the location to be specified, so that we can call the canMoveTo method on the location
		 * therefore we need a find(CardFly) method that will return either the SuitStack or Working Stack index of where the card is
		 * we first need to verify that the move is legal*/
		
	}
}
