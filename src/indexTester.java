

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IndexTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path p = Paths.get("test/test");
		try {
			Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File if2 = new File ("test/objects"); 
		deleteDir(if2); 		
		//String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f";
		//File removed1 = new File("test/objects/" + shaCode);
		//removed1.delete();
		//File removed2 = new File ("test/test");
		//removed2.delete();

		File objectsFolder = new File ("test/objects");
		objectsFolder.delete();
		File index = new File("test/Index");
		index.delete();
	}
	static void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
	@Test
	void testInit() throws Exception{
		Index index = new Index();
		index.init();
		
		//File file = new File("Index");
		Path filePath = Paths.get("test/Index.txt");
		assertTrue(Files.exists(filePath));
	
		Path path = Paths.get("test/objects");
	assertTrue(Files.exists(path));
		
		index.add("test");
		String shaCode = "3ef5025e733e7023d7a2276def76bff49488cc6c.txt";
		File file = new File("test/objects/" +shaCode);
	assertTrue(file.exists());
		
		index.remove("3ef5025e733e7023d7a2276def76bff49488cc6c.txt");
		assertTrue(file.exists());
	}
	
}
	