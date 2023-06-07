package ast;

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

        //System.out.println(tmp.getSymbolTable().lookup("b").getStatus());

        for(Node n: innerThenStatements){
            result.addAll(n.checkSemantics(e));
        }
        result.addAll(innerThenExp.checkSemantics(e));

        //System.out.println(tmp.getSymbolTable().lookup("b").getStatus());

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
            throw new Error2("Errore di TypeChecking: Guardia non booleana.");

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
                throw new Error2("Errore di TypeChecking: Then e Else ritornano tipi differenti.");

            for(int i=0; i<e.getSymbolTable().getSize(); i++) {
                Hashtable<String, SymbolTableEntry> tmp1 = e.getSymbolTable().get(i);
                Hashtable<String, SymbolTableEntry> tmp2 = tmp.getSymbolTable().get(i);
                for(String elem: tmp1.keySet()) {
                    if((tmp1.get(elem).getStatus() == Status.INITIALIZED && tmp2.get(elem).getStatus() == Status.DECLARED) || (tmp1.get(elem).getStatus() == Status.DECLARED && tmp2.get(elem).getStatus() == Status.INITIALIZED)) {
                        System.out.println("Warning: Incompatibilità di assegnamenti nel ramo then e nel ramo false per la variabile "+tmp1.get(elem).getLabel());
                        e.getSymbolTable().get(i).get(elem).setDeclared();
                    }
                }
            }
        } else {
            //Restore a come era prima, non posso sapere
            e=tmp;
        }
        return thenTypeNode;
    }

    @Override
    public String toPrint(String s) {
        return null;
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
