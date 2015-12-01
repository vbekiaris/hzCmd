package local;

import java.io.*;

public class ReadComms extends Thread {


    public static String commsFile = "commsIn.txt";
    private BufferedReader commsIn;

    public volatile boolean running = true;

    private volatile boolean dead = false;

    public ReadComms() throws IOException {
        File f = new File(commsFile);
        if(!f.exists()) {
            f.createNewFile();
        }
        commsIn = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    }


    public void run() {
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
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(commsFile);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dead = true;
    }

    public boolean dead() {
        return dead;
    }


}
