import static org.junit.Assert.*; 
import org.junit.*; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParseTest { 
    @Test
    public void addition() { 
        assertEquals(2, 1 + 1); 
    }
    @Test
    public void getLinkstestfile() throws IOException {
        ArrayList<String> testArrayList = new ArrayList<>();

        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);

        testArrayList.add("https://something.com");
        testArrayList.add("some-page.html");

        assertEquals(MarkdownParse.getLinks(content), testArrayList);
    } 
    @Test
    public void getLinksnewmd() throws IOException {
        ArrayList<String> testArrayList = new ArrayList<>();

        Path fileName = Path.of("newmd.md");
        String content = Files.readString(fileName);

        testArrayList.add("a link on the first line");
        testArrayList.add("https://something.com");

        assertEquals(MarkdownParse.getLinks(content), testArrayList);
    }
    @Test
    public void getLinkSnippet1() throws IOException {
        ArrayList<String> testArrayList = new ArrayList<>();

        Path fileName = Path.of("snippet1.md");
        String content = Files.readString(fileName);

        testArrayList.add("`google.com");
        testArrayList.add("google.com");
        testArrayList.add("ucsd.edu");

        assertEquals(MarkdownParse.getLinks(content), testArrayList);
    }
    @Test
    public void getLinkSnippet2() throws IOException {
        ArrayList<String> testArrayList = new ArrayList<>();

        Path fileName = Path.of("snippet2.md");
        String content = Files.readString(fileName);

        testArrayList.add("a.com");
        testArrayList.add("a.com(())");
        testArrayList.add("example.com");

        assertEquals(MarkdownParse.getLinks(content), testArrayList);
    }
    @Test
    public void getLinkSnippet3() throws IOException {
        ArrayList<String> testArrayList = new ArrayList<>();

        Path fileName = Path.of("snippet3.md");
        String content = Files.readString(fileName);

        testArrayList.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");

        assertEquals(MarkdownParse.getLinks(content), testArrayList);
    }
}