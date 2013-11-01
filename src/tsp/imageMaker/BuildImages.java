package tsp.imageMaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Matthew Johnson
 *loads a file and gets background info
 *
 */
public class BuildImages {
	
	public int imageCount = 0;
	
	/**
	 * 
	 * @param name			name of file to load (does not need res/stages/)
	 * @param imageMaker	the imageMaker that is going to have the list of images
	 * @return				set imageMaker input == to this to update it
	 */
	public MakeImages getFile(String name, MakeImages imageMaker){
		String	fileName = "res/stages/" + name;
		Scanner wordScanner = null;
		try {
			wordScanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int imageCount = 0;

		int j = 0;
		int x[];
		int y[];
		String imageNames[];
		boolean collide[];
		while(wordScanner.hasNext()){
			
			int i = 0;
			String word = wordScanner.nextLine();
			
			if (word.compareTo("Background")==0){
				word = wordScanner.next();
				
				imageCount = Integer.parseInt(word);
				x  = new int[imageCount];
				y  = new int[imageCount];
				imageNames = new String[imageCount];
				collide = new boolean[imageCount];
				
				while(true){
					word = wordScanner.next();
					
					if(word.compareTo("End") ==0)break;
					
					if (i == 0){
						imageNames[j] = word;
					}
					else if (i == 1){
						x[j] = Integer.parseInt(word);
					}
					else if (i == 2){
						y[j] = Integer.parseInt(word);

					}
					else if (i == 3){
						if(word.compareTo("T") == 0){
							collide[j] = true;
						}
						else if(word.compareTo("F") ==0){
							collide[j] = false;
						}
						i = -1;
						j+=1;
					}
					i+=1;
					
				}
				imageMaker = new MakeImages();
				imageMaker.setSize(imageCount);
				
				for(int k =0; k <j; k++){
					imageMaker.setBaseBackgroundImage(k, x[k], y[k], imageNames[k], collide[k]);
				}
				
			}//end of if
			
		}// end of scanner
		
		wordScanner.close();
		return imageMaker;	
		

	}

}
