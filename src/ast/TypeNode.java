package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class TypeNode implements Node {
    // Generic type
    private String type;
    private ArrayList<TypeNode> params;
    public TypeNode(String type){
        this.type = type;
    }

    public TypeNode(String type, ArrayList<TypeNode> params) {
        this.type = type;
        this.params = params;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"Type "+this.type;
    }

    public String getType() {
        return type;
    }

    public ArrayList<TypeNode> getParams() {
        return this.params;
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        return null;
    }

    @Override
    public String codeGeneration(/*LabelGenerator labgen,*/ Environment localenv) {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }

    /*@Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }*/
}