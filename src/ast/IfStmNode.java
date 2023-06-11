package ast;

import others.SimpLanlib;
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
        Environment tmp = new Environment(e);

        //System.out.println(tmp.getSymbolTable().lookup("b").getStatus());

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
    public TypeNode typeCheck(Environment e) throws Error2{
        if (!condition.typeCheck(e).getType().equals("bool") )
            throw new Error2("Errore di TypeChecking: Guardia non booleana.");

        Environment tmp = new Environment(e);

        //System.out.println(tmp.getSymbolTable().lookup("b").getStatus());

        for(Node n: then_node) {
            n.typeCheck(e);
        }

        if(else_node != null) {
            //System.out.println(e.getSymbolTable().lookup("b").getStatus());
            //System.out.println(tmp.getSymbolTable().lookup("b").getStatus());

            for (Node n : else_node) {
                n.typeCheck(tmp);
            }

            for(int i=0; i<e.getSymbolTable().getSize(); i++) {
                Hashtable<String, SymbolTableEntry> tmp1 = e.getSymbolTable().get(i);
                Hashtable<String, SymbolTableEntry> tmp2 = tmp.getSymbolTable().get(i);
                for(String elem: tmp1.keySet()) {
                    if((tmp1.get(elem).getStatus() == Status.INITIALIZED && tmp2.get(elem).getStatus() == Status.DECLARED) || (tmp1.get(elem).getStatus() == Status.DECLARED && tmp2.get(elem).getStatus() == Status.INITIALIZED)) {
                        throw new Error2("Incompatibilità di assegnamenti nel ramo then e nel ramo false per la variabile "+tmp1.get(elem).getLabel());
                        //System.out.println("Warning: Incompatibilità di assegnamenti nel ramo then e nel ramo false per la variabile "+tmp1.get(elem).getLabel());
                        //e.getSymbolTable().get(i).get(elem).setDeclared();
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
    public String codeGeneration(Environment e) {
        String lthen = SimpLanlib.freshLabel();
        String lend = SimpLanlib.freshLabel();

        String stmThen = "";
        for(Node stm: this.then_node)
            stmThen = stmThen + stm.codeGeneration(e);

        String stmElse = "";
        for(Node stm: this.else_node)
            stmElse = stmElse + stm.codeGeneration(e);


        return condition.codeGeneration(e) +
                "storei T1 1 \n" +
                "beq A0 T1 "+ lthen + "\n" +
                stmElse +
                "b " + lend + "\n" +
                lthen + ":\n" +
                stmThen +
                lend + ":\n" ;
    }

}
