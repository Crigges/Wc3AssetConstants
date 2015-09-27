package de.crigges.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SylkParser {
	private Scanner sc;
	private String keypos, entrypos;
	private boolean keyFirst, entryFirst;
	private File file;

	public SylkParser(File f, int keypos, int entrypos) throws FileNotFoundException {
		this.file = f;
		sc = new Scanner(file);
		sc.useDelimiter(";|\r\n");
		this.entrypos = "X" + entrypos;
		this.keypos = "X" + keypos;
		keyFirst = keypos == 1;
		entryFirst = entrypos == 1;
	}
	
	private void resetScanner(){
		sc.close();
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.useDelimiter(";|\r\n");
	}
	
	public Entry next(){
		Entry res = new Entry();
		while(true){
			String s = sc.next();
			if(s.equals(keypos)){
				if(keyFirst){
					sc.next();
				}
				if(res.put(clean(sc.next()))){
					break;
				}
			}
			if(s.equals(entrypos)){
				if(entryFirst){
					sc.next();
				}
				if(res.put(clean(sc.next()))){
					break;
				}
			}
		}
		return res;
	}
	
	public String find(String s){
		resetScanner();
		try{
			while(true){
				Entry e = next();
				if(e.id.equals(s)){
					return e.path;
				}
			}
		}catch(NoSuchElementException e){
			return null;
		}
	}
	
	private String clean(String s){
		String c = s.replaceFirst("K", "");
		c = c.replace("\"", "");
		return c.replace("\r\n", "");
	}
	
	public class Entry{
		String id, path;
		boolean first = true;
		
		private boolean put(String s){
			if(first){
				id = s;
				first = false;
				return false;
			}else{
				path = s;
				return true;
			}
		}

		@Override
		public String toString() {
			return "Entry [id=" + id + ", path=" + path + "]";
		}
		
		
		
	}
}
