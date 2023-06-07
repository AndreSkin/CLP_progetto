package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class FunCallNode implements Node{
    String id;
    ArrayList<Node> params;

    public FunCallNode(String id, ArrayList<Node> params) {
        this.id = id;
        this.params = params;
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
        return this.id+" :"+this.params.toString();
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
