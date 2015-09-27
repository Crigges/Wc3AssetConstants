package de.crigges.main;

import java.io.File;
import java.io.FileNotFoundException;

import de.crigges.main.SylkParser.Entry;

public class Main {

	public static void main(String[] args) {
		try {
			SylkParser p = new SylkParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\unitui.slk"), 1, 3);
			UnitStringParser par = new UnitStringParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\unitstrings\\comb.txt"));
			while(true){
				Entry e = p.next();
				String name = par.find(e.id);
				System.out.println("Path: " + e.path + "  |  Name: " + name);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
