package local;

import cmdline.CmdLine;

import java.io.*;
import java.util.*;



//TODO WAN REP xml SETUP
//TODO man center integ
//TODO tail -f grepping logs and pop up display
//TODO exampe 3 3node cluster wan replicate ring, with rolling upgrade,  members putting,  clients getting,  kill restart members,  man center running,
public class HzCmd implements Serializable {

    public static final String commsFile = "commsIn.txt";


    public String homeIp;

    private BoxManager boxes = new BoxManager();
    private Map<String, ClusterManager> clusters = new HashMap();


    public void addBoxes(String user, String file){
        try {
            boxes.addBoxes(user, file);
        }catch (Exception e){

        }
    }



    private Collection<ClusterManager> selectClusterSet(String cluster) {
        Collection<ClusterManager> selected = new ArrayList();
        if( cluster.equals("*") ){
            selected = clusters.values();
        }else{
            selected.add(clusters.get(cluster));
        }
        return  selected;
    }


/*
    private ClusterManager selectSubCluster(ClusterManager c, String criterionToken) throws Exception {

        switch (criterionToken.getType()) {

            case HzCmdParser.MEMBER_ALL:
                return c.getMemberManager();

            case HzCmdParser.CLIENT_ALL:
                return c.getClientManager();

            case HzCmdParser.CLIENT_VAR:
            case HzCmdParser.MEMBER_VAR:
                String id = criterionToken.getText() + c.getClusterId();
                return c.getIDManager(id);

            default:
                return c;
        }
    }
*/


/*
    private Collection<ClusterManager> getClusterManagers(HzCmdParser.StatementContext cmd) {
        Collection<ClusterManager> c = new ArrayList();
        if( cmd.ALL(0) != null) {
            c = clusters.values();
        }else {
            String clusterId = cmd.VAR(0).getText();
            c.add( clusters.get(clusterId) );
        }
        return c;
    }
*/




    @Override
    public String toString() {
        return "HzCmd{" +
                "boxes=" + boxes +
                "homeIp='" + homeIp + '\'' +
                ", commsFile='" + commsFile + '\'' +
                ", clusters=" + clusters +
                '}';
    }


    public static HzCmd loadHzCmd(){
        HzCmd hzCmd = null;
        try {
            FileInputStream fileIn = new FileInputStream("HzCmd.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hzCmd = (HzCmd) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception e) {
            hzCmd = new HzCmd();
        }
        return hzCmd;
    }

    public static void saveHzCmd(HzCmd hzCmd){
        try {
            FileOutputStream fileOut = new FileOutputStream("HzCmd.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hzCmd);
            out.close();
            fileOut.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) throws InterruptedException, IOException {
        ReadComms readComms = new ReadComms(HzCmd.commsFile);
        readComms.read();

        HzCmd hzCmd = loadHzCmd();

        com.github.rvesse.airline.Cli<CmdLine.Command> parser = CmdLine.getParser();

        parser.parse(args).exe(hzCmd);

        saveHzCmd(hzCmd);
    }

}