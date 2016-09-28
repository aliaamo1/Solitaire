package comp303.mod3;

import java.util.Comparator;

/** This class implements the Flyweight design pattern: 
* there can only ever be one instance of a card that 
* represents a specific real-world playing card (such as ace
* of spaces). In the absence of serialization and reflection,
* this ensures that the behavior of the == operator is identical 
* to that of the equals method when two card arguments are 
* provided.
*/
public final class CardFly implements Comparable<CardFly>
{
	// Indexed by suit, then rank
	private static final CardFly[][] CARDS = new CardFly[Suit.values().length][];
	
	// Create the flyweight objects
	static
	{
		for( Suit suit : Suit.values() )
		{
			CARDS[suit.ordinal()] = new CardFly[Rank.values().length];
			for( Rank rank : Rank.values() )
			{
				CARDS[suit.ordinal()][rank.ordinal()] = new CardFly(rank, suit);
			}
		}
	}
	
	/**
	 * Represents the rank of the card.
	 */
	public enum Rank 
	{ ACE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }
	
	/**
	 * Represents the suit of the card.
	 */
	public enum Suit 
	{ CLUBS, DIAMONDS, HEARTS, SPADES }
	
	private final Rank aRank;
	private final Suit aSuit;
	private boolean visible;
	
	private CardFly(Rank pRank, Suit pSuit )
	{
		aRank = pRank;
		aSuit = pSuit;
	}
	
	/**
	 * @param pRank The rank of the card (from ace to kind)
	 * @param pSuit The suit of the card (clubs, diamond, spades, hearts)
	 * @return The card object representing the card with pRank and pSuit
	 * this is the factory method. It elminates the user's need to use the "new" 
	 * keyword to create an instance of the FlyWeight class.
	 * Factory methods are usually static. Factory methods return an object
	 * - they can only access static fields
	 */
	public static CardFly get(Rank pRank, Suit pSuit)
	{
		assert pRank != null && pSuit != null;
		return CARDS[pSuit.ordinal()][pRank.ordinal()];
	}
	
	/**
	 * @param pId The id string for the card. This is needs to have
	 * been produced by Card.getIDString to be considered a
	 * valid input to this method.
	 * @return The card object with id string pId
	 */
	public static CardFly get( String pId )
	{
		assert pId != null;
		int id = Integer.parseInt(pId);
		return get(Rank.values()[id % Rank.values().length],
				Suit.values()[id / Rank.values().length]);
	}
	
	/**
	 * Obtain the rank of the card.
	 * @return An object representing the rank of the card.
	 */
	public Rank getRank()
	{
		return aRank;
	}
	
	/**
	 * @param pCard The card to compare against
	 * @return True if and only if pCard's suit is of the same color as 
	 * this card.
	 */
	public boolean sameColorAs(CardFly pCard)
	{
		assert pCard != null;
		if( getSuit() == Suit.DIAMONDS || getSuit() == Suit.HEARTS )
		{
			return pCard.getSuit() == Suit.DIAMONDS || pCard.getSuit() == Suit.HEARTS;
		}
		else
		{
			return pCard.getSuit() == Suit.CLUBS || pCard.getSuit() == Suit.SPADES;
		}
	}
	
	/**
	 * @return A string uniquely representing this card. The string
	 * format is not specified except that it is fully compatible
	 * with the format expected by Card.get(String).
	 */
	public String getIDString()
	{
		return Integer.toString(getSuit().ordinal() * Rank.values().length + getRank().ordinal());
	}
	
	/**
	 * Obtain the suit of the card.
	 * @return An object representing the suit of the card 
	 */
	public Suit getSuit()
	{
		return aSuit;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return aRank + " of " + aSuit;
	}

	public static Comparator<CardFly> compareByRank() {
		return new
				Comparator<CardFly>()
				{
			      public int compare(CardFly c1, CardFly c2) {
			    	  if(c1.aRank == c2.aRank) {
			    		  return c1.aSuit.ordinal() - c2.aSuit.ordinal();
			    	  }
			    	  return c1.aRank.ordinal() - c2.aRank.ordinal();
			      }
				};
	}

	@Override
	public int compareTo(CardFly pCard) {
		// TODO Auto-generated method stub
		return this.getRank().ordinal() - pCard.getRank().ordinal();
	}
	
	/**
	 * @return Has the effect of making the card visible
	 * */
	public void makeVisible() {
		this.visible = true;
	}
}
