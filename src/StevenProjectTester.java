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
		 Path p = Paths.get("test/test.txt");
	        try {
	            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		/**String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt";
		File removed1 = new File("test/objects/" + shaCode);
		removed1.delete();
		File removed2 = new File ("test.txt");
		removed2.delete();
		
		File objectsFolder = new File ("test/objects");
		objectsFolder.delete();
		File index = new File("test/Index.txt");
		index.delete();
		**/
		
	
	}
	
	@Test
	void testBlobCreation() throws Exception{
		Blob testBlob = new Blob("test/test.txt");
		String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt";
		File file = new File("test/objects/" + shaCode);
		assertTrue(file.exists());
	}
	
	
	@Test
	void testInit() throws Exception{
		Index index = new Index();
		index.init();
		
		//File file = new File("Index.txt");
		Path filePath = Paths.get("test/Index.txt");
		assertTrue(Files.exists(filePath));
		
		Path path = Paths.get("test/objects");
		assertTrue(Files.exists(path));
		
		index.add("test.txt");
		String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt";
		File file = new File("test/objects/" +shaCode);
		assertTrue(file.exists());
		
		index.remove("c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
		assertTrue(file.exists());
	}

}
