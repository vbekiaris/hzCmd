package local;


import main.HzCmd;
import java.io.IOException;

public class HzCmdProperties extends OrderedProperties {

    public static final String clientOps = "clientOps";
    public static final String memberOps = "memberOps";


    public static final String redisReplicas = "redisReplicas";

    public static final String jhic = "jhic";
    public static final String jhicArgs = "jhicArgs";

    public static final String JFR = "jfr";
    public static final String JFRARGS = "jfrArgs";

    public static final String GCLOG = "gclog";

    public static final String BENCH_NUMBER = "benchNumber";


    public void writePropertie(String key, String value) throws IOException {
        writePropertie( HzCmd.propertiesFile, key, value );
    }

    public void  writeIntPropertie(String key, int defaultValue) throws IOException {
        writePropertie( HzCmd.propertiesFile, key, Integer.toString(defaultValue) );
    }

    public String readPropertie(String key, String defaultValue) throws IOException {
        return readPropertie(HzCmd.propertiesFile, key, defaultValue);
    }

    public int readIntPropertie(String key, int defaultValue) throws IOException {
        return Integer.parseInt(readPropertie(HzCmd.propertiesFile, key, "" + defaultValue));
    }

    public boolean getBoolean(String key, String defaultValue) throws IOException {
        return Boolean.parseBoolean( readPropertie(HzCmd.propertiesFile, key, defaultValue) );
    }
}