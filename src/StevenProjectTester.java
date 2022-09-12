import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StevenProjectTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 Path p = Paths.get("test.txt");
	        try {
	            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

	/**@AfterAll
	static void tearDownAfterClass() throws Exception {
		String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f";
		File removed1 = new File("objects/" + shaCode);
		removed1.delete();
		File removed2 = new File ("test.txt");
		removed2.delete();
		
	
	}**/
	
	@Test
	void testBlobCreation() throws Exception{
		Blob testBlob = new Blob("test.txt");
		String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt";
		File file = new File("test/objects/" + shaCode);
		assertTrue(file.exists());
	}
	
	
	@Test
	void testInit() throws Exception{
		Index index = new Index();
		index.init();
		
		File file = new File("index");
		assertTrue(file.exists());
		
		Path path = Paths.get("objects");
		assertTrue(Files.exists(path));
	}

}
