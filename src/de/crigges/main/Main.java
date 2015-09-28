package de.crigges.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import de.crigges.main.SylkParser.Entry;
import de.peeeq.jmpq3.JMpqEditor;

public class Main {

	public static void main(String[] args) {
		try {
			SylkParser unitSlk = new SylkParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\unitui.slk"), 1, 3);
			SylkParser dooSlk = new SylkParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\doodads.slk"), 5, 6);
			UnitStringParser unitStrings = new UnitStringParser(new File("C:\\Users\\Crigges-Pc\\Desktop\\unitstringseng\\comb.txt"));
			PackageGenerator gen = new PackageGenerator(new File("Assets.wurst"), "Assets");
			ConstantList unitList = new ConstantList();
			ConstantList dooList = new ConstantList();
			ConstantList otherList = new ConstantList();
			JMpqEditor mpqEditor = new JMpqEditor(new File("C:\\Users\\Crigges-Pc\\Desktop\\Desktop\\mpqedit\\war3.mpq"));
			JMpqEditor mpqxEditor = new JMpqEditor(new File("C:\\Users\\Crigges-Pc\\Desktop\\Desktop\\mpqedit\\war3x.mpq"));
			List<String> mpqFiles = mpqEditor.getFileNames();
			List<String> mpqxFiles = mpqxEditor.getFileNames();
			try{
				while(true){
					Entry e = unitSlk.next();
					String name = unitStrings.find(e.id);
					unitList.add(e.path, name);
				}
			}catch(NoSuchElementException e){
				unitList.writeToPackage(gen, "Units");
			}
			try{
				while(true){
					Entry e = dooSlk.next();
					dooList.add(e.id, e.path);
				}
			}catch(NoSuchElementException e){
				dooList.writeToPackage(gen, "Doodads");
			}
			for(String s : mpqFiles){
				if(s.endsWith(".mdx")){
					s = s.replace(".mdx", "");
					if((!unitList.hasPath(s)) && (!dooList.hasPath(s))){
						otherList.add(s + ".mdx", null);
					}
				}
			}
			otherList.writeToPackage(gen, "Other");
			gen.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
