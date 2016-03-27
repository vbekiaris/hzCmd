package local;


import main.HzCmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <a href="OrderedProperties.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class HzCmdProperties extends OrderedProperties {

    public static final String clientOps = "clientOps";
    public static final String memberOps = "memberOps";


    public static final String redisReplicas = "redisReplicas";

    public static final String jhic = "jhic";


    public void writePropertie(String key, String value) throws IOException {
        writePropertie(HzCmd.propertiesFile, key, value);
    }

    public String readPropertie(String key, String defaultValue) throws IOException {
        return readPropertie(HzCmd.propertiesFile, key, defaultValue);
    }

    public boolean getBoolean(String key, String defaultValue){
        return Boolean.parseBoolean(getProperty(key, defaultValue));
    }
}