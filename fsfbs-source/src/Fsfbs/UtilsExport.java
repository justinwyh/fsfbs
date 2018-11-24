package Fsfbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class UtilsExport {

public static void printToFile(String filepath, String input[]) {
		//
		try {
			PrintWriter writer;
			writer = new PrintWriter(filepath, "UTF-8");
			for(String s: input)
				writer.println(s);
			if(writer!=null)
				writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
}
public static void printToFile(String filepath, String input) {
	//
	try {
		PrintWriter writer;
		writer = new PrintWriter(filepath, "UTF-8");
		writer.print(input);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
}
	public static void appendToFile(String filepath, String input) throws IOException {
		 File file = new File(filepath);
		 FileWriter fr = new FileWriter(file, true);
		 fr.write(input+"\n");
		 fr.close();
	}


}
