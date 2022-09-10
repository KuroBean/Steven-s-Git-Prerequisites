import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*; 

public class Index {
	HashMap<String,String> values; 
	
	
	public static void main (String [] args) throws IOException {
		Index i = new Index(); 
		i.init(); 
		i.add("foo.txt");
		i.add("bar.txt");
		i.add("foobar.txt");
//		i.remove("foo.txt");
	
		
		
		
		
	}
	public Index() {
		values = new HashMap<String,String>(); 
	}
	private HashMap<String,String> getValues(){
		return values; 
	}
	
	public void init() throws FileNotFoundException {//makes index file and object folder
		File f = new File ("Index.txt"); 
		PrintWriter pw = new PrintWriter("test/" + f); 
		pw.append(""); 
		pw.close(); 
		File d = new File("test/objects"); 
		d.mkdir(); 
		
	}
	
	private void writeIndex(String key1) throws FileNotFoundException {
		String str = ""; 
		String k = key1; 
		String v = values.get(k); 
		File f = new File ("Index.txt"); 
		PrintWriter pw = new PrintWriter("test/Index.txt"); 
		for (String s : values.keySet()) {
			pw.append(s + " : " + values.get(s)); 
			pw.append("\n"); 
		}
		
		pw.close(); 
	}
	
	public void add(String name) throws IOException {
//		Blob b = new Blob("test/" + name,"F"); 
		Blob b = new Blob("test/" + name,true); 
		
		//make file in objectsIndex
		b.createFBlobZipped(); 
		
		
		String s = b.getHashZipped(); 
		values.put(name,s); 
		writeIndex(name); 
	}
	
	public void remove (String name) throws FileNotFoundException {
		File f = new File ("test/objects/" + values.get(name) + ".txt"); 
		f.delete(); 
		values.remove(name); 
		writeIndex(""); 
	}
	
}
