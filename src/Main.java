import org.antlr.v4.runtime.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
    	
    	//Exercise 1
    	
        String input = new String(Files.readAllBytes(Paths.get("src/input.txt")));
        CharStream stream = CharStreams.fromString(input);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(stream);
        List<Token> lexerErrors = new ArrayList<>();
        Token token = lexer.nextToken();
        while (token.getType() != SimpLanPlusLexer.EOF)
        {
            if (token.getType() == SimpLanPlusLexer.ERR)
                lexerErrors.add(token);
            token = lexer.nextToken();
        }

        //System.out.println("Numero di errori lessicali: "+ lexerErrors.size());

        File f = new File("out/errors.txt");
        if(!f.exists()){
        	f.createNewFile();
        } else {
        	f.delete();
        	f.createNewFile();
        }
        
        for (int i = 0; i<lexerErrors.size();i++)
        {
            int errLine = lexerErrors.get(i).getLine();
            String errStr = lexerErrors.get(i).getText();
            int errPos = lexerErrors.get(i).getCharPositionInLine() +1;
            String toWrite = "Errore "+i+1+": Linea "+errLine+", carattere numero "+errPos+" -> "+errStr+"\n";
            Files.write(Paths.get("out/errors.txt"), toWrite.getBytes(), StandardOpenOption.APPEND);
        }
    }
}