import ast.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class Visitor extends SimpLanPlusBaseVisitor<Node>{
    boolean log;
    public Visitor(boolean log) {
        this.log = log;
    }
    public Node visitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx) {
        if(this.log)
            System.out.println("visitMultipleExp");
        ProgramNode result;
        //list of declarations in @res
        ArrayList<Node> declarations = new ArrayList<Node>();
        //list of statements
        ArrayList<Node> statements = new ArrayList<Node>();

        Node exp;

        for (SimpLanPlusParser.DecContext dc : ctx.dec()){
            declarations.add(visit(dc));
        }

        if (ctx.stm() != null)
            for (SimpLanPlusParser.StmContext sm: ctx.stm()){
                statements.add(visit(sm));
            }

        if (ctx.exp() != null) {
            exp = visit(ctx.exp());
        } else {
            exp = null;
        }

        return new ProgramNode(declarations, statements, exp);
    }

    public Node visitSingleExp(SimpLanPlusParser.SingleExpContext ctx) {
        if(this.log)
            System.out.println("visitSingleExp");
        //simply return the result of the visit to the inner exp
        return new ProgramNode(visit(ctx.exp()));
    }

    public Node visitVarDec(SimpLanPlusParser.VarDecContext ctx) {
        if(this.log)
            System.out.println("visitVarDec");

        //visit the type
        TypeNode typeNode = (TypeNode) visit(ctx.type());

        //build the varNode
        return new DecNode(ctx.ID().getText(), typeNode);
    }

    public Node visitParam(SimpLanPlusParser.ParamContext ctx) {
        if(this.log)
            System.out.println("visitParam");

        //visit the type
        TypeNode typeNode = (TypeNode) visit(ctx.type());

        //build the varNode
        return new ArgNode(typeNode,ctx.ID().getText());
    }

    public Node visitFunDec(SimpLanPlusParser.FunDecContext ctx) {
        // TODO: 6/6/23 Controlla anche funzione senza parametri
        if(this.log)
            System.out.println("visitFunDec");

        ArrayList<Node> param = new ArrayList<Node>() ;

        for (SimpLanPlusParser.ParamContext p : ctx.param())
            param.add(visit(p));

        //Visita del corpo
        ArrayList<Node> innerDecs = new ArrayList<Node>();
        ArrayList<Node> innerStatements = new ArrayList<Node>();
        Node innerExp;

        SimpLanPlusParser.BodyContext body = ctx.body();

        if(body.dec() != null)
            for(SimpLanPlusParser.DecContext dc : body.dec())
                innerDecs.add(visit(dc));


        if(body.stm() != null)
            for (SimpLanPlusParser.StmContext sm: body.stm()){
                innerStatements.add(visit(sm));
            }

        if (body.exp() != null) {
            innerExp = visit(body.exp());
        } else {
            innerExp = null;
        }

        return new DecFunNode(visit(ctx.type()), ctx.ID().getText(), param, innerDecs, innerStatements, innerExp);
    }

    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        if(this.log)
            System.out.println("visitType");
        return new TypeNode(ctx.getText());
    }

    public Node visitAsgStm(SimpLanPlusParser.AsgStmContext ctx) {
        if(this.log)
            System.out.println("visitAsgStm");
        return new AsgNode(ctx.ID().getText(), visit(ctx.exp()));
    }



    public Node visitIfStm(SimpLanPlusParser.IfStmContext ctx) {
        if(this.log)
            System.out.println("visitIfStm");

        SimpLanPlusParser.ThenStmBranchContext thenS = ctx.thenB;
        ArrayList<Node> innerThenStatements = new ArrayList<Node>();
        for(SimpLanPlusParser.StmContext sm: thenS.stm()){
            innerThenStatements.add(visit(sm));
        }

        SimpLanPlusParser.ElseStmBranchContext elseS = ctx.elseB;
        ArrayList<Node> innerElseStatements = new ArrayList<Node>();
        if(elseS != null)
            for(SimpLanPlusParser.StmContext sm: elseS.stm()){
                innerElseStatements.add(visit(sm));
            }

        return new IfStmNode(visit(ctx.condition), innerThenStatements, innerElseStatements);
    }

    public Node visitIntExp(SimpLanPlusParser.IntExpContext ctx) {
        if(this.log)
            System.out.println("visitIntExp");
        return new IntNode(Integer.parseInt(ctx.getText()));
    }

    public Node visitTrueExp(SimpLanPlusParser.TrueExpContext ctx) {
        if(this.log)
            System.out.println("visitTrueExp");
        return new BoolNode(Boolean.parseBoolean(ctx.getText()));
    }

    public Node visitFalseExp(SimpLanPlusParser.FalseExpContext ctx) {
        if(this.log)
            System.out.println("visitFalseExp");
        return new BoolNode(Boolean.parseBoolean(ctx.getText()));
    }

    public Node visitIdExp(SimpLanPlusParser.IdExpContext ctx) {
        if(this.log)
            System.out.println("visitIdExp");
        return new IdNode(ctx.getText());
    }

    public Node visitNotIdExp(SimpLanPlusParser.NotIdExpContext ctx) {
        if(this.log)
            System.out.println("visitNotIdExp");
        return new NotExpNode(visit(ctx.exp()));
    }

    public Node visitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx) {
        if(this.log)
            System.out.println("visitMulDivExp");
        return new MulDivNode(visit(ctx.e1), ctx.op.getText(), visit(ctx.e2));
    }
