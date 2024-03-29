package ast;

import semanticanalysis.Environment;
import semanticanalysis.SimpLanCustomError;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class NotExpNode implements Node{
    Node exp;
    public NotExpNode(Node e) {
        this.exp = e;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        res.addAll(this.exp.checkSemantics(e));
        return res;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        System.out.println(exp.typeCheck(e).getType());
        if(!exp.typeCheck(e).getType().equals("bool")){
            throw new SimpLanCustomError("Errore di TypeChecking: Operatore non booleano.");
        }
        return new TypeNode("bool");
    }

    @Override
    public String toPrint(String s) {
        return s+exp.toPrint(s);
    }

    @Override
    public String codeGeneration(Environment e) {
        return this.exp.codeGeneration(e)   
        + "storei T1 1\n"
        + "sub T1 A0\n"
        + "popr A0\n";
    }
}
