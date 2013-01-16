import java.util.ListIterator;
import java.util.Scanner;

/**
 * @author Jonathan Ho
 * A class that will run the Blackjack game.
 */
public class blackJackDriver 
{
	private static Scanner input = new Scanner(System.in);
	private static String choice;
	private static int bankroll;
	static ListIterator<Card> deckIterator;
	static Card temp;
	static boolean is_necessary = true;
	
	// Returns value of the card dealt
	private static Card dealCard(ListIterator<Card> deckIterator)
	{
		temp = deckIterator.next();
		System.out.println(temp);
		return temp;
	}
	
	// Returns true if dealer didn't bust.
	// Returns false if dealer did bust.
	private static boolean dealerTurn(ListIterator<Card> deckIterator, CardHand dealerHand)
	{
		System.out.println("Dealer's turn:");
		//Loop until dealer hits soft 17 or bust occurs
		do
		{
			//Check if dealerHand value > 17
			if(dealerHand.handValue() > 16)
			{
				System.out.println("Dealer stands.");
				return true;
			}
			//Dealer must hit until value > 17 or bust occurs
			else
			{
				System.out.print("New card: ");
				dealerHand.add(dealCard(deckIterator));
				System.out.println("Dealer has: " + dealerHand.handValue());
			}
		} while(dealerHand.handValue() < 22);
		//Dealer busts
		System.out.println("Dealer bust!  You win.");
		return false;
	}
	
	// Returns false if player bust.
	// Returns true if player did not bust.
	private static boolean playerTurn(ListIterator<Card> deckIterator, CardHand playerHand)
	{
		System.out.println("Player's turn:");
		// Loops for player action
		while(playerHand.handValue() < 22)
		{
			choice = input.nextLine();
			if(choice.equalsIgnoreCase("hit"))
			{
				System.out.println("New card: ");
				playerHand.add(dealCard(deckIterator));
				if(playerHand.handValue() < 22)
					System.out.println("Player has: " + playerHand.handValue());
			}
			else if(choice.equalsIgnoreCase("stand"))
				return true;
			System.out.println("Options: [hit] [stand]");
		}
		System.out.println("Player bust!  You lose.");
		return false;
	}
	
	// Handles the beginning of the round
	private static void beginRound(ListIterator<Card> deckIterator, CardHand playerHand, CardHand dealerHand)
	{
		int counter = 0;
		System.out.println("Current hand:");
		for (counter = 0; counter < 2; counter++)
		{
			playerHand.add(dealCard(deckIterator));
		}
		System.out.println("Player has " + playerHand.handValue());
		
		System.out.println("Dealer hand:");
		for (counter = 0; counter < 2; counter++)
		{
			dealerHand.add(dealCard(deckIterator));
		}
		System.out.println("Dealer has " + dealerHand.handValue());
	}
	
	// Handles the end of the round
	private static void endRound(CardHand playerHand, CardHand dealerHand, int bankroll, int bet)
	{
		if(playerHand.handValue() == dealerHand.handValue())
			System.out.println("Push!");
		else if(playerHand.handValue() > dealerHand.handValue())
		{
			System.out.println("You win!");
			setBankroll(bankroll += bet);
		}
		else
		{
			System.out.println("You lose!");
			setBankroll(bankroll -= bet);
		}
		System.out.println("Current bankroll: " + getBankroll());
	}
	
	// Returns an int for user bet
	private static int getBet(int bankroll)
	{
		int bet; 
		// Loop until a valid bet is made
		do
		{
			//Displays bankroll available
			System.out.println("Current bankroll: " + bankroll);
			System.out.println("Enter bet:");
			bet = input.nextInt();
			if(bet > bankroll || bet <= 0)
				System.out.println("Bet must be greater than zero and at most " + bankroll);
		} while(bet > bankroll || bet <= 0);
		return bet;
	}
	
	// Prompts the user for continue
	// Returns true then player continues
	// Returns false then player discontinues
	private static boolean keepOnGoing(int bankroll)
	{
		if(bankroll == 0)
			System.out.println("You are broke leave my awesome casino!!!");
		else
		{
			System.out.println("Would you like to continue?");
			// Note: you can use string literals e.g. "yes" is being used
			return input.nextLine().equalsIgnoreCase("yes");
		}
		return false;
	}
	
	// Mutator for bankroll
	private static void setBankroll(int bankroll)
	{
		//blackJackDriver.bankroll used because bankroll was made private static I assume
		blackJackDriver.bankroll = bankroll;
	}
	
	// Accessor for bankroll
	private static int getBankroll()
	{
		return bankroll;
	}
	
	//Main of the program, runs game.
	public static void main(String[] args)
	{
		CardHand playerHand;
		CardHand dealerHand;
		System.out.println("Enter bankroll to start out with:");
		setBankroll(input.nextInt());
		
		System.out.println("== Jon's Awesome Casino ==");
		System.out.println("Initializing deck...");
		
		// Each iteration represents a game played
		do
		{
			int bet = 0;	/* bet placed by the player */
			playerHand = new CardHand(); // New hand being dealt for player
			dealerHand = new CardHand(); // New hand being dealt for dealer
			Deck deck = new Deck(); // Initializes deck
			deckIterator = deck.listIterator(); // Instantiates new iterator for new deck
			
			System.out.println("Shuffling deck...");
			bet = getBet(bankroll);
			beginRound(deckIterator, playerHand, dealerHand);
			/* if the player didn't bust */
			if(playerTurn(deckIterator, playerHand))
			{
				/* if the dealer didn't bust */
				if(dealerTurn(deckIterator, dealerHand))
					endRound(playerHand, dealerHand, bankroll, bet);
				else
					bankroll += bet;
			}
			else
				bankroll -= bet;
		} while(keepOnGoing(bankroll));

		System.out.println("Goodbye!");
		input.close(); // Cleaning up after usage
		System.exit(0);
	}
}
