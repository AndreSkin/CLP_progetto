import SVMPkg.SVMLexer;
import SVMPkg.SVMParser;
import ast.Node;
import interpreter.ExecuteVM;
import org.antlr.v4.runtime.*;
import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        String inputPath = "./src/input.txt";
        boolean log = false;
        boolean skipType = false;

        int i = 0;
        try {
            while (args.length - i > 0)
            {
                String command = args[i];
                switch (command)
                {
                    case "-h":
                        case "-help":
                            System.out.println("java -jar CLP_progetto.jar [path_to_input_file] [-h|-log|-skiptypecheck]");
                            System.exit(0);
                    case "-log":
                    case "-l":
                            log = true;
                            System.out.println("Log abilitato.");
                            break;
                    case "-skiptypecheck":
                    case "-s":
                            skipType = true;
                            System.out.println("Ignoro Type Checking.");
                            break;
                    default:
                        if (inputPath.equals("./src/input.txt")) {
                            System.out.println("Input File modificato: "+command);
                            inputPath = command;
                        } else throw new Error2("Paremetro sconosciuto");
                }
                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Usage: java -jar CLP_progetto.jar [path_to_input_file] [-h|-log|-skiptypecheck]");
            throw new Error2("Errore nei parametri.");
        }



    	//Exercise 1
    	
        String input = new String(Files.readAllBytes(Paths.get(inputPath)));
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

        Visitor visitor = new Visitor(log);

        Node ast = visitor.visit(parser.prog());

            if (!handler.errors.isEmpty()) {
                File f = new File("./out/errors.txt");
                if (!f.exists()) {
                    f.createNewFile();
                } else {
                    f.delete();
                    f.createNewFile();
                }
                for (String s : handler.errors) {
                    Files.write(Paths.get("./out/errors.txt"), s.getBytes(), StandardOpenOption.APPEND);
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


            if(!skipType) {
                System.out.println("Inizio Type Checking");
                ast.typeCheck(env);
                System.out.println("Type Checking completato con successo.");
            }


            //Codice per debug Offset
            /*SymbolTable st = env.getSymbolTable();
            for(int i=0; i<st.getSize();i++) {
                System.out.println("Livello "+i);
                for(String k: st.get(i).keySet()){
                    System.out.println(k+": "+st.get(i).get(k).getOffset());
                }
                System.out.println("-----");
            }*/
            System.out.println("Inizio Generazione di codice intermedio.");
            String code = ast.codeGeneration(env);

            File f = new File("./out/code.asm");
            if (!f.exists()) {
                f.createNewFile();
            } else {
                f.delete();
                f.createNewFile();
            }

            Files.write(Paths.get("./out/code.asm"), code.getBytes(), StandardOpenOption.WRITE);

            System.out.println("Codice generato con successo. Output nel file /out/code.asm");


            String fileName = "./out/code";
            FileInputStream isASM = new FileInputStream(fileName+".asm");
            ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
            SVMLexer lexerASM = new SVMLexer(inputASM);
            CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
            SVMParser parserASM = new SVMParser(tokensASM);

            SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
            visitorSVM.visit(parserASM.assembly());

            System.out.println("Starting Virtual Machine...");
            ExecuteVM vm = new ExecuteVM(visitorSVM.code);
            vm.cpu();
        } catch (Exception e) {
            throw new Error2(e.getMessage());
        }
    }
}


