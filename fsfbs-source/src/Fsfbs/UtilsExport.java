package Fsfbs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class UtilsExport {
	
<<<<<<< HEAD
public static void printToFile(String filepath, String input[]) {		//overwrite
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
=======
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
>>>>>>> c28bf3ee2d92973ae08cc7f5cb272c086073be7b
	}

<<<<<<< HEAD
public static void printToFile(String filepath, String input) {		//overwrite
	//
	try {	
		PrintWriter writer;
		writer = new PrintWriter(filepath, "UTF-8");
			writer.println(input);
			writer.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
}

public static void appendToFile(String filepath, String input) throws IOException {		//append
	 File file = new File(filepath);
	 FileWriter fr = new FileWriter(file, true);
	 fr.write(input);
	 fr.close();
}
=======
	public static void appendToFile(String filepath, String input) throws IOException {
		 File file = new File(filepath);
		 FileWriter fr = new FileWriter(file, true);
		 fr.write(input);
		 fr.close();
	}
>>>>>>> c28bf3ee2d92973ae08cc7f5cb272c086073be7b
}
