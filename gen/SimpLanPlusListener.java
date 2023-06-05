// Generated from /home/alessio/Projects/CLP_progetto/src/SimpLanPlus.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpLanPlusParser}.
 */
public interface SimpLanPlusListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multipleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterMultipleExp(SimpLanPlusParser.MultipleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multipleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code asgStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterAsgStm(SimpLanPlusParser.AsgStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code asgStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitAsgStm(SimpLanPlusParser.AsgStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funCallStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterFunCallStm(SimpLanPlusParser.FunCallStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funCallStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitFunCallStm(SimpLanPlusParser.FunCallStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#thenStmBranch}.
	 * @param ctx the parse tree
	 */
	void enterThenStmBranch(SimpLanPlusParser.ThenStmBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#thenStmBranch}.
	 * @param ctx the parse tree
	 */
	void exitThenStmBranch(SimpLanPlusParser.ThenStmBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#elseStmBranch}.
	 * @param ctx the parse tree
	 */
	void enterElseStmBranch(SimpLanPlusParser.ElseStmBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#elseStmBranch}.
	 * @param ctx the parse tree
	 */
	void exitElseStmBranch(SimpLanPlusParser.ElseStmBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMinusExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinusExp(SimpLanPlusParser.PlusMinusExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinusExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinusExp(SimpLanPlusParser.PlusMinusExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterTrueExp(SimpLanPlusParser.TrueExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitTrueExp(SimpLanPlusParser.TrueExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIdExp(SimpLanPlusParser.IdExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIdExp(SimpLanPlusParser.IdExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notIdExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNotIdExp(SimpLanPlusParser.NotIdExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notIdExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNotIdExp(SimpLanPlusParser.NotIdExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExp(SimpLanPlusParser.MulDivExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funCallExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFunCallExp(SimpLanPlusParser.FunCallExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funCallExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFunCallExp(SimpLanPlusParser.FunCallExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bracketExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBracketExp(SimpLanPlusParser.BracketExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bracketExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBracketExp(SimpLanPlusParser.BracketExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFalseExp(SimpLanPlusParser.FalseExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFalseExp(SimpLanPlusParser.FalseExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cfrExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCfrExp(SimpLanPlusParser.CfrExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cfrExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCfrExp(SimpLanPlusParser.CfrExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExp(SimpLanPlusParser.LogicalExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExp(SimpLanPlusParser.LogicalExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIntExp(SimpLanPlusParser.IntExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIntExp(SimpLanPlusParser.IntExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#thenExpBranch}.
	 * @param ctx the parse tree
	 */
	void enterThenExpBranch(SimpLanPlusParser.ThenExpBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#thenExpBranch}.
	 * @param ctx the parse tree
	 */
	void exitThenExpBranch(SimpLanPlusParser.ThenExpBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#elseExpBranch}.
	 * @param ctx the parse tree
	 */
	void enterElseExpBranch(SimpLanPlusParser.ElseExpBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#elseExpBranch}.
	 * @param ctx the parse tree
	 */
	void exitElseExpBranch(SimpLanPlusParser.ElseExpBranchContext ctx);
}