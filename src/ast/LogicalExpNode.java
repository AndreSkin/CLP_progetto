package ast;

import others.SimpLanlib;
import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class LogicalExpNode implements Node{

    Node e1;
    Node e2;
    String op;

    public LogicalExpNode(Node e1, String op, Node e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> result = new ArrayList<SemanticError>();
        if (this.e1 != null) {
            result.addAll(e1.checkSemantics(e));
        }
        if (this.e2 != null) {
            result.addAll(e2.checkSemantics(e));
        }
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error {
        if(!e1.typeCheck(e).getType().equals("bool") || !e2.typeCheck(e).getType().equals("bool")){
            throw new Error2("Errore di TypeChecking: Variabili non compatibili con questa operazione.");
        }
        return new TypeNode("bool");
    }

    @Override
    public String toPrint(String s) {
        return e1.toPrint(s)+op+e2.toPrint(s);
    }

    @Override
    public String codeGeneration(Environment e) {
        String trueL = SimpLanlib.freshLabel();
        String lend = SimpLanlib.freshLabel();

        String or="add A0 T1\n" +
                "popr T1\n" +
                "BGE T1 1 "+ trueL+"\n";

        String and= "BEQ A0 1" + trueL+"\n"+
                "BEQ T1 1" + trueL+"\n";

        return e1.codeGeneration(e)+
                "pushr A0 \n" +
                e2.codeGeneration(e)+
                "popr T1 \n" +
                (op.equals("||") ? or : and) +
                //false
                "pushr A0 0\n"+
                "B"+lend+

                trueL+ ":\n" +
                "pushr A0 1\n"+

                lend+ ":\n";
    }
}
