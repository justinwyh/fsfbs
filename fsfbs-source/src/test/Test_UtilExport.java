package test;
import static org.junit.jupiter.api.Assertions.*;

import Fsfbs.UtilsExport;
import org.junit.jupiter.api.Test;

import java.io.*;

class Test_UtilExport {

	//success printToFile[]
	@Test
	public void testUtilWrite_1() {
       boolean result = true;
       File f = new File("./Data/Membership/paragraph.txt");
       if(f.exists())
           f.delete();
        String[] s = new String[5];
        for (int i = 0; i < 5; i++) {
            s[i] = "paragraph";
        }
        UtilsExport.printToFile("./Data/Membership/paragraph.txt", s);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./Data/Membership/paragraph.txt"));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                if (!line.equals(s[i])) {
                    result = false;
                    break;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            result = false;
        } catch (IOException e) {
            result = false;
        }
        //return boolean true / f
        assertEquals(true, result);
    }

    //file not found printToFile[]
    @Test
    public void testUtilWrite_2() {
        boolean result = true;
        File f = new File("./Data/Membership/paragraph.txt");
        if(f.exists())
            f.delete();
        String[] s = new String[5];
        for (int i =0; i<5; i++){
            s[i] = "abcd";
        }
        UtilsExport.printToFile("./Data/Member/paragraph.txt", s);
        //logic check if file contains s
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./Data/Membership/paragraph.txt"));
            String line = reader.readLine();
            int i=0;
            while(line != null){
                if(!line.equals(s[i])){
                    result = false;
                    break;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e){
            result = false;
        } catch (IOException e){
            result = false;
        }
        //return boolean true / f
        assertEquals(false, result);
    }

    //success printToFile
    @Test
    public void testUtilWrite_3() {
        File f = new File("./Data/Membership/word.txt");
        if(f.exists())
            f.delete();
        String s = "a word";
        UtilsExport.printToFile("./Data/Membership/word.txt",s);
        //logic check if file contains s
        boolean result = true;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./Data/Membership/word.txt"));
            String line = reader.readLine();
            if(line != null){
                if(!line.equals(s)){
                    result = false;
                }
            }
        } catch (FileNotFoundException e){
            result = false;
        } catch (IOException e){
            result = false;
        }
        //return boolean true / f
        assertEquals(true, result);
    }

    //file not found printToFile
    @Test
    public void testUtilWrite_4() {
        File f = new File("./Data/Membership/word.txt");
        if(f.exists())
            f.delete();
        String s = "a word";
        UtilsExport.printToFile("./Data/Member/word.txt",s);
        //logic check if file contains s
        boolean result = true;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./Data/Membership/word.txt"));
            String line = reader.readLine();
            if(line != null){
                if(!line.equals(s)){
                    result = false;
                }
            }
        } catch (FileNotFoundException e){
            result = false;
        } catch (IOException e){
            result = false;
        }
        //return boolean true / f
        assertEquals(false, result);
    }

    //success appendToFile
    @Test
    public void testUtilWrite_5() {
        boolean result = true;
        File f = new File("./Data/Membership/append.txt");
        if(f.exists())
            f.delete();
        String[] s = new String[5];
        for (int i = 0; i < 5; i++) {
            s[i] = "append";
        }
        UtilsExport.printToFile("./Data/Membership/append.txt", s);
        BufferedReader reader;
        UtilsExport.appendToFile("./Data/Membership/append.txt", "append");
        try {
            reader = new BufferedReader(new FileReader("./Data/Membership/append.txt"));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                if (!line.equals("append")) {
                    result = false;
                    break;
                }
                i++;
                line = reader.readLine();
            }
            if(i!=6){
                result = false;
            }
        } catch (FileNotFoundException e) {
            result = false;
        } catch (IOException e) {
            result = false;
        }
        //return boolean true / f
        assertEquals(true, result);
    }

    //fail appendToFile
    @Test
    public void testUtilWrite_6() {
        boolean result = true;
        File f = new File("./Data/Membership/append.txt");
        if(f.exists())
            f.delete();
        String[] s = new String[5];
        for (int i = 0; i < 5; i++) {
            s[i] = "append";
        }
        UtilsExport.printToFile("./Data/Membership/append.txt", s);
        BufferedReader reader;
        UtilsExport.appendToFile("./Data/Member/append.txt", "append");
        try {
            reader = new BufferedReader(new FileReader("./Data/Membership/append.txt"));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                if (!line.equals("append")) {
                    result = false;
                    break;
                }
                i++;
                line = reader.readLine();
            }
            if(i!=6){
                result = false;
            }
        } catch (FileNotFoundException e) {
            result = false;
        } catch (IOException e) {
            result = false;
        }
        //return boolean true / f
        assertEquals(false, result);
    }

}
