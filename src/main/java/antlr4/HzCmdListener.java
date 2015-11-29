// Generated from HzCmd.g4 by ANTLR 4.5
package antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HzCmdParser}.
 */
public interface HzCmdListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HzCmdParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(HzCmdParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link HzCmdParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(HzCmdParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link HzCmdParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HzCmdParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HzCmdParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HzCmdParser.StatementContext ctx);
}