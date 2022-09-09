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
	private String hashedZipped; 



	public static void main (String [] args) throws IOException {
		Blob b = new Blob ("test/Stuff.txt",true); 
		Blob b1 = new Blob("test/Stuff.txt");



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
	public Blob (String fileName, Boolean bool) throws IOException {

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
		zip(); 
		hashedZipped = getSHA1(zipped); 
		zip2(); 


		//		System.out.println("text:" + ret);
		//		System.out.println("encoded:" + hashed);
		//		System.out.println ("BLOB^^"); 


		//		read.close(); 
		//		File f = new File ("test/" + "hashedZipped" + ".txt.zip"); 
		//		f.delete(); 
		System.out.println (zipped + "-" + hashedZipped); 
	}


	public Blob (String fileName, String Index) throws IOException { //for Index

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


		public void zip() throws IOException {
	
			String filePath = "test/Stuff.txt";
			String zipPath = "test/" + "Stuff" + ".txt.zip";
	
			try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
				File fileToZip = new File(filePath);
				zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
				Files.copy(fileToZip.toPath(), zipOut);
			}
			getZipped(); 
	
		}
	

	public void zip2() throws IOException {

		String filePath = "test/Stuff.txt.zip";
		String zipPath = "test/" + hashedZipped + ".txt.zip";

		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
			File fileToZip = new File(filePath);
			zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
			Files.copy(fileToZip.toPath(), zipOut);
		}


		//
		File delete = new File ("test/Stuff.txt.zip"); 
		delete.delete(); 

	}

	public void getZipped() throws IOException {
		String temp = ""; 
		File f1 = new File("test/" + "Stuff" + ".txt.zip");

		//		Scanner read = new Scanner(f1);
		//		while (read.hasNextLine()) {
		//			temp += read.nextLine();
		//		}
		//		read.close(); 
		BufferedReader	in = new BufferedReader(new FileReader("test/Stuff.txt.zip"));

		while (in.ready()) {
			Character temp1 = (char)in.read(); 
			temp+=temp1; 

		}


		in.close(); 

		zipped = temp; 
		//		System.out.println (zipped); 
		//		return zipped;  
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

