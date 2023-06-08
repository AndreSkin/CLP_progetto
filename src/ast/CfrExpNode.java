package ast;

import others.SimpLanlib;
import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class CfrExpNode implements Node{

    Node e1;
    Node e2;
    String op;

    public CfrExpNode(Node e1, String op, Node e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> result = new ArrayList<SemanticError>();
        if (this.e1 != null) {
            result.addAll(e1.checkSemantics(e));
        }
        if (this.e2 != null) {
            result.addAll(e2.checkSemantics(e));
        }
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error {

        if(op.equals("=="))
            if(!((e1.typeCheck(e).getType().equals("int") && e2.typeCheck(e).getType().equals("int"))||(e1.typeCheck(e).getType().equals("bool") && e2.typeCheck(e).getType().equals("bool")))){
                throw new Error2("Errore di TypeChecking: Variabili non compatibili con questa operazione.");
            }
        return new TypeNode("bool");
    }

    @Override
    public String toPrint(String s) {
        return e1.toPrint(s)+op+e2.toPrint(s);
    }

    @Override
    public String codeGeneration(Environment e) {

        String false_lab = SimpLanlib.freshLabel();
        String end = SimpLanlib.freshLabel();


        String command = switch (this.op) {
            case ">" -> "gt T0 A0\n";
            case "<" -> "lt T0 A0\n";
            case ">=" -> "gte T0 A0\n";
            case "<=" -> "lte T0 A0\n";
            case "==" -> "eq T0 A0\n";
            default -> "";
        };

        // TODO: 6/8/23 Controlla operatori

        return this.e1.codeGeneration(e)+
                "pushr AO \n"+
                this.e2.codeGeneration(e)+
                "popr A0 \n"+
                command+
                "storei T0 0\n"+
                "beq A0 T0 "+false_lab+"\n"+
                "storei A0 1 \n"+
                "b " + end + "\n" +
                false_lab + ":\n"+
                "storei A0 0 \n"+
                end + ":\n";
    }
}
