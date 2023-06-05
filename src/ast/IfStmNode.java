package ast;

import semanticanalysis.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class IfStmNode implements Node{
    Node condition;
    ArrayList<Node> then_node;
    ArrayList<Node> else_node;

    public IfStmNode(Node condition, ArrayList<Node> then_node, ArrayList<Node> else_node) {
        this.condition = condition;
        this.then_node = then_node;
        this.else_node = else_node;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> result = new ArrayList<SemanticError>();
        result.addAll(condition.checkSemantics(e));
        Environment tmp = e;

        for(Node n: then_node) {
            result.addAll(n.checkSemantics(e));
        }

        if(else_node != null) {
            for (Node n : else_node) {
                result.addAll(n.checkSemantics(tmp));
            }
        }
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        if (!condition.typeCheck(e).getType().equals("bool") )
            throw new Error2("Errore di TypeChecking: Guardia non booleana.");

//DA MODIFICARE, SERVE DEEP COPY
        Environment tmp = new Environment();
        tmp.copy(e.getSymbolTable());
        System.out.println(tmp.getSymbolTable().lookup("b").getStatus());

        for(Node n: then_node) {
            n.typeCheck(e);
        }

        if(else_node != null) {
            System.out.println(tmp.getSymbolTable().lookup("b").getStatus());

            for (Node n : else_node) {
                n.typeCheck(tmp);
            }

            for(int i=0; i<e.getSymbolTable().getSize(); i++) {
                Hashtable<String, SymbolTableEntry> tmp1 = e.getSymbolTable().get(i);
                Hashtable<String, SymbolTableEntry> tmp2 = tmp.getSymbolTable().get(i);
                for(String elem: tmp1.keySet()) {
                    if((tmp1.get(elem).getStatus() == Status.INITIALIZED && tmp2.get(elem).getStatus() == Status.DECLARED) || (tmp1.get(elem).getStatus() == Status.DECLARED && tmp2.get(elem).getStatus() == Status.INITIALIZED)) {
                        e.getSymbolTable().get(i).get(elem).setDeclared();
                    }
                }
            }
        } else {
            //restore dell'ambiente, non posso sapere
            e = tmp;
        }
        return null;
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
