package de.crigges.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.HashAttributeSet;

public class ConstantList {
	private ArrayList<Constant> content = new ArrayList<>();
	private HashMap<String, Integer> occurrences = new HashMap<>();
	
	public void add(String path, String name){
		if(hasPath(path)){
			return;
		}
		path = path.toLowerCase();
		if(name == null){
			//use file name as var name
			name = camelize(getFileName(path));
		}else{
			name = camelize(name);
		}
		Constant c;
		Integer i = occurrences.get(name);
		if(i == null || i == 0){
			c = new Constant(name, path);
			occurrences.put(name, 1);
		}else{
			c = new Constant(name + (i), path);
			occurrences.put(name, i + 1);
		}
		content.add(c);
	}
	
	public void print(){
		for (Constant constant : content) {
			System.out.println(constant);
		}
	}
	
	public void writeToPackage(PackageGenerator pack, String className) throws IOException{
		Collections.sort(content);
		pack.startBlock("public class " + className);
		for (Constant constant : content) {
			pack.writeln("static constant " + constant.name + " = \"" + (constant.path + ".mdx").replace("\\", "\\\\") + "\"");
		}
		pack.writeln("");
		pack.endBlock();
	}
	
	public boolean hasPath(String path){
		path = path.toLowerCase();
		return content.contains(new Constant("", path));
	}
	
	private String camelize(String s){
		StringBuilder sb = new StringBuilder();
		if(!Character.isJavaIdentifierStart(s.charAt(0))) {
			sb.append("");
		}
		for (char c : s.toCharArray()) {
			if(!Character.isJavaIdentifierPart(c)) {
				sb.append("");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	private String getFileName(String s){
		int i = s.lastIndexOf('\\');
		return s.substring(i + 1).replace(".mdx", "");
	}
}
