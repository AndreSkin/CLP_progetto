import ast.Node;
import org.antlr.v4.runtime.*;
import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;

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
        //System.out.println(input);
        CharStream stream = CharStreams.fromString(input);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(stream);
        List<Token> allTokens = new ArrayList<>();
        List<Token> lexerErrors = new ArrayList<>();
      //  Token token = lexer.nextToken();

       /* while (token.getType() != SimpLanPlusLexer.EOF)
        {
            allTokens.add(token);
            //System.out.println(token);
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

                int errLine = lexerErrors.get(i).getLine();
                String errStr = lexerErrors.get(i).getText();
                int errPos = lexerErrors.get(i).getCharPositionInLine() +1;
                int k=i+1;
                String toWrite = "Errore "+k+": Linea "+errLine+", carattere numero "+errPos+" -> "+errStr+"\n";
                Files.write(Paths.get("out/errors.txt"), toWrite.getBytes(), StandardOpenOption.APPEND);
            }
                System.out.println("Errori sintattici rilevati, consultare il file /out/errors.txt");
                throw new Error();
        }*/

        //Se uso il lexer.nextToken allora mi trovo eof, bisogna capire come resettarlo o se sopra si puo evitare di usare

        //Exercise 2
        //System.out.println(lexer.nextToken());
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
        Visitor visitor = new Visitor(false);
        //Bisogna produrre l'AST
        //System.out.println(parser.prog());
        Node ast = visitor.visit(parser.prog());
        System.out.println("Started Check Semantics...");
        Environment env = new Environment();
        ArrayList<SemanticError> err = ast.checkSemantics(env);
        if (!err.isEmpty()) {
            for(SemanticError er: err)
                System.out.println(er);
            throw new Error2("Errori semantici presenti.");
        }
        System.out.println("Check Semantics Passed.");
    }


    //Exercise 3
    //Environment e = new Environment();


}


