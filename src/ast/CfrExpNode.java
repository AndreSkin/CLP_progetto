package ast;

import others.SimpLanlib;
import semanticanalysis.Environment;
import semanticanalysis.SimpLanCustomError;
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
                throw new SimpLanCustomError("Errore di TypeChecking: Variabili non compatibili con questa operazione.");
            }
        return new TypeNode("bool");
    }

    @Override
    public String toPrint(String s) {
        return e1.toPrint(s)+op+e2.toPrint(s);
    }

    @Override
    public String codeGeneration(Environment e) {

        String true_lab = SimpLanlib.freshLabel();
        String end = SimpLanlib.freshLabel();


        String command = switch (this.op) {
            case ">" -> "bgt T1 A0 "+true_lab+"\n";
            case "<" -> "blt T1 A0 "+true_lab+"\n";
            case ">=" -> "bgte T1 A0 "+true_lab+"\n";
            case "<=" -> "bleq T1 A0 "+true_lab+"\n";
            case "==" -> "beq T1 A0 "+true_lab+"\n";
            default -> "";
        };

        // TODO: 6/8/23 Controlla operatori

        return this.e1.codeGeneration(e)+
                "pushr A0 \n"+
                this.e2.codeGeneration(e)+
                "popr T1 \n"+
                command+
                "storei A0 0 \n"+
                "b " + end + "\n" +
                true_lab + ":\n"+
                "storei A0 1 \n"+
                end + ":\n";
    }
}
