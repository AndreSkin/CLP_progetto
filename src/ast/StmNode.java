package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class StmNode implements  Node{
    private Node statement;
    public StmNode(Node statement) {
        this.statement = statement;
    }

    public Node getStatement() {
        return statement;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> result= new ArrayList<SemanticError>();
        if (statement != null) {
            result.addAll(this.statement.checkSemantics(e));
        }
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        return statement.typeCheck(e);
    }

    @Override
    public String toPrint(String s) {
        return s+statement.toPrint(" ");
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
