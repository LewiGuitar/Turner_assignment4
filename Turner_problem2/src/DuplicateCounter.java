import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicateCounter {
	
	private Map<String, Integer> wordCounter;
	private boolean duplicate;
	private String tmpWord;
	
	DuplicateCounter(){
		wordCounter = new HashMap<String, Integer>();
	}
	
	public void count(String dataFile) {
		Scanner in = null;		
		
		try {
			in = new Scanner(Paths.get(dataFile));
		} catch (IOException e) {			
			System.out.println("Sorry, but problem2.txt was not found");
			System.exit(404);
		}
		
		while(in.hasNext()) {
			tmpWord = in.next();
			duplicate = false;
			
			wordCounter.forEach((k, v) -> {
				if(k.toLowerCase().matches(tmpWord.toLowerCase())) {
					duplicate = true;
					System.out.println(k + v);
					int newVal = v+1;
					wordCounter.replace(k, newVal);					
				}
			});
			if(!duplicate) {
				wordCounter.put(tmpWord, 1);
			}
		}
		System.out.println(wordCounter.toString());
		in.close();
	}
	
	public void write(String outputFile) {
		try {
			FileWriter writer = new FileWriter(outputFile);
			wordCounter.forEach((k,v) ->{
				try {
					writer.write(k + ": found " + v + " time(s) \n");
				} catch (IOException e) {
					System.out.println("Failed to write to: " + outputFile);
					e.printStackTrace();
					System.exit(406);
				}
			});
			writer.close();
		} catch (IOException e) {
			File newFile = new File(outputFile);
			try {
				newFile.createNewFile();
			} catch (IOException e1) {				
				System.out.println("Could neither find, nor create file:" + outputFile);
				e1.printStackTrace();
				System.exit(405);
			}
		}
	}
}
