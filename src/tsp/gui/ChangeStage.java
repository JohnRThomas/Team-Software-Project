package tsp.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChangeStage {
	
	public String getNextStage(int currentStage, int max){
		currentStage +=1;
		String stageName = "stage" + currentStage;

		if (currentStage > max){
			stageName =null;
		}
		return stageName;
		
	}
	
	public int getKeyInfo(){
		Scanner wordScanner = null;
		try {
			wordScanner = new Scanner(new File("res/stages/keyInfo"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int stageCount =0;
		
		while(wordScanner.hasNext()){
			String word = wordScanner.nextLine();
			stageCount = Integer.parseInt(word);
		}
		return stageCount;
	}

}
