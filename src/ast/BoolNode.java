package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class BoolNode implements Node{
    private boolean val;

    public BoolNode(boolean v) {
        this.val = v;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        return new ArrayList<SemanticError>();
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        return new TypeNode("bool");
    }

    @Override
    public String toPrint(String s) {
        return s+this.val;
    }

    @Override
    public String codeGeneration(Environment e) {
        return "storei A0 "+(this.val ? 1 : 0)+"\n";
    }
}
