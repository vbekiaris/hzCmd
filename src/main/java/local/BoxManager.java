package local;

import global.Bash;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoxManager implements Serializable {

    private List<Box> boxes = new ArrayList();

    public BoxManager(){

    }

    public BoxManager(String file, String user) throws IOException, InterruptedException {
        if(file==null || user==null){
            return;
        }
        addBoxes(file, user);
    }

    public void addBoxes(String file, String user) {
        BufferedReader boxFile = null;
        try {
            boxFile = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            System.out.println(Bash.ANSI_RED+file+" not Found"+Bash.ANSI_RESET);
            System.exit(1);
        }

        try {
            String line;
            while( (line=boxFile.readLine()) !=null ){

                if(line==null || line.equals("")){
                    System.out.println(Bash.ANSI_RED+"line: "+line+" in "+file+" publicIP,privateIP"+Bash.ANSI_RESET);
                    System.exit(1);
                }

                addBox(user, line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void addBox(String user, String ipString) throws IOException, InterruptedException  {
        String[] split = ipString.split(",");

        if(split==null || split.length < 2){
            System.out.println(Bash.ANSI_RED+ipString+" expected publicIP,privateIP"+Bash.ANSI_RESET);
            System.exit(1);
        }

        Box box = new Box(user, split[0], split[1]);

        if (boxes.contains(box) ){
            return;
        }

        if(box.testConnecton()){
            boxes.add(box);
        }else {
            System.out.println(Bash.ANSI_RED+box+Bash.ANSI_RESET);
            System.exit(1);
        }
    }

    public void addBoxes(BoxManager boxManager) {
        for(Box box : boxManager.boxes) {
            if ( !this.boxes.contains(box) ){
                this.boxes.add(box);
            }
        }
    }


    public Box get(int i){
        return boxes.get(i);
    }

    public List<Box> getBoxList(){ return boxes; }

    public List<Box> getBoxListExcluding(Box thisbox){
        List<Box> sublist = new ArrayList<Box>(boxes);
        sublist.remove(thisbox);
        return sublist;
    }


    public void upload(String souce, String dest) throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.upload(souce, dest);
        }
    }

    public void download(String souce, String dest) throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.downlonad(souce, dest+"/"+box.pub);
        }
    }

    public void mkdir(String arg) throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.mkdir(arg);
        }
    }

    public void rm(String arg) throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.rm(arg);
        }
    }

    public void killAllJava( ) throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.killAllJava();
        }
    }

    public void jps() throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.jps();
        }
    }

    public void startDstat() throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.startDstat();
        }
    }

    public void stopDstat() throws IOException, InterruptedException {
        for (Box box : boxes) {
            box.stopDstat();
        }
    }

    public int size(){return boxes.size();}

    @Override
    public String toString() {

        String str = new String();
        for(Box box : boxes){
            try {
                if(box.testConnecton()){
                    str+=Bash.ANSI_GREEN + box + Bash.ANSI_RESET + "\n";
                }else {
                    str+=Bash.ANSI_RED + box + Bash.ANSI_RESET + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return str;
    }

    public int getBoxCount() {
        return boxes.size();
    }


}