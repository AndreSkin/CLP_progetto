import ast.*;

import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class Visitor extends SimpLanPlusBaseVisitor<Node>{
    public Node visitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx) {
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
        //simply return the result of the visit to the inner exp
        return new ProgramNode(visit(ctx.exp()));
    }

    public Node visitVarDec(SimpLanPlusParser.VarDecContext ctx) {
        //visit the type
        TypeNode typeNode = (TypeNode) visit(ctx.type());

        //build the varNode
        return new DecNode(ctx.ID().getText(), typeNode);
    }

    public Node visitFunDec(SimpLanPlusParser.FunDecContext ctx) {
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
        return new TypeNode(ctx.getText());
    }

    public Node visitAsgStm(SimpLanPlusParser.AsgStmContext ctx) {
        return new AsgNode();
    }

    //todo
    public Node visitFunCallStm(SimpLanPlusParser.FunCallStmContext ctx) {
        return null;
    }

    public Node visitIfStm(SimpLanPlusParser.IfStmContext ctx) {
        return null;
    }

    public Node visitIntExp(SimpLanPlusParser.IntExpContext ctx) {
        return null;
    }

    public Node visitTrueExp(SimpLanPlusParser.TrueExpContext ctx) {
        return null;
    }

    public Node visitFalseExp(SimpLanPlusParser.FalseExpContext ctx) {
        return null;
    }

    public Node visitIdExp(SimpLanPlusParser.IdExpContext ctx) {
        return null;
    }

    public Node visitNotIdExp(SimpLanPlusParser.NotIdExpContext ctx) {
        return null;
    }

    public Node visitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx) {
        return null;
    }

    public Node visitPlusMinusExp(SimpLanPlusParser.PlusMinusExpContext ctx) {
        return null;
    }

    public Node visitCfrExp(SimpLanPlusParser.CfrExpContext ctx) {
        return null;
    }

    public Node visitLogicalExp(SimpLanPlusParser.LogicalExpContext ctx) {
        return null;
    }

    public Node visitIfExp(SimpLanPlusParser.IfExpContext ctx) {
        return null;
    }

    public Node visitBracketExp(SimpLanPlusParser.BracketExpContext ctx) {
        return null;
    }

    public Node visitFunCallExp(SimpLanPlusParser.FunCallExpContext ctx) {
        return null;
    }

}
