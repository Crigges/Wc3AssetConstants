package de.crigges.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import de.crigges.main.SylkParser.Entry;

public class Main {

	public static void main(String[] args) {
		try {
			SylkParser p = new SylkParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\unitui.slk"), 1, 3);
			UnitStringParser par = new UnitStringParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\unitstringseng\\comb.txt"));
			ConstantList list = new ConstantList();
			try{
				while(true){
					Entry e = p.next();
					String name = par.find(e.id);
					list.add(e.path, name);
				}
			}catch(NoSuchElementException e){
				PackageGenerator gen = new PackageGenerator(new File("Assets.wurst"), "Assets");
				list.writeToPackage(gen);
				gen.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
