package Fsfbs;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class UtilsExport {
public static void printToFile(String filepath, String[] input) {
	
	try {
		PrintWriter writer;
		writer = new PrintWriter(filepath, "UTF-8");
		for(String s : input){
			writer.println(s);
			if(writer!=null)
				writer.close();
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
}
}
