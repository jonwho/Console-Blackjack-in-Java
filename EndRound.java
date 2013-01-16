/**
* @author Jonathan Ho
* Enum that represents the state that the end of the round can take.
* For now this is only a string representation.
*/

// work in progress, not necessary for game as is

public enum EndRound 
{
	WIN("You win!"), LOSE("You lose!"), PUSH("Push!"), NOMONEY("No money? Get out!!");
	final String s;
	EndRound(String s) { this.s = s; }
	public String toString() { return this.s; }
}
