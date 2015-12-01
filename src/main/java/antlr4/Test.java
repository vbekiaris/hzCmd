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

        //style 1
        cmd("user danny");
        cmd("ee true");
        cmd("ee tru");
        cmd("version 3.6-RC2");
        cmd("cluster A 0 10");
        cmd("cluster A");      //set current working cluster
        cmd("cluster B 3 8");
        cmd("kill member1A");


        //style 2

        //variable assignment
        cmd("v1 = 3.5");
        cmd("v2 = 3.6");
        cmd("bigJvm = -xmx16G");
        cmd("smlJvm = -xmx2G");
        cmd("wanSingle=com.hazelcast.enterprise.wan.replication.WanNoDelayReplication");
        cmd("wanBatch=com.hazelcast.enterprise.wan.replication.WanBatchReplication");


        //set globals
        cmd("user danny");

        //adds ips to box manager from file or one by one
        cmd("addIps agents.txt");
        cmd("addIp 000.00.0.0,111,11,1,11");

        //define cluster name and which boxes it runs on
        //(makes copy of hazelCast xml for this cluster,  e.g. puts these ip in members list)
        cmd("cluster A 1 5");
        cmd("cluster B 1 5");

        //set up wan replication in xml using wan-replication-ref name="wanReplication" and repImplClass="com.hazelcast.enterprise.wan.replication.WanNoDelayReplication"
        cmd("replicate A B wanReplication wanSingle");
        cmd("replicate B A wanReplication wanBatch");


        //install / upload hz jars to cluster
        cmd("install * OS v1 v2");
        cmd("install A EE v1 v2");

        //add member / clients to clusters and start its jvm
        cmd("member A 2 v1 bigJvm");
        cmd("member B 1 v2 smlJvm");

        //load a task up to cluster
        cmd("load * task1 com.some.task");
        cmd("load A task1 com.some.task");

        //call a method of 1 or more task's, in a cluster, on a member / client
        cmd("invoke method * 3 * *");
        cmd("invoke method task1 3 * *");
        cmd("invoke method task1 3 A *");
        cmd("invoke method task1 3 A member*");
        cmd("invoke method task1 4 A member1");

        //perform operation on a jvm in a cluster, one a member / client    [cat | jps | tail | clean | start | kill]
        cmd("kill * *");
        cmd("kill A *");
        cmd("kill A member*");
        cmd("kill A member1");


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
