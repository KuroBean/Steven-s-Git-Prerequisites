import java.math.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.io.*; 
import java.util.*; 
import java.security.*; 


public class Blob {
	private String hashed; 
	private String unhashed; 


	public static void main (String [] args) throws IOException {
		Blob b = new Blob ("test/Stuff.txt"); 
	}
	public Blob (String fileName) throws IOException {

		String ret = "";

		//get file and read 
		try {

			File f1 = new File(fileName);
			Scanner read = new Scanner(f1);
			while (read.hasNextLine()) {
				ret += read.nextLine();
			}
			read.close(); 
		} 
		catch (FileNotFoundException err) {
			System.out.println("There was an error!");
			err.printStackTrace();
		}
		unhashed = ret; 
		hashed = getSHA1(unhashed);
		createFBlob();

//		System.out.println("text:" + ret);
//		System.out.println("encoded:" + hashed);
//		System.out.println ("BLOB^^"); 


		//		read.close(); 

	}
	
	public Blob (String fileName, String Index) throws IOException {

		String ret = "";

		//get file and read 
		try {

			File f1 = new File(fileName);
			Scanner read = new Scanner(f1);
			while (read.hasNextLine()) {
				ret += read.nextLine();
			}
			read.close(); 
		} 
		catch (FileNotFoundException err) {
			System.out.println("There was an error!");
			err.printStackTrace();
		}
		unhashed = ret; 
		hashed = getSHA1(unhashed);
		createFIndex();

//		System.out.println("text:" + ret);
//		System.out.println("encoded:" + hashed);
//		System.out.println ("BLOB^^"); 


		//		read.close(); 

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
	public String getHash() {
		return hashed; 
	}

	public void createFBlob () throws IOException { //with hashed as name
		
		File f = new File (hashed + ".txt"); 
		PrintWriter pw = new PrintWriter("test/objects/" + f); 
		pw.append(unhashed); 
		pw.close(); 
		

	}
	public void createFIndex () throws IOException { //with hashed as name
		
		File f = new File (hashed + ".txt"); 
		PrintWriter pw = new PrintWriter("test/objectsIndex/" + f); 
		pw.append(unhashed); 
		pw.close(); 
		

	}



}

