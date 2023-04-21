import org.antlr.v4.runtime.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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

        System.out.println("Numero di errori lessicali: "+ lexerErrors.size());

        for (int i = 0; i<lexerErrors.size();i++)
        {
            int errLine = lexerErrors.get(i).getLine();
            String errStr = lexerErrors.get(i).getText();
            int errPos = lexerErrors.get(i).getCharPositionInLine() +1;
            System.out.printf("Errore %d: Linea %d, carattere numero %d -> %s%n", i + 1, errLine, errPos, errStr);        }
    }
}