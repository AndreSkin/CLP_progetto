package ast;

import others.SimpLanlib;
import semanticanalysis.Environment;
import semanticanalysis.SimpLanCustomError;
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
            throw new SimpLanCustomError("Errore di TypeChecking: Variabili non compatibili con questa operazione.");
        }
        return new TypeNode("bool");
    }

    @Override
    public String toPrint(String s) {
        return e1.toPrint(s)+op+e2.toPrint(s);
    }

    @Override
    public String codeGeneration(Environment e) {


        String result;
        if (op.equals("&&")) {
            String trueL = SimpLanlib.freshLabel();
            String lend = SimpLanlib.freshLabel();
            result = e1.codeGeneration(e)+
                    "push 0 \n"+
                    "popr T1 \n"+
                    "beq A0 T1 "+lend+"\n"+
                    e2.codeGeneration(e)+
                    lend+ ":\n";
        } else {
            String trueL = SimpLanlib.freshLabel();
            String lend = SimpLanlib.freshLabel();
            result = e1.codeGeneration(e)+
                    "pushr A0 \n" +
                    e2.codeGeneration(e)+
                    "popr T1 \n" +
                    "add A0 T1 \n"+
                    "popr A0 \n"+
                    "push 1 \n"+
                    "popr T1 \n"+
                    "bgte A0 T1 "+trueL+"\n"+
                    "push 0 \n"+
                    "popr A0 \n"+
                    "b "+lend+"\n"+
                     trueL+ ":\n" +
                    "push 1 \n"+
                    "popr A0 \n"+
                    lend+ ":\n";
        }


        return result;
    }
}
