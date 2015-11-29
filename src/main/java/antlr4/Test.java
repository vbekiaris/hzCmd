package antlr4;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;

/**
 * Created by danny on 29/11/2015.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, IOException {


        cmd("user danny");
        cmd("ee true");
        cmd("ee tru");
        cmd("version 3.6-RC2");
        cmd("cluster a 0 10");
        cmd("cluster a");
        cmd("cluster b 3 8");
        cmd("install");
        cmd("kill member1A");

        //cmd("// not used line");
    }

    private static  void cmd(String drinkSentence) {
        // Get our lexer
        HzCmdLexer lexer = new HzCmdLexer(new ANTLRInputStream(drinkSentence));
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        HzCmdParser parser = new HzCmdParser(tokens);
        // Specify our entry point
        HzCmdParser.StatementContext hzCmdStatment = parser.statement();


        switch (hzCmdStatment.start.getType()){
            case HzCmdParser.CLUSTER:
                System.out.println("NODE 0="+hzCmdStatment.getChild(0).getText());
                System.out.println("START = " + hzCmdStatment.start.getTokenSource());
                System.out.println("CLUSTER = " + hzCmdStatment.CLUSTER());
                System.out.println("ID = " + hzCmdStatment.ID());
                System.out.println("NUMBER = "+hzCmdStatment.NUMBER(0));
                System.out.println("NUMBER = "+hzCmdStatment.NUMBER(1));
                break;
            case HzCmdParser.USER:
                System.out.println("START = " + hzCmdStatment.start);
                System.out.println("ID = " + hzCmdStatment.ID());
        }

        System.out.println("");
    }


}
