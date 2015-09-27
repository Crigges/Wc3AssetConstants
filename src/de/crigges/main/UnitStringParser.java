package de.crigges.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UnitStringParser {
	private Scanner sc;
	private String keypos, entrypos;
	private boolean keyFirst, entryFirst;
	private File file;

	public UnitStringParser(File f) throws FileNotFoundException {
		this.file = f;
		sc = new Scanner(file);
		sc.useDelimiter("\r\n");
	}
	
	private void resetScanner(){
		sc.close();
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.useDelimiter("\r\n");
	}
	
	public String find(String key){
		resetScanner();
		try{
			while(true){
				String s = sc.next();
				s = cleanID(s);
				if(s.equals(key)){
					return cleanName(sc.next());
				}
			}
		}catch(NoSuchElementException e){
			return null;
		}
	}
	
	private String cleanID(String s){
		String c = s.replace("[", "");
		c = c.replace("]", "");
		return c.replace("\r\n", "");
	}
	
	private String cleanName(String s){
		String c = s.replace("Name=", "");
		return c.replace("\r\n", "");
	}
}
