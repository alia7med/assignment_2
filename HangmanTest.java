package Testing;
import java.util.Scanner;
import Classes.Game_Process;

public class HangmanTest {
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner (System.in);
		Game_Process obj = new Game_Process() ;
		String path= "D:\\Dictionary.txt"; // Path of file I want to read from .
		obj.setDictionary(obj.readFile(path)); 
		obj.setMaxWrongGuesses(6); // it is optional and I made it as in the game.
		String secretWord = obj.selectRandomSecretWord();
		System.out.printf("Note :  Max wrong guesses = 8...secret word to guess and test various scenarios is : %s.\n",secretWord);
		while(obj.maxWrongGuesses>0)
		{
			System.out.print("Please Enter the Character :  ");
			Character ch = input.next().charAt(0) ;
			String word =obj.guess(ch);
			if(word==null && Character.isLowerCase(ch))
			{
			   System.out.printf("\t Secret Word : %s \n",secretWord);
				break ;
			}
			if(word!=null)
			System.out.printf("\t Your guess : %s  \t max Wrong Guesses = %d \n",word,obj.maxWrongGuesses);
		}
	
	} 
}