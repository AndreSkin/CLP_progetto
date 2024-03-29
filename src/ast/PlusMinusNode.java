package ast;

import semanticanalysis.Environment;
import semanticanalysis.SimpLanCustomError;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class PlusMinusNode implements Node{

    Node e1;
    Node e2;
    String op;

    public PlusMinusNode(Node e1, String op, Node e2) {
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

        if(!e1.typeCheck(e).getType().equals("int") || !e2.typeCheck(e).getType().equals("int")){
            throw new SimpLanCustomError("Errore di TypeChecking: Variabili non compatibili con questa operazione.");
        }
        return new TypeNode("int");
    }

    @Override
    public String toPrint(String s) {
        return e1.toPrint(s)+op+e2.toPrint(s);
    }

    @Override
    public String codeGeneration(Environment e)
    {
        return e1.codeGeneration(e)+
            "pushr A0 \n" +
            e2.codeGeneration(e)+
            "popr T1 \n" +
            (op.equals("+") ? "add T1 A0 \n" : "sub T1 A0 \n") +
            "popr A0 \n" ;
    }
}
