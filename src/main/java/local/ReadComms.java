package local;

import global.Bash;

import java.io.*;

public class ReadComms {

    private String commsFile;
    private BufferedReader commsIn;

    public ReadComms(String file) throws IOException {
        commsFile =  file;
        File f = new File(commsFile);
        if(!f.exists()) {
            f.createNewFile();
        }
        commsIn = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    }


    public void read() {
        String line;
        try {
            while ( (line = commsIn.readLine()) != null ){

                String color = Bash.ANSI_PURPLE;
                if(line.startsWith("ERROR")){
                    color = Bash.ANSI_RED;
                }

                System.out.println(color+line+Bash.ANSI_RESET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
