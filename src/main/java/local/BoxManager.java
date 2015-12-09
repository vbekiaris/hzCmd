package local;

import global.Bash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BoxManager {

    private List<Box> boxes = new ArrayList();

    public BoxManager(){ }

    public BoxManager(List<Box> boxes){
        this.boxes = boxes;
    }

    public void addBoxes(String user, String file) throws IOException {

        BufferedReader boxFile = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while( (line=boxFile.readLine()) !=null ){
            addBox(user, line);
        }
    }

    public void addBox(String user, String ipString) {
        String[] split = ipString.split(",");
        Box ip = new Box(user, split[0], split[1]);
        boxes.add(ip);
    }

    public BoxManager getBoxes(int start, int end){
        return new BoxManager( boxes.subList(start-1,  end) );
    }

    public Box get(int i){
        return boxes.get(i);
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


    public int size(){return boxes.size();}

    @Override
    public String toString() {

        String str="";
        for(Box b : boxes){
            str+=b+"\n";
        }

        return "BoxManager{ boxes="+boxes.size()+" "+ str ;
    }
}