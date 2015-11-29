package antlr4;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AntlrDrinkListener extends HzCmdBaseListener {


    public void enterScript(HzCmdParser.ScriptContext ctx) {

        System.out.println("Entering enterDrinkSentence : " + ctx.getText());

        final HzCmdParser.StatementContext statementContext = ctx.statement().get(0);

        System.out.println("cluster="+statementContext.CLUSTER());
        System.out.println("name="+statementContext.ID());
        System.out.println("int1="+statementContext.NUMBER() );
        System.out.println("int2="+statementContext.NUMBER(1) );
    }

    public void exitScript(HzCmdParser.ScriptContext ctx) { }
    public void enterStatement(HzCmdParser.StatementContext ctx) { }
    public void exitStatement(HzCmdParser.StatementContext ctx) { }
    public void enterEveryRule(ParserRuleContext ctx) { }
    public void exitEveryRule(ParserRuleContext ctx) { }
    public void visitTerminal(TerminalNode node) { }
    public void visitErrorNode(ErrorNode node) { }


}