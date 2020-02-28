package Classes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math; 


public class Game_Process implements Interfaces.IHangman  {
	
	private String[] Dictionary = new String[20000]; // 20000 is the total number of words in Dictionary.
	private String wordGame ;  // the word should the user guess.
 	public int maxWrongGuesses ;
 	private static  int[] check  = new int [25] ; // this for check of correct guesses.      
 	private String inputs = " " ; // if a user add a particular char multiple of times so store intputs. 
 	private int noOfWords=0;
  	
 	public String[] readFile(String filePath) { // this function read the words from a file and store it in array of strings.
		int i =0 ;
		 String[] words = new String[20000];
		 BufferedReader objReader = null;
		  try {
		   String strCurrentLine;
		   objReader = new BufferedReader(new FileReader(filePath));   // path of the file that will be read with its extension.
		   while ((strCurrentLine = objReader.readLine()) != null) {
			 words[i++]=strCurrentLine ;
			 noOfWords++;
		   }

		  } catch (IOException e) {
		   e.printStackTrace();

		  } finally {
		   try {
		    if (objReader != null)
		     objReader.close();
		   } catch (IOException ex) {
		    ex.printStackTrace();
		   }
		  }
		  return words ;
	}
	
	
	public void setDictionary(String[] words) {
		Dictionary=words;
	}

	public String selectRandomSecretWord() {
		
		 int rand = (int)(Math.random()*(noOfWords-1) ) ; 
		 wordGame = Dictionary[rand];
		 return wordGame;
	}

	
	public String guess(Character c) throws Exception {
	
		c = Character.toLowerCase(c);
		if(!Character.isLowerCase(c)) // check validity of letter..if it isn't valid do nothing.
			return null ;
		int i ;
		boolean flag = true,guess=false ; // flag is a boolean variable to check if the user win .  
		for(i=0;i<wordGame.length();i++)
		{
			if(wordGame.charAt(i)==c)	
			{
				check[i]=1;
				guess=true;
			}
		}
		char[]temp=new char[wordGame.length()] ;
		for(i=0;i<wordGame.length();i++)
		{
			if(check[i]==1)
			{
				temp[i]=wordGame.charAt(i) ;
			}
			else
				{
					temp[i]='_' ;
					flag=false;
				}
		}
		if(!guess && inputs.indexOf(c)<0 )
			maxWrongGuesses--;		
	String wordGuess = new String(temp) ;
	inputs = inputs + c ;	
	if(flag || maxWrongGuesses==0) // if the user win or lose print a message in console and the function returns null. 
		{
			if(flag)
				System.out.println("\t\tCongratulations,You win.");
			else
				System.out.println("\t\tUnfortunately, you lose so try in another time.");
			
			return null ;   
		}
	else
		return wordGuess; 
			
	}

	
	public void setMaxWrongGuesses(Integer max) {		 
		 maxWrongGuesses =max ;
		 if(max <=0 || max==null)
			 maxWrongGuesses=1 ;
	}

}
