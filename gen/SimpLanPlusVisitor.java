// Generated from /home/alessio/Projects/CLP_progetto/src/SimpLanPlus.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpLanPlusParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpLanPlusVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multipleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleExp(SimpLanPlusParser.MultipleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asgStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsgStm(SimpLanPlusParser.AsgStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funCallStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCallStm(SimpLanPlusParser.FunCallStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#thenStmBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThenStmBranch(SimpLanPlusParser.ThenStmBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#elseStmBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStmBranch(SimpLanPlusParser.ElseStmBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusMinusExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinusExp(SimpLanPlusParser.PlusMinusExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueExp(SimpLanPlusParser.TrueExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExp(SimpLanPlusParser.IdExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notIdExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotIdExp(SimpLanPlusParser.NotIdExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funCallExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCallExp(SimpLanPlusParser.FunCallExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bracketExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketExp(SimpLanPlusParser.BracketExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseExp(SimpLanPlusParser.FalseExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cfrExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCfrExp(SimpLanPlusParser.CfrExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExp(SimpLanPlusParser.LogicalExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExp(SimpLanPlusParser.IntExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#thenExpBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThenExpBranch(SimpLanPlusParser.ThenExpBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#elseExpBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseExpBranch(SimpLanPlusParser.ElseExpBranchContext ctx);
}