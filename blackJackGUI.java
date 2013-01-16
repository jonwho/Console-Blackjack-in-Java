import java.awt.Dialog;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// work in progress, most likely move this to Android project

/**
 * @author Jonathan Ho
 * A class that contains the GUI components of
 * the game Blackjack.
 */
public class blackJackGUI extends JFrame
{
	// Delete note later: Remember to instantiate all these objects inside the constructor and add them in order for them to show up!
	private static int bankroll;
	private static boolean tryAgain = true; // Make it true
	private static Dialog endState; // initially it is invisible, so setVisible(true) to have it appear try to add yes and no buttons to it
	private JButton hitButton, standButton, betButton, newGameButton;
	private JTextArea playerTA, dealerTA;
	private JScrollPane scrollpane; // by default the scroll pane will only show up when needed whether it's vertical or horizontal
	
	// When this class becomes instantiated the default
	// constructor needs to initialize components that are necessary
	// for this game to work.
	public blackJackGUI()
	{
		this.setTitle("Jon's Awesome Casino!!");
		this.setSize(700, 640);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Sets the bankroll to begin the game, get the desired amount from the user.
	private static void setBankroll(int bankroll)
	{
		blackJackGUI.bankroll = bankroll;
	}
	
	// Returns the bankroll the user has left.
	private static int getBankroll()
	{
		return blackJackGUI.bankroll;
	}
	
	// Returns the user bet, however need to check to see if bet
	// made by player is valid. Add in these checks later.
	private static int getBet(int bet)
	{
		return bet;
	}
	
	public static void main(String[] args)
	{
		new blackJackGUI();
	}
}
