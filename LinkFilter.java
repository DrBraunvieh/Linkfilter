import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Eine Klasse die aus HTML Dateien Links rausfiltert und aufbereitet anzeigt.
 * 
 * @author Aaron Schneider, Moritz Schönenberger
 * @version 05.01.2021
 */
public class LinkFilter{
    private static final String FILE_NOT_FOUND    = "Die angegebene Datei konnte nicht gefunden werden.";
    private static final String NO_FILE_SPECIFIED = "Sie haben keine Datei angegeben auf der die Methode arbeiten soll.";
    private static final String REGULAR_EXPRESSION = "<a href=\"http://www.[a-z]*.[a-z]*\">[a-z]*</a>";
    
    public static void main(String[] args){
        boolean weitermachen = true;
        int numberOfLine = 0;
        boolean hasFound;
        String currentLine;
        int anfangURL;
        int endeURL;
        int endeName;
        
        try{
            Scanner file = new Scanner(new File(args[0]));
            Pattern regex = Pattern.compile(REGULAR_EXPRESSION);
            while(weitermachen){
                currentLine = file.nextLine();
                numberOfLine++;
                Matcher match = regex.matcher(currentLine);
                hasFound = match.find();
                anfangURL = currentLine.indexOf("=\"");
                endeURL = currentLine.indexOf("\">");
                endeName = currentLine.indexOf("</a>");
                if(hasFound == true){
                    System.out.println(currentLine.substring(endeURL, endeName) + ": " + currentLine.substring(anfangURL, endeURL));
                }
                if(file.hasNextLine() == false){
                    weitermachen = false;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println(FILE_NOT_FOUND);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println(NO_FILE_SPECIFIED);
        }
    }
}
