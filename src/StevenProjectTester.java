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
		//String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt";
		//File removed1 = new File("test/objects/" + shaCode);
		//removed1.delete();
		//File removed2 = new File ("test/test.txt");
		//removed2.delete();
		
		//File objectsFolder = new File ("test/objects");
		//objectsFolder.delete();
		File index = new File("test/Index.txt");
		index.delete();
		
		
	
	}
	
	@Test
	void testBlobCreation() throws Exception{
		Blob testBlob = new Blob("test/test.txt");
		String shaCode = "c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt";
		File file = new File("test/objects/" + shaCode);
		assertTrue(file.exists());
		
		testBlob.getHash();
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
	
	@Test
	void testTree() throws Exception{
		ArrayList<String> test = new ArrayList<String>();
		
		test.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		test.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		test.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		test.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		test.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		
		Tree tree1 = new Tree(test);
		File file = new File("test/objects/dd4840f48a74c1f97437b515101c66834b59b1be");
		assertTrue(file.exists());
	}

}
