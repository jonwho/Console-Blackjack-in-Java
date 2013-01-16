import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Ho
 * A class to represent a hand of cards. It can be for a dealer or player.
 */
public class CardHand
{
	List<Card> cardHand = new ArrayList<Card>();
	private int value = 0;
	
	// Instantiating this class means a new hand is dealt
	public CardHand()
	{
		this.value = 0;
	}
	
	// Adds a new card to the end of the list, it automatically resizes itself
	// to accommodate for more elements.
	// At the same time add the card value to "value" so that overall hand value
	// can be found.
	public void add(Card newCard)
	{
		cardHand.add(newCard);
		this.value += newCard.rank().getValue();
	}
	
	// Returns the removed card from the hand at the specified index.
	// Substracts the value from the overall hand. Although that's
	// kind of pointless and totally unnecessary in blackjack
	// since you can't remove a card anyways... uh...
	// in future updates may want to create separate acessors
	// and mutators anyways... just a thought.
	public Card remove(int index)
	{
		this.value -= cardHand.get(index).rank().getValue();
		return cardHand.remove(index);
	}
	
	// Returns the size of the ArrayList
	// In this case it returns how many cards there are.
	public int size()
	{
		return cardHand.size();
	}
	
	// Returns the total value of the hand
	public int handValue()
	{
		return this.value;
	}
}