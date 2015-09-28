package de.crigges.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PackageGenerator {
	private File out;
	private FileWriter writer;
	private int indentionLvl = 0;
	
	public PackageGenerator(File out, String name) throws IOException {
		this.out = out;
		writer = new FileWriter(out);
		writeln("package " + name);
		writeln("");
	}
	
	public void writeln(String s) throws IOException{
		String ind = "";
		for (int i = 1; i <= indentionLvl ; i++) {
			ind += "\t";
		}
		writer.write(ind + s + System.lineSeparator());
	}
	
	public void startBlock(String desc) throws IOException{
		writeln(desc);
		indentionLvl++;
	}
	
	public void endBlock() throws IOException{
		indentionLvl--;
	}
	
	public void close() throws IOException{
		writer.flush();
		writer.close();
	}
}
