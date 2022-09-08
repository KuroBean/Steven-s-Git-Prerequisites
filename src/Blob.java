import java.math.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.io.*; 
import java.util.*;
import java.util.zip.ZipOutputStream;
import java.security.*; 
import java.util.zip.*; 
import java.nio.*; 



public class Blob {
	private String hashed; 
	private String unhashed; 
	private String zipped; 



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


	//public void zip() throws IOException {
	//		
	//		String filePath = "test/Stuff.txt";
	//		String zipPath = "test/objects/" + hashed + ".txt";
	//
	//		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
	//		    File fileToZip = new File(filePath);
	//		    zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
	//		    Files.copy(fileToZip.toPath(), zipOut);
	//		}
	//		
	//
	//	}
	//	public String getZipped() throws FileNotFoundException {
	//		String temp = ""; 
	//		File f1 = new File("test/objects/" + hashed + ".txt");
	////		PrintWriter pw = new PrintWriter(f1); 
	////		pw.append("F"); 
	////		pw.close(); 
	//		Scanner read = new Scanner(f1);
	//		while (read.hasNextLine()) {
	//			temp += read.nextLine();
	//		}
	//		read.close(); 
	//		zipped = temp; 
	//		return temp; 
	//	}


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


		//paths way
		File f1 = new File ("objects"); 
//		Path folder = Paths.get(f1.toString());
//		Path path = Paths.get(hashed + ".txt"); 
//		Files.writeString(folder, unhashed);
//		PrintWriter pw = new PrintWriter(folder.toString()); 
//		pw.close(); 


		//pw way
		File f = new File (hashed + ".txt"); 
		PrintWriter pw = new PrintWriter("test/objects/" + f); //in test folder
		pw.append(unhashed); 
		pw.close(); 


	}
	public void createFIndex () throws IOException { //with hashed as name

		File f = new File (hashed + ".txt"); 
		//		Path path = Paths.get(hashed + ".txt");
		//		PrintWriter pw = new PrintWriter(path.toString()); 
		PrintWriter pw = new PrintWriter("test/objectsIndex/" + f); 
		pw.append(unhashed); 
		pw.close(); 


	}



}

