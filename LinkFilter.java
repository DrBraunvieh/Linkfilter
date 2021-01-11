import java.util.Scanner;
import java.util.regex.Pattern;
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
        try{
            Scanner file = new Scanner(new File(args[0]));
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println(FILE_NOT_FOUND);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println(NO_FILE_SPECIFIED);
        }
        Pattern regex = Pattern.compile(REGULAR_EXPRESSION);
    }
}
