package ast;

import semanticanalysis.*;

import java.util.ArrayList;

public class IdNode implements Node{
    private String id;
    SymbolTableEntry s;

    private int nesting;
    private int nestingNode;
    public IdNode(String id) {
        this.id = id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> result = new ArrayList<SemanticError>();
        s= e.getSymbolTable().lookup(this.id);
        this.nestingNode = e.getSymbolTable().nestingLookup(this.id);
        this.nesting = e.getNestingLevel();
        if (s==null)
            result.add(new SemanticError("Variabile "+this.id+" non dichiarata."));
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error{
        //SymbolTableEntry s = e.getSymbolTable().lookup(this.id);
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
    public String codeGeneration(Environment e) {

        // TODO: 6/8/23 Controlla offset e inserimento della variabile, non viene fatto

        String getAR="";
        for (int i = 0; i < nesting - nestingNode; i++)
            getAR += "store T1 0(T1) \n";

        return  "move AL T1 \n"
                + getAR  //risalgo la catena statica
                + "subi T1 " + s.getOffset() + "\n" //metto offset sullo stack
                + "store A0 0(T1) \n"; //carico sullo stack il valore all'indirizzo ottenuto
    }

    public String getId() {
        return this.id;
    }
}
