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
	private String file; 



	public static void main (String [] args) throws IOException {
//		Blob b = new Blob ("test/Stuff.txt",true); 
		Blob b1 = new Blob("test/Stuff.txt");



	}

	public Blob (String fileName) throws IOException {
		file = fileName; 
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




	public Blob (String fileName, Boolean bool) throws IOException {//for zipping 
		file = fileName; 
		String ret = "";

		//		get file and read 
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
		//		zip(file); //makes a duplicate zip file 
		setZipped(file); //reads content from base file and compresses text 
		hashedZipped = getSHA1(zipped); //sets hashedZipped for new zipped file name
		//		zip2(file); 


		//		
	}

	//	public void zip(String filename) throws IOException {
	//
	////		String filePath = "test/Stuff.txt";
	////		String zipPath = "test/objects/" + "Stuff" + ".txt.zip";
	//		
	//		String filePath = "test/" + filename;
	//		String zipPath = "test/objects/" + filename + ".zip";
	//
	//		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
	//			File fileToZip = new File(filePath);
	//			zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
	//			Files.copy(fileToZip.toPath(), zipOut);
	//		}
	//
	//
	//	}


	//	public void zip2(String filename) throws IOException {
	//
	//		System.out.println ("hashedZipped:" + hashedZipped); 
	//		
	////		String filePath = "test/objects/Stuff.txt.zip";
	////		String zipPath = "test/objects/" + hashedZipped + ".txt.zip";
	//		
	//		String filePath = "test/objects/" + filename + ".zip";
	//		String zipPath = "test/objects/" + hashedZipped + ".txt.zip";
	//
	//		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
	//			File fileToZip = new File(filePath);
	//			zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
	//			Files.copy(fileToZip.toPath(), zipOut);
	//		}
	//
	//
	//		//
	//		File delete = new File ("test/objects/" + filename + ".zip"); 
	//		delete.delete(); 
	//
	//	}



	public void setZipped(String filename) throws IOException {//reading from unzipped file and compressing text
		String temp = ""; 
		File f1 = new File(file);

		//		Scanner read = new Scanner(f1);
		//		while (read.hasNextLine()) {
		//			temp += read.nextLine();
		//		}
		//		read.close(); 
		BufferedReader	in = new BufferedReader(new FileReader(file));

		while (in.ready()) {
			Character temp1 = (char)in.read(); 
			temp+=temp1; 

		}

		in.close(); 

		zipped = compress(temp); 

		System.out.println ("Zipped:" + zipped); 
//		System.out.println ("content:" + unhashed); 
		//		System.out.println (zipped); 
		//		return zipped;  
	}

	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString("ISO-8859-1");
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
	public String getHashZipped() {
		return hashedZipped; 
	}


	public void createFBlob () throws IOException { //with hashed as name


		//paths way
		File f1 = new File ("test/objects"); 
		f1.mkdir();
		//		Path folder = Paths.get(f1.toString());
		//		Path path = Paths.get(hashed + ".txt"); 
		//		Files.writeString(folder, unhashed);
		//		PrintWriter pw = new PrintWriter(folder.toString()); 
		//		pw.close(); 


		//pw way
		File f = new File ("test/objects/" +hashed + ".txt"); 
		PrintWriter pw = new PrintWriter(f); //in test folder
		pw.append(unhashed); 
		pw.close(); 


	}
	public void createFBlobZipped () throws IOException { //with hashed as name


		//paths way
		File f1 = new File ("objects"); 
		//		Path folder = Paths.get(f1.toString());
		//		Path path = Paths.get(hashed + ".txt"); 
		//		Files.writeString(folder, unhashed);
		//		PrintWriter pw = new PrintWriter(folder.toString()); 
		//		pw.close(); 


		//pw way
		File f = new File (hashedZipped + ".txt"); 
		PrintWriter pw = new PrintWriter("test/objects/" + f); //in test folder
		pw.append(zipped); 
		pw.close(); 


	}




}

