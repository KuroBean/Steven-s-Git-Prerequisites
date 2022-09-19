import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Tree {
	//keys can either be blobs or trees
	//HashMap<String, String> treeMap;
	ArrayList<String> list;
	
	public Tree (ArrayList<String> inputList) throws Exception{
		this.list = inputList;
		File d = new File("test/objects"); 
		d.mkdir();
		writeFile();
	}
	
	public void writeFile() throws Exception {
		String fileNameString = "";
		
		for (int i = 0; i < list.size(); i++) {
			fileNameString += list.get(i);
			if (i + 1 != list.size()) {
				fileNameString += "\n";
			}
			
			//System.out.println(fileNameString);
		}
		
		String shaCode = sha1Code(fileNameString);
		File file = new File("test/objects/" + shaCode);
		PrintWriter print = new PrintWriter(file);
		
		for (int i = 0; i < list.size(); i++) {
			print.println(list.get(i));
		}
		print.close();
		
	}
	
	
	
	
	//gets sha1Code of parameter String
	public String sha1Code (String value) {
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		return sha1;
	}
	
//	public static void main (String[]args) throws Exception{
//		ArrayList<String> test = new ArrayList<String>();
//		
//		test.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
//		test.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
//		test.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
//		test.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//		test.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
//		
//		Tree tree1 = new Tree(test);
//	}
}
