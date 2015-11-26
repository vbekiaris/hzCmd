package remote;

import global.Args;

import java.util.Properties;

public class HomeSettings {
    public String user;
    public String ip;
    public String cwd;
    public String inputFile;

    public HomeSettings(){
        Properties props = System.getProperties();
        user = props.getProperty(Args.homeUser.name());
        ip = props.getProperty(Args.homeIp.name());
        cwd = props.getProperty(Args.homeCwd.name());
        inputFile = props.getProperty(Args.homeInfile.name());
    }

    @Override
    public String toString() {
        return "HomeSettings{" +
                "cwd='" + cwd + '\'' +
                ", user='" + user + '\'' +
                ", ip='" + ip + '\'' +
                ", inputFile='" + inputFile + '\'' +
                '}';
    }
}
