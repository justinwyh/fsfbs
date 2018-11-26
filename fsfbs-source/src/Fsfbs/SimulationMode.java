package Fsfbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimulationMode {
	public static boolean getSimulationMdoe() throws IOException {
		return !UtilsLoadconfig.getConfig("runSimulationMode").equals("0");
	    }
}

