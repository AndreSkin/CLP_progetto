package ast;

import semanticanalysis.*;

import java.util.ArrayList;

public class IdNode implements Node{
    private String id;

    public IdNode(String id) {
        this.id = id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> result = new ArrayList<SemanticError>();
        SymbolTableEntry s= e.getSymbolTable().lookup(this.id);
        if (s==null)
            result.add(new SemanticError("Variabile "+this.id+" non dichiarata."));
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error{
        SymbolTableEntry s= e.getSymbolTable().lookup(this.id);
        if(s == null) {
            //non dichiarata
            throw new Error2("Errore di TypeChecking: Variabile "+this.id+" non dichiarata.");
        }
        //Qui sto facendo typecheck di quando la variabile occorre come termine di una exp, quindi qui devo controllare l'inizializzazione
        if (s.getStatus() == Status.DECLARED) {
            throw new Error2("Errore di TypeChecking: Uso della variabile "+s.getLabel()+" dichiarata ma non inizializzata.");
        }
        //se volessi implementare anche le usate e non usate, l'update va messo qui
        return s.getType();
    }

    @Override
    public String toPrint(String s) {
        return s+this.id;
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }

    public String getId() {
        return this.id;
    }
}
