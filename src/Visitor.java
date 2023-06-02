import ast.*;
import ast.DeclarationNode;

import java.util.ArrayList;

public class Visitor extends SimpLanPlusBaseVisitor<Node>{
    public Node visitProgram(SimpLanPlusParser.ProgContext ctx) {
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

    public Node visitSingleExp(SimpLanPlusParser.ProgContext ctx) {
        //simply return the result of the visit to the inner exp
        return new ProgramNode(visit(ctx.exp()));
    }

    public Node visitIdInit(SimpLanPlusParser.DecContext ctx) {
        //visit the type
        TypeNode typeNode = (TypeNode) visit(ctx.type());

        //build the varNode
        return new DecNode(ctx.ID().getText(), typeNode);
    }

    public Node visitFunDec(SimpLanPlusParser.DecContext ctx) {
        ArrayList<ParNode> param = new ArrayList<ParNode>() ;

        for (SimpLanPlusParser.ParamContext vc : ctx.param())
            param.add(new ParNode(vc.ID().getText(), (Type) visit( vc.type() )) );

        //add body
        //create a list for the nested declarations
        ArrayList<Node> innerDec = new ArrayList<Node>();
        ArrayList<Node> innerStatements = new ArrayList<Node>();
        Node innerExp;

        //check whether there are actually nested decs
        SimpLanPlusParser.BodyContext body = ctx.body();
        if(body.dec() != null){
            //if there are visit each dec and add it to the @innerDec list
            for(SimpLanPlusParser.DecContext dc : body.dec())
                innerDec.add(visit(dc));
        }

        if(!body.stm().isEmpty())
            for (SimpLanPlusParser.StmContext sm: body.stm()){
                innerStatements.add(visit(sm));
            }

        if (!body.exp().isEmpty()) {
            innerExp = visit(body.exp());
        } else {
            innerExp = null;
        }
        //get the exp body
        Node exp = visit(body.exp());

        return new FunNode(ctx.ID().getText(), (Type) visit(ctx.type()), _param, innerDec, exp);
    }

    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        return new TypeNode(ctx.getText());
    }

    public Node visitExp(SimpLanPlusParser.ExpContext ctx) {
        if(ctx.exp(1) == null){ //it is a simple expression
            return visit( ctx.exp(0) );
        } else { //it is a binary expression: visit left and right
            if (ctx.plus != null)
                return new PlusNode(visit(ctx.left), visit(ctx.right));
            else return new MinusNode(visit(ctx.left), visit(ctx.right));
        }
    }
    // Integer.parseInt(ctx.INTEGER().getText())

    public Node visitTerm(TermContext ctx) {
        if(ctx.right == null){ //it is a simple expression
            return visit( ctx.left );
        } else {
            //it is a binary expression: visit left and right
            if(ctx.mul != null)
                return new MultNode(visit(ctx.left), visit(ctx.right));
            else return new DivNode(visit(ctx.left), visit(ctx.right));
        }
    }

    public Node visitFactor(FactorContext ctx) {
        if(ctx.right == null){ //it is a simple expression
            return visit( ctx.left );
        }else{ //it is a binary expression, you should visit left and right
            return new EqualNode(visit(ctx.left), visit(ctx.right));
        }
    }

    public Node visitIntVal(IntValContext ctx) {
        // notice that this method is not actually a rule but a named production #intVal
        //there is no need to perform a check here, the lexer ensures this text is an int
        return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
    }

    public Node visitBoolVal(BoolValContext ctx) {
        //there is no need to perform a check here, the lexer ensures this text is a boolean
        return new BoolNode(Boolean.parseBoolean(ctx.getText()));
    }

    public Node visitBaseExp(BaseExpContext ctx) {

        //this is actually nothing in the sense that for the ast the parenthesis are not relevant
        //the thing is that the structure of the ast will ensure the operational order by giving
        //a larger depth (closer to the leafs) to those expressions with higher importance

        //this is actually the default implementation for this method in the SimpLanBaseVisitor class
        //therefore it can be safely removed here

        return visit (ctx.exp());
    }

    public Node visitIfExp(IfExpContext ctx) {
        //visit the conditional, then the then branch, and then the else branch
        //notice once again the need of named terminals in the rule, this is because
        //we need to point to the right expression among the 3 possible ones in the rule

        Node condExp = visit (ctx.cond);

        Node thenExp = visit (ctx.thenBranch);

        Node elseExp = visit (ctx.elseBranch);

        return new IfNode(condExp, thenExp, elseExp);
    }

    public Node visitVarExp(VarExpContext ctx) {
        //this corresponds to a variable access
        return new IdNode(ctx.ID().getText());
    }

    public Node visitFunExp(FunExpContext ctx) {
        //this corresponds to a function invocation
        //declare the result
        Node res;

        //get the invocation arguments
        ArrayList<Node> args = new ArrayList<Node>();

        for (ExpContext exp : ctx.exp())
            args.add(visit(exp));

        // this is ad-hoc for this project...
        if(ctx.ID().getText().equals("print"))
            res = new PrintNode(args.get(0));

        else
            //instantiate the invocation
            res = new CallNode(ctx.ID().getText(), args);

        return res;
    }


}
