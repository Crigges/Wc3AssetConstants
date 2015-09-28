package de.crigges.main;

public class Constant implements Comparable<Constant>{
	public String name, path;

	public Constant(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public int compareTo(Constant o) {
		if(this.equals(o)){
			return 0;
		}else{
			return name.compareToIgnoreCase(o.name);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Constant){
			Constant c = (Constant) obj;
			return c.path.equals(path);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Constant [name=" + name + ", path=" + path + "]";
	}
}
