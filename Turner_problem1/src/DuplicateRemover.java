import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DuplicateRemover {
	
	private ArrayList<String> uniqueWords;
	private boolean duplicate;

	
	DuplicateRemover(){
		uniqueWords = new ArrayList<String>();
	}
	
	public void remove(String dataFile) {
		Scanner in = null;
		String tmpWord;
		
		try {
			in = new Scanner(Paths.get(dataFile));
		} catch (IOException e) {			
			System.out.println("Sorry, but problem1.txt was not found");
			System.exit(404);
		}
		
		while(in.hasNext()) {
			tmpWord = in.next();
			duplicate = false;
			for (String word: uniqueWords) {
				if(word.toLowerCase().matches(tmpWord.toLowerCase())) {
					duplicate = true;
					break;
				}
			}
			if(!duplicate) {
				uniqueWords.add(tmpWord);
			}
		}					
		in.close();
	}
	
	public void write(String outputFile) {		
		try {
			FileWriter writer = new FileWriter(outputFile);
			System.out.println("All unique words found are: \n\n");
			for(int i = 0; i < uniqueWords.size(); i++) {
				writer.write(uniqueWords.get(i) + "\n");
			}				
			writer.close();				
		} 
		catch (IOException e) {			
			try {
				File newFile = new File(outputFile);				
				newFile.createNewFile();					
			} 				
			catch (IOException e1) {
				System.out.println("Could neither find, nor create file:" + outputFile);
				e1.printStackTrace();
				System.exit(405);
			}						
		}
	}
}
