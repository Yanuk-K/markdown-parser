//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        String tempStr = markdown;
        while(currentIndex < markdown.length()) {
            tempStr = markdown.substring(currentIndex, markdown.length());

            if(!tempStr.contains("[") || !tempStr.contains("]") || !tempStr.contains("(") || !tempStr.contains(")")) {
                break;
            }
            
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            currentIndex = closeParen + 1;
            
            if(openBracket == 0) {
                if(openParen - closeBracket != 1) {
                    continue;
                }
            }
            else {
                if (markdown.substring(openBracket - 1, openBracket).contains("!") || openParen - closeBracket != 1) {
                    continue;
                }
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
