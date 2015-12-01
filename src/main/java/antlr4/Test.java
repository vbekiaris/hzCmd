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

        //Style

        //TODO what state should be preserved so we can stop and start hzCmd and still inter act with the cluster
        // proabley remote box obj,  cluster obj,
        // what classes have been loaded,  is preserved on the running cluster
        // what methods are running are presereved on the running cluster though hidden in the executor
        // all though the stoped started messages are in the in message file.

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
        cmd("add Ips agents.txt");
        cmd("add Ip 000.00.0.0,111,11,1,11");

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
        cmd("add member A 2 v1 bigJvm");
        cmd("add member B 1 v2 smlJvm");

        //load a task up to cluster
        cmd("load * task1 com.some.task");
        cmd("load A task1 com.some.task");

        //set the public vars of loaded test class
        cmd("set task1.count=2");
        cmd("set task1.mapName=map22");

        //call a method of 1 or more task's, in a cluster, on a member / client
        cmd("invoke method * 3 * *");
        cmd("invoke method task1 3 * *");
        cmd("invoke method task1 3 A *");
        cmd("invoke method task1 3 A member*");
        cmd("invoke method task1 4 A member1");

        //perform operation in a cluster, one a member/client [cat | jps | tail | clean | start | kill]
        cmd("kill * *");
        cmd("kill A *");
        cmd("kill A member*");
        cmd("kill A member1");

        //start the member jvm what was killed,  however the tasks which were loaded to the jvm are gone
        // for this reason it could be better to require explisit start after every cluster deff and add members
        cmd("start A member*");

        //grep example
        cmd("grep A member1 -A10 -B3 starting.*Now");


        //download
        cmd("download * * file/path/");

        cmd("sleep 10");

        //await for some regx string to match a msg
        cmd("await some regx to match string send to hzCmd");


        cmd("save scenario.hz");

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
