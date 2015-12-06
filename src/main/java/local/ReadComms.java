package local;

import java.io.*;

public class ReadComms extends Thread {


    private String commsFile;
    private BufferedReader commsIn;

    public volatile boolean running = true;

    private volatile boolean dead = false;

    public ReadComms(String file) throws IOException {
        commsFile =  file;
        File f = new File(commsFile);
        if(!f.exists()) {
            f.createNewFile();
        }
        commsIn = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    }


    public void run() {

        System.out.println("ReadComms STARTED");
        String line;
        while ( running ) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                while ( (line = commsIn.readLine()) != null ){
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                running=false;
            }

        }
        clearFile();
        dead = true;
    }

    private void clearFile(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(commsFile);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean dead() {
        return dead;
    }

}
