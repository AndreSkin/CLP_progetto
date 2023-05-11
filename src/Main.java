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
        List<Token> allTokens = new ArrayList<>();
        List<Token> lexerErrors = new ArrayList<>();
        Token token = lexer.nextToken();

        while (token.getType() != SimpLanPlusLexer.EOF)
        {
            allTokens.add(token);
            System.out.println(token);
            if (token.getType() == SimpLanPlusLexer.ERR)
            {
                lexerErrors.add(token);
            }
            token = lexer.nextToken();
        }

        if (lexerErrors.size() > 0)
        {
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
                int k=i+1;
                String toWrite = "Errore "+k+": Linea "+errLine+", carattere numero "+errPos+" -> "+errStr+"\n";
                Files.write(Paths.get("out/errors.txt"), toWrite.getBytes(), StandardOpenOption.APPEND);
            }
                System.out.println("Errori sintattici rilevati, consultare il file /out/errors.txt");
                throw new Error2();
        }
        
        
        //Exercise 2
        final int openGraffa = 5;
        final int closeGraffa = 6;
        final int intType = 7;
        final int boolType = 8;
        final int voidType = 9;

        int nestingLevel = 0;
        for (int k=0; k<allTokens.size();k=k+1)
        {
            Token T = allTokens.get(k);
            switch (T.getType())
            {
                case openGraffa:
                    nestingLevel = nestingLevel +1;
                    break;
                case closeGraffa:
                    if (nestingLevel > 0)
                    {
                        nestingLevel = nestingLevel - 1;
                    }
                    break;
                case intType:
                case boolType:
                case voidType:
                    if (k + 1 < allTokens.size())
                    {
                        Token nextToken = allTokens.get(k + 1);
                        String tokenName = nextToken.getText();
                        if (k + 2 < allTokens.size())
                        {
                            Token nextNextToken = allTokens.get(k + 2);
                            if (nextNextToken.getText().equals(";"))
                            {
                                // declaration
                            }
                            else if (nextNextToken.getText().equals("="))
                            {
                                // declaration + initialization
                                if (k + 3 < allTokens.size())
                                {
                                    Token initValToken = allTokens.get(k + 3);
                                    String initVal = initValToken.getText();
                                    // save initVal
                                }
                            }
                            else if (nextNextToken.getText().equals("("))
                            {
                                // function declaration
                            }
                        }
                    }
                    break;
                default:
                    //
            }
        }

    }
}