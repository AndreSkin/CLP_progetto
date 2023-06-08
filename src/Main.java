import ast.Node;
import org.antlr.v4.gui.Interpreter;
import org.antlr.v4.runtime.*;
import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

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

        //Error Handler per gestire gli errori sintattici
        Handler handler = new Handler();
        lexer.removeErrorListeners();
        lexer.addErrorListener(handler);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        System.out.println("Inizio Analisi Sintattica.");
        try {
        SimpLanPlusParser parser;

        parser = new SimpLanPlusParser(tokens);

        Visitor visitor = new Visitor(false);

        Node ast = visitor.visit(parser.prog());

            if (!handler.errors.isEmpty()) {
                File f = new File("out/errors.txt");
                if (!f.exists()) {
                    f.createNewFile();
                } else {
                    f.delete();
                    f.createNewFile();
                }
                for (String s : handler.errors) {
                    Files.write(Paths.get("out/errors.txt"), s.getBytes(), StandardOpenOption.APPEND);
                }
                throw new Error2("Errori sintattici rilevati. Visualizzare l'output nel file ./out/errors.txt");
            }
            System.out.println("Analisi Sintattica completata con successo.");


            System.out.println("Inizio Analisi Semantica.");
            Environment env = new Environment();
            ArrayList<SemanticError> err = ast.checkSemantics(env);
            if (!err.isEmpty()) {
                for (SemanticError er : err)
                    System.out.println(er);
                throw new Error2("Errori semantici presenti.");
            }
            System.out.println("Analisi Semantica completata con successo.");


            System.out.println("Inizio Type Checking");
            ast.typeCheck(env);
            System.out.println("Type Checking completato con successo.");

        /*
        Codice per debug Offset
        SymbolTable st = env.getSymbolTable();
        for(int i=0; i<st.getSize();i++) {
            System.out.println("Livello "+i);
            for(String k: st.get(i).keySet()){
                System.out.println(k+": "+st.get(i).get(k).getOffset());
            }
            System.out.println("-----");
        }*/
            System.out.println("Inizio Generazione di codice intermedio.");
            String code = ast.codeGeneration(env);

            File f = new File("out/code.asm");
            if (!f.exists()) {
                f.createNewFile();
            } else {
                f.delete();
                f.createNewFile();
            }

            Files.write(Paths.get("out/code.asm"), code.getBytes(), StandardOpenOption.WRITE);

            System.out.println("Codice generato con successo. Output nel file /out/code.asm");

            /*int memsize = 100000;
            ArrayList<Integer> breakpoints = new ArrayList<Integer>();
            Interpreter interpreter = new Interpreter("out/code.asm");
            interpreter.runVM();
*/

        } catch (RecognitionException e) {
            // TODO: 6/8/23 Sistema RecognitionException
            throw new Error2(e.getMessage());
        }
    }




}


