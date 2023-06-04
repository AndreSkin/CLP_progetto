import ast.*;

import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class Visitor extends SimpLanPlusBaseVisitor<Node>{
    public Node visitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx) {
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

        for (SimpLanPlusParser.StmContext sm: ctx.stm()){
            statements.add(visit(sm));
        }
        if (!ctx.exp().isEmpty()) {
            exp = visit(ctx.exp());
        } else {
            exp = null;
        }

        return new ProgramNode(declarations, statements, exp);
    }

    public Node visitSingleExp(SimpLanPlusParser.SingleExpContext ctx) {
        System.out.println("visitSingleExp");

        //simply return the result of the visit to the inner exp
        return new ProgramNode(visit(ctx.exp()));
    }

    public Node visitVarDec(SimpLanPlusParser.VarDecContext ctx) {
        System.out.println("visitVarDec");

        //visit the type
        TypeNode typeNode = (TypeNode) visit(ctx.type());

        //build the varNode
        return new DecNode(ctx.ID().getText(), typeNode);
    }

    public Node visitFunDec(SimpLanPlusParser.FunDecContext ctx) {
        System.out.println("visitFunDec");

        ArrayList<Node> param = new ArrayList<Node>() ;

        for (SimpLanPlusParser.ParamContext p : ctx.param())
            param.add(visit(p));

        //Visita del corpo
        ArrayList<Node> innerDecs = new ArrayList<Node>();
        ArrayList<Node> innerStatements = new ArrayList<Node>();
        Node innerExp;
        //check whether there are actually nested decs
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
        //get the exp body
        Node exp = visit(body.exp());

        return new DecFunNode(visit(ctx.type()), ctx.ID().getText(), param, innerDecs, innerStatements, innerExp);
    }

    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        System.out.println("visitType");
        return new TypeNode(ctx.getText());
    }

    public Node visitAsgStm(SimpLanPlusParser.AsgStmContext ctx) {
        System.out.println("visitAsgStm");
        return new AsgNode();
    }

    //todo
    public Node visitFunCallStm(SimpLanPlusParser.FunCallStmContext ctx) {
        System.out.println("visitFunCallStm");
        return null;
    }

    public Node visitIfStm(SimpLanPlusParser.IfStmContext ctx) {
        System.out.println("visitIfStm");
        return null;
    }

    public Node visitIntExp(SimpLanPlusParser.IntExpContext ctx) {
        System.out.println("visitIntExp");
        return new IntNode(Integer.parseInt(ctx.getText()));
    }

    public Node visitTrueExp(SimpLanPlusParser.TrueExpContext ctx) {
        System.out.println("visitTrueExp");
        return new BoolNode(Boolean.parseBoolean(ctx.getText()));
    }

    public Node visitFalseExp(SimpLanPlusParser.FalseExpContext ctx) {
        System.out.println("visitFalseExp");
        return new BoolNode(Boolean.parseBoolean(ctx.getText()));
    }

    public Node visitIdExp(SimpLanPlusParser.IdExpContext ctx) {
        System.out.println("visitIdExp");
        return new IdNode(ctx.getText());
    }

    public Node visitNotIdExp(SimpLanPlusParser.NotIdExpContext ctx) {
        System.out.println("visitNotIdExp");
        return new NotExpNode(visit(ctx.exp()));
    }

    public Node visitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx) {
        System.out.println("visitMulDivExp");
        System.out.println(ctx.children.get(1));
        return new MulDivNode(visit(ctx.exp(0)), "*", visit(ctx.exp(1)));
    }

    public Node visitPlusMinusExp(SimpLanPlusParser.PlusMinusExpContext ctx) {
        System.out.println("visitPlusMinusExp");
        return null;
    }

    public Node visitCfrExp(SimpLanPlusParser.CfrExpContext ctx) {
        System.out.println("visitCfrExp");
        return null;
    }

    public Node visitLogicalExp(SimpLanPlusParser.LogicalExpContext ctx) {
        System.out.println("visitLogicalExp");
        return null;
    }

    public Node visitIfExp(SimpLanPlusParser.IfExpContext ctx) {
        System.out.println("visitIfExp");
        return null;
    }

    public Node visitBracketExp(SimpLanPlusParser.BracketExpContext ctx) {
        System.out.println("visitBracketExp");
        return null;
    }

    public Node visitFunCallExp(SimpLanPlusParser.FunCallExpContext ctx) {
        System.out.println("visitFunCallExp");
        return null;
    }

}