// term   : left=exp ((mul='*' | div='/') right=exp)?
    public Node visitPlusMinusExp(SimpLanPlusParser.PlusMinusExpContext ctx) {
        if(this.log)
            System.out.println("visitPlusMinusExp");
        return new PlusMinusNode(visit(ctx.e1), ctx.op.getText(), visit(ctx.e2));
    }

    public Node visitCfrExp(SimpLanPlusParser.CfrExpContext ctx) {
        if(this.log)
            System.out.println("visitCfrExp");
        return new CfrExpNode(visit(ctx.e1),ctx.op.getText(), visit(ctx.e2));
    }

    public Node visitLogicalExp(SimpLanPlusParser.LogicalExpContext ctx) {
        if(this.log)
            System.out.println("visitLogicalExp");
        return new LogicalExpNode(visit(ctx.e1),ctx.op.getText(), visit(ctx.e2));
    }

    public Node visitIfExp(SimpLanPlusParser.IfExpContext ctx) {
        if(this.log)
            System.out.println("visitIfExp");

        SimpLanPlusParser.ThenExpBranchContext thenS = ctx.thenB;
        ArrayList<Node> innerThenStatements = new ArrayList<Node>();
        for(SimpLanPlusParser.StmContext sm: thenS.stm()){
            innerThenStatements.add(visit(sm));
        }
        Node innerThenExp = visit(thenS.exp());

        SimpLanPlusParser.ElseExpBranchContext elseS = ctx.elseB;
        ArrayList<Node> innerElseStatements = new ArrayList<>();
        Node innerElseExp = null;
        if(elseS != null) {
            for(SimpLanPlusParser.StmContext sm: elseS.stm()){
                innerElseStatements.add(visit(sm));
            }
            innerElseExp = visit(elseS.exp());
        }

        return new IfExpNode(visit(ctx.condition), innerThenStatements, innerThenExp, innerElseStatements, innerElseExp);
    }

    public Node visitBracketExp(SimpLanPlusParser.BracketExpContext ctx) {
        if(this.log)
            System.out.println("visitBracketExp");
        return visit(ctx.exp());
    }

    public Node visitFunCallExp(SimpLanPlusParser.FunCallExpContext ctx) {
        // TODO: 6/6/23 Controlla bene la differenza tra le due chiamate di funzione. Posso usare lo stesso Nodo?
        if(this.log)
            System.out.println("visitFunCallExp");

        ArrayList<Node> params = new ArrayList<>();
        for(SimpLanPlusParser.ExpContext e: ctx.exp()) {
            params.add(visit(e));
        }
        return new FunCallNode(ctx.ID().getText(), params);
    }

    public Node visitFunCallStm(SimpLanPlusParser.FunCallStmContext ctx) {
        if(this.log)
            System.out.println("visitFunCallStm");
        return null;
    }

}
