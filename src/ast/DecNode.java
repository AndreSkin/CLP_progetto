package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class DecNode implements Node {
    private String id;
    private TypeNode type;

    public DecNode(String i, TypeNode t) {
        id = i ;
        type = t ;
    }

    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if (e.getSymbolTable().topLookup(id) != null)
            errors.add(new SemanticError("Variabile " + id + " già dichiarata nel blocco corrente."));
        else {

            e.getSymbolTable().insert(id, type, e.getOffset()) ;
            e.incrementOffset();
        }
        return errors ;
    }

    public TypeNode typeCheck (Environment e) {
        return null;
    }

    public String codeGeneration(Environment e) {
        return "subi SP 1 \n";
    }

    public String toPrint(String s) {
        return "Var: "+this.id+": "+this.type.getType();
    }

}