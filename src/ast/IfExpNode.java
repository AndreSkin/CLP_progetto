package ast;

import others.SimpLanlib;
import semanticanalysis.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class IfExpNode implements Node{
    Node condition;
    ArrayList<Node> innerThenStatements;
    Node innerThenExp;
    ArrayList<Node> innerElseStatements;
    Node innerElseExp;



    public IfExpNode(Node condition, ArrayList<Node> innerThenStatements, Node innerThenExp, ArrayList<Node> innerElseStatements, Node innerElseExp ) {
        this.condition = condition;
        this.innerThenStatements = innerThenStatements;
        this.innerThenExp = innerThenExp;
        this.innerElseStatements = innerElseStatements;
        this.innerElseExp = innerElseExp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {

        ArrayList<SemanticError> result = new ArrayList<SemanticError>();
        result.addAll(condition.checkSemantics(e));

        Environment tmp = new Environment(e);

        for(Node n: innerThenStatements){
            result.addAll(n.checkSemantics(e));
        }
        result.addAll(innerThenExp.checkSemantics(e));

        if(innerElseStatements != null) {
            for (Node n : innerElseStatements) {
                result.addAll(n.checkSemantics(tmp));
            }
            result.addAll(innerElseExp.checkSemantics(tmp));
        }
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        if (!condition.typeCheck(e).getType().equals("bool") )
            throw new SimpLanCustomError("Errore di TypeChecking: Guardia non booleana.");

        Environment tmp = new Environment(e);

        for(Node n: innerThenStatements){
            n.typeCheck(e);
        }
        TypeNode thenTypeNode = innerThenExp.typeCheck(e);

        if(innerElseStatements != null) {
            for (Node n : innerElseStatements) {
                n.typeCheck(tmp);
            }
            TypeNode elseTypeNode = innerElseExp.typeCheck(tmp);

            if(!thenTypeNode.getType().equals(elseTypeNode.getType()))
                throw new SimpLanCustomError("Errore di TypeChecking: Then e Else ritornano tipi differenti.");

            for(int i=0; i<e.getSymbolTable().getSize(); i++) {
                Hashtable<String, SymbolTableEntry> tmp1 = e.getSymbolTable().get(i);
                Hashtable<String, SymbolTableEntry> tmp2 = tmp.getSymbolTable().get(i);
                for(String elem: tmp1.keySet()) {
                    if((tmp1.get(elem).getStatus() == Status.INITIALIZED && tmp2.get(elem).getStatus() == Status.DECLARED) || (tmp1.get(elem).getStatus() == Status.DECLARED && tmp2.get(elem).getStatus() == Status.INITIALIZED)) {
                        //System.out.println("Warning: Incompatibilità di assegnamenti nel ramo then e nel ramo false per la variabile "+tmp1.get(elem).getLabel());
                        throw new SimpLanCustomError("Incompatibilità di assegnamenti nel ramo then e nel ramo else per la variabile "+tmp1.get(elem).getLabel());
                        //e.getSymbolTable().get(i).get(elem).setDeclared();
                    }
                }
            }
        } else {
            e=tmp;
        }
        return thenTypeNode;
    }

    @Override
    public String toPrint(String s) {
        return null;
    }

    @Override
    public String codeGeneration(Environment e)
    {
        String lthen = SimpLanlib.freshLabel();
        String lend = SimpLanlib.freshLabel();

        String stmThen = "";
        for(Node stm: innerThenStatements)
            stmThen = stmThen + stm.codeGeneration(e);

        String stmElse = "";
        for(Node stm: innerElseStatements)
            stmElse = stmElse + stm.codeGeneration(e);


        return condition.codeGeneration(e) +
            "storei T1 1 \n" +
            "beq A0 T1 "+ lthen + "\n" +
                stmElse +
                 innerElseExp.codeGeneration(e) +
            "b " + lend + "\n" +
            lthen + ":\n" +
                stmThen +
               innerThenExp.codeGeneration(e) +
            lend + ":\n" ;     }
}
