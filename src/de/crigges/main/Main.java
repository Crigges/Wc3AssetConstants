package de.crigges.main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		try {
			SylkParser p = new SylkParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\unitui.slk"), 1, 3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
