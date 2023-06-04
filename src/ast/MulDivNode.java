package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class MulDivNode implements Node{

    Node e1;
    Node e2;
    String op;

    public MulDivNode(Node e1, String op, Node e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        return null;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        return null;
    }

    @Override
    public String toPrint(String s) {
        return null;
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
