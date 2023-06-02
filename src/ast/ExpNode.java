package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class ExpNode implements Node{
    protected Node exp;

    public ExpNode(Node e){
        this.exp = e;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        return exp.checkSemantics(e);
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        return exp.typeCheck(e);
    }

    @Override
    public String toPrint(String s) {
        return s+exp.toPrint(" ");
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
