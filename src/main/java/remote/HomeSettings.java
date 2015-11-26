package remote;

public class HomeSettings {
    public String user;
    public String ip;
    public String cwd;
    public String inputFile;

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
