package rollSim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int minDice = 6;
		int maxDice = 1000;

		int tests = 100000;

		ArrayList<String> dataToWrite = new ArrayList<String>();
		String s = ("Dice, MiniCrit, DoubleMiniCrit, HalfCrit, HalfMiniCrit, Crit, CritMiniCrit");
		dataToWrite.add(s);
		System.out.println(s);

		for (int i = minDice; i <= maxDice; i ++) {
			double totalMiniCrit = 0;
			double totalDoubleMiniCrit = 0; 
			double totalHalfCrit = 0;
			double totalHalfMiniCrit = 0;
			double totalCrit = 0;
			double totalCritMiniCrit = 0;
			
			for (int k = 0; k < tests; k++) {
				totalMiniCrit += damage(i, 1);
				totalDoubleMiniCrit += damage(i, 2);
				
				//.5 is added because dice round up
				totalHalfCrit += damage((int)(.5+(i*1.5)), 0);
				//.5 is added because dice round up
				totalHalfMiniCrit += damage((int)(.5+(i*1.5)), 1);
				
				totalCrit += damage((i*2), 0);
				totalCritMiniCrit += damage((i*2), 1);
			}
			totalMiniCrit /= tests;
			totalDoubleMiniCrit /= tests;
			totalHalfCrit /= tests;
			totalHalfMiniCrit /= tests;
			totalCrit /= tests;
			totalCritMiniCrit /= tests;

			//String s = (i + ", " + total + ", " + (total - i));
			s = (i + ", " + totalMiniCrit + ", " +totalDoubleMiniCrit+ ", " +totalHalfCrit+ ", "+totalHalfMiniCrit+ ", "+totalCrit+ ", " +totalCritMiniCrit);
			dataToWrite.add(s);
			System.out.println(s);
		}

		writeToFile(dataToWrite);
	}

	private static int damage(int numDice, int explode) {
		dice Dice = new dice(numDice, explode);
		return Dice.totalDamage();
	}

	//info taken from http://beginnersbook.com/2014/01/how-to-write-to-file-in-java-using-bufferedwriter/
	//	and http://www.programcreek.com/2011/03/java-write-to-a-file-code-example/
	private static void writeToFile(ArrayList<String> dataToWrite) {
		BufferedWriter writer = null;
		try {
			File file = new File("A:\\Documents\\THE System\\minicrit_sim.txt");

			/*
			 * This logic will make sure that the file gets created if it is not
			 * present at the specified location
			 */
			if (!file.exists()) {
				file.createNewFile();
			}
			
			  FileWriter fw = new FileWriter(file);
			  writer = new BufferedWriter(fw);
			  
			  for (String s : dataToWrite){
					writer.write(s);
					writer.newLine();
			  }
			  
			  writer.close();
		          System.out.println("File written Successfully");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
