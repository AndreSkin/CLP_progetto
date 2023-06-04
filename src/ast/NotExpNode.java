package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class NotExpNode implements Node{
    Node exp;
    public NotExpNode(Node e) {
        this.exp = e;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        return null;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        if(exp.typeCheck(e).getType() != "bool"){
            System.out.println("Operatore non booleano.");
        }
        return new TypeNode("bool");
    }

    @Override
    public String toPrint(String s) {
        return s+exp.toPrint(s);
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
