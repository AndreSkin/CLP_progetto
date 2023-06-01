package ast;

import java.util.ArrayList;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class DecNode implements Node {
    private String id;
    private Node type;
    private int nesting;

    public DecNode(String _id, Node _type) {
        id = _id ;
        type = _type ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        nesting = _nesting ;

        if (ST.lookup(id) != null)
            errors.add(new SemanticError("Var id " + id + " already declared"));
        else ST.insert(id, (Type) type) ;

        return errors ;
    }

    public Type typeCheck () {
       /* if (exp.typeCheck().getClass().equals(type.getClass() ))
            return null ;
        else {
            System.out.println("Type Error: incompatible type of expression for variable "+id) ;
            return new ErrorType() ;
        }*/
        return null;
    }

    public String codeGeneration() {
        /*return exp.codeGeneration() +
                "pushr A0 \n" ;*/
        return null;
    }

    public String toPrint(String s) {
        return null;/*s + "Var:" + id + type.toPrint(" ") + "\n" + exp.toPrint(s+"\t");*/
    }

}