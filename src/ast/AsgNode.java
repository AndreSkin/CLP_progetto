package ast;

import semanticanalysis.Environment;
import semanticanalysis.SimpLanCustomError;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTableEntry;

import java.util.ArrayList;

public class AsgNode implements Node{

    private String id;
    private Node exp;

    SymbolTableEntry st;
    private int nesting;
    private int nestingNode;

    public AsgNode(String id, Node exp) {
        this.id = id;
        this.exp = exp;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        st = e.getSymbolTable().lookup(id);
        nestingNode = e.getSymbolTable().nestingLookup(id);
        nesting = e.getNestingLevel();
        if(st == null){
            throw new SimpLanCustomError("Variabile "+this.id+" non dichiarata nell'ambiente corrente.");
        } else if(this.exp != null) {
            res.addAll(this.exp.checkSemantics(e));
        }
        return res;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error {
        if (st == null) {
            throw new Error();
        }
        TypeNode expNode = this.exp.typeCheck(e);
        if(!st.getType().getType().equals(expNode.getType())){
            throw new SimpLanCustomError("Errore di TypeChecking: Espressioni incompatibili durante l'assegnamento.");
        }
        st.setInitialized();
        return expNode;
    }

    @Override
    public String toPrint(String s) {
        return s + "Var:" + id + "\n" + exp.toPrint(s+"\t");
    }

    @Override
    public String codeGeneration(Environment e) {

        String getAR="";
        for (int i = 0; i < nesting - nestingNode; i++)
            getAR += "store T1 0(T1) \n";


        return exp.codeGeneration(e) +
                "move AL T1 \n"
                + getAR  //risalgo la catena statica
                + "subi T1 " + st.getOffset()+ "\n" //metto offset sullo stack
                + "load A0 0(T1) \n";
    }
}
