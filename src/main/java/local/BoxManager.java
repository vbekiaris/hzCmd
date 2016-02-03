package local;

import global.Bash;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoxManager implements Serializable {

    private String id;
    private List<Box> boxes = new ArrayList();

    public BoxManager(String id){
        this.id=id;
    }

    public BoxManager(String id, List<Box> boxes){
        this.id = id;
        this.boxes = boxes;
    }

    public void addBoxes(String user, String file) throws IOException, InterruptedException  {
        BufferedReader boxFile = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while( (line=boxFile.readLine()) !=null ){
            addBox(user, line);
        }
    }

    public void addBox(String user, String ipString) throws IOException, InterruptedException  {
        String[] split = ipString.split(",");
        Box box = new Box(user, split[0], split[1]);

        if(box.testConnecton()){
            boxes.add(box);
            //System.out.println(Bash.ANSI_GREEN+box+Bash.ANSI_RESET);
        }else {
            System.out.println(Bash.ANSI_RED+box+Bash.ANSI_RESET);
        }
    }

    public BoxManager getBoxes(int start, int end){ return new BoxManager(id,  new ArrayList( boxes.subList(start-1,  end) ) ); }

    public Box get(int i){
        return boxes.get(i);
    }

    public List<Box> getBoxList(){ return boxes; }

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


    public int size(){return boxes.size();}

    @Override
    public String toString() {

        String str="\n";
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
}