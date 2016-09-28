package comp303.mod3;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
/**
 * This class uses the FlyWeight Card class
 * 
 * What happens if I call pass in a copy of my deck to the foreach loop
 * why does it not get sorted? 
 * */

public class Deck implements Iterable<CardFly> , Cloneable{
  
	private final Stack<CardFly> aCards; 
  
  public Deck() {
	  aCards = new Stack<CardFly>();
  }
  
  public List<CardFly> getCards()
  {
	  return Collections.unmodifiableList(aCards);
  }
  
  //Now any changes to the contents of the list are not visible to the client
  /**
   * @return returns a read-only view of the list
   * */
  public List<CardFly> getCards1(){
	  ArrayList<CardFly> nDeck = new ArrayList<>();
	  
	  for(CardFly card : aCards) {
		  String cardID = card.getIDString();
		  nDeck.add(card.get(cardID)); 
	  }
	  
	  //nDeck.addAll(0, aCards);
	  return Collections.unmodifiableList(nDeck);
  }
  
  public Deck(Deck pDeck) {
	  aCards = new Stack<CardFly>();
	  for( CardFly card : pDeck.aCards) {
		  aCards.add(card.get(card.getIDString()));
	  }
  }
  
  
  
  /**
   * @return Iterator<CardF> is returned that iterates
   * over a sorted deck
   * */
 public Iterator<CardFly> createSortedIterator()
 {
	 ArrayList<CardFly> foo = new ArrayList<CardFly>(aCards);
	 Collections.sort(foo);
	 return foo.iterator();
 }
 
 /**
  * @return Iterator<CardF> is returned that iterates
  * over the deck
  * */
 public Iterator<CardFly> createIterator() 
 {
	 return aCards.iterator();
 }
 
  
  public void shuffle() 
  {
	  aCards.clear();
	  for( CardFly.Suit suit : CardFly.Suit.values()) 
	  {
		  for( CardFly.Rank rank : CardFly.Rank.values()) 
		  {
			  aCards.push(CardFly.get(rank, suit));
		  }
	  }
	  Collections.shuffle(aCards);
  }
  
  public boolean isEmpty() {
	  return aCards.isEmpty();
  }
  
  /**
   * @return CardFly - returns the card at the top of the deck
   * */
  public CardFly draw() {
	  return aCards.pop();
  }
  
  /** Sorting by Rank and Suit 
   * Order of Suits (in ascending): Clubs, Diamonds, Hearts, Spades
   * Order of Rank (in ascending): Ace, Two, Three, ...., King 
   * @return a sorted Deck*/
  
  public void sort(Comparator<CardFly> comp) {
	  
	  Collections.sort(aCards, comp);
			  
	
	 //Collections.sort(aCards, CardF.compareByRank());
	//Collections.sort(aCards, CardF.compareBySuit());
	 
  } 
  
  /**using Comparable interface to make Deck sortable */
  public void sortable() {
	  Collections.sort(aCards);
  }
  public static void main(String[] args) {
	Deck cards = new Deck();
	cards.shuffle();
	List<CardFly> deck = cards.getCards1();
	System.out.println(deck.toString());
	for(CardFly aCard : cards) {
		System.out.println(aCard);
	}
	
	System.out.println(deck);
	
  }
	
/*	Iterator<CardF> iterator = cards.createIterator();
	while(iterator.hasNext()) {
		System.out.println(iterator.next());
	}
	
	
	*/
  
 /**
  * @return int- returns size of the deck i.e. the number of cards in the deck
  * */
  public int size() {
	  return aCards.size();
  }
  @Override
public Deck clone() {
	try {
		Deck clone = (Deck) super.clone();
		Stack<Card> clonedStack = (Stack<Card>) aCards.clone();
		//clone.aCards = clonedStack;
		return clone;
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		return null;
	}
}

@Override
public Iterator<CardFly> iterator() {
	ArrayList<CardFly> cards = new ArrayList<>(aCards);
	Collections.sort(cards, CardFly.compareByRank());
	
	return cards.iterator();
}  

}