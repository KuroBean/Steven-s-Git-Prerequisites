import java.util.*; 
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest; 


public class Commit {
	private String child; 
	private String parent; 
	private String treeFileName; //ptree
	private String summary; 
	private String author; 
	private String date; 
	private String thisFileName; 

	public Commit(String treeFileName1, String s, String a, String p) throws FileNotFoundException {

		treeFileName = treeFileName1; 
		summary = s; 
		author = a; 
		if (p == null) {
			parent = null; 
		}
		else{
			parent = p; 
		}
		child = null; 


		String name = "";  
		name += summary;
		name += date; 
		name += author; 
		name += parent; 
		name = getSHA1(name); 
		setFileName(name); 


		if (parent != null) {
			File f = new File ("test/objects/" + parent); 
			Scanner in = new Scanner(f); 
			String newContents = ""; 
			newContents += in.nextLine() + "\n"; 
			newContents += in.nextLine() + "\n"; 
			newContents += thisFileName + "\n"; 
			in.nextLine(); 
			newContents += in.nextLine() + "\n"; 
			newContents += in.nextLine() + "\n"; 
			newContents += in.nextLine() + "\n"; 
			
			
			PrintWriter pw = new PrintWriter (f); 
			pw.append(newContents); 
			pw.close(); 
			
		}
		







		//get date 
		Calendar date1 = Calendar.getInstance();
		int year = date1.get(Calendar.YEAR);
		int month = date1.get(Calendar.MONTH);
		int day = date1.get(Calendar.DAY_OF_MONTH);
		date =  month + " / " + day + " / " + year; 

	}

	public void setFileName(String s) {
		this.thisFileName = s; 
	}



	public void writeFile() throws FileNotFoundException {

		String ret = "";  

		//write file 
		File f = new File(thisFileName); 
		PrintWriter pw = new PrintWriter("test/objects/" + f); 


		//adding to file 
		ret += treeFileName + "\n"; 
		ret += parent + "\n"; 
		ret += child + "\n"; 
		ret += author + "\n"; 
		ret += date + "\n"; 
		ret += summary + "\n"; 

		System.out.println ("R:\n" + thisFileName + "\n" + ret); 


		pw.append(ret); 
		pw.close(); 

	}

	public String getFileName() {
		return this.thisFileName; 
	}

	public String getDate() {
		return date; 
	}


	//parents and child setters / getters
	public void setChild(Commit com) {
		child = com.getFileName(); 
	}

	public String getChild() {
		return child; 
	}
	public  void setParent(Commit com) {
		parent = com.getFileName(); 
	}
	public String getParent() {
		return parent; 
	}



	private String getSHA1 (String s1){
		String s = s1;
		String ret = "";

		//process
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(s.getBytes("utf8"));
			ret = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){

			e.printStackTrace();
		}

		return ret; 
	}






}
