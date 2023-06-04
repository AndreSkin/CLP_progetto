package ast;

import java.util.ArrayList;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class DecNode implements Node {
    private String id;
    private TypeNode type;

    public DecNode(String _id, TypeNode _type) {
        id = _id ;
        type = _type ;
    }

    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if (e.getSymbolTable().lookup(id) != null)
            errors.add(new SemanticError("Var id " + id + " already declared"));
        else e.getSymbolTable().insert(id, type) ;

        return errors ;
    }

    public TypeNode typeCheck (Environment e) {
        return null;
    }

    public String codeGeneration(Environment e) {
        /*return exp.codeGeneration() +
                "pushr A0 \n" ;*/
        return null;
    }

    public String toPrint(String s) {
        return null;/*s + "Var:" + id + type.toPrint(" ") + "\n" + exp.toPrint(s+"\t");*/
    }

}