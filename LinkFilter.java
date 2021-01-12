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
    private static final String REGULAR_EXPRESSION = "<a href=\"http?://*www.*.*\">*";
    
    public static void dateienFiltern(String[] args, int anzahlDateien){
        String currentLine;
        int anfangURL;
        int endeURL;
        int endeName;
        
        for(int i = 0; i < anzahlDateien; i++){
            int numberOfLine = 0;
            try{
                Scanner file = new Scanner(new File(args[i]));
                Pattern regex = Pattern.compile(REGULAR_EXPRESSION);
                while(file.hasNextLine()){
                    currentLine = file.nextLine();
                    numberOfLine++;
                    Matcher match = regex.matcher(currentLine);
                    anfangURL = currentLine.indexOf("=\"");
                    endeURL = currentLine.indexOf("\">");
                    endeName = currentLine.indexOf("</a>");
                
                    if(match.find()){
                        System.out.println(numberOfLine + ": " + currentLine.substring(endeURL + 2, endeName) + ": " 
                                         + currentLine.substring(anfangURL + 2, endeURL));
                    }
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
                System.out.println(FILE_NOT_FOUND);
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println(NO_FILE_SPECIFIED);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /*
    public static void eineDatei(String[] args){
        int numberOfLine = 0;
        String currentLine;
        int anfangURL;
        int endeURL;
        int endeName;
        int anzahlDateien;
        
        try{
            Scanner file = new Scanner(new File(args[0]));
            Pattern regex = Pattern.compile(REGULAR_EXPRESSION);
            while(file.hasNextLine()){
                currentLine = file.nextLine();
                numberOfLine++;
                Matcher match = regex.matcher(currentLine);
                anfangURL = currentLine.indexOf("=\"");
                endeURL = currentLine.indexOf("\">");
                endeName = currentLine.indexOf("</a>");
                
                if(match.find()){
                    System.out.println(numberOfLine + ": " + currentLine.substring(endeURL + 2, endeName) + ": " 
                                     + currentLine.substring(anfangURL + 2, endeURL));
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println(FILE_NOT_FOUND);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println(NO_FILE_SPECIFIED);
        }
    }*/
    
    public static void main(String[] args){
        int anzahlDateien = args.length;
        
        dateienFiltern(args, anzahlDateien);
    }
}
