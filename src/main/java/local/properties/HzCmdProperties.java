package local.properties;

import local.bench.FieldValue;
import main.HzCmd;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HzCmdProperties extends OrderedProperties {

    public static final String CLIENT_OPS = "CLIENT_OPS";
    public static final String MEMBER_OPS = "MEMBER_OPS";


    public static final String REDIS_REPLICAS = "REDIS_REPLICAS";

    public static final String JHIC = "JHIC";
    public static final String JHIC_ARGS = "JHIC_ARGS";

    public static final String JFR = "JFR";
    public static final String JFR_ARGS = "JFR_ARGS";

    public static final String GC_LOG = "GC_LOG";

    public static final String BENCH_DRIVER = "driver";
    public static final String BENCH_DURATION = "duration";
    public static final String BENCH_INTERVAL = "interval";
    public static final String BENCH_RECORD_EXCEPTION = "recordException";
    public static final String BENCH_TYPE = "type";
    public static final String BENCH_THREADS = "threads";
    public static final String BENCH_WARMUP = "warmup";
    public static final String BENCH_DUMMEY = "dummey";


    public void writePropertie(String key, String value) throws IOException {
        writePropertie( HzCmd.propertiesFile, key, value );
    }

    public String readPropertie(String key, String defaultValue) throws IOException {
        return readPropertie(HzCmd.propertiesFile, key, defaultValue);
    }

    public boolean getBoolean(String key, String defaultValue) throws IOException {
        return Boolean.parseBoolean( readPropertie(HzCmd.propertiesFile, key, defaultValue) );
    }

    public List<String> getList(String key, String defaultValue) throws IOException {
        String csv = readPropertie(HzCmd.propertiesFile, key, defaultValue);
        return Arrays.asList( csv.split(",") );
    }

    public FieldValue getFieldValue(String key, String defaultValue) throws IOException {
        String value = readPropertie(HzCmd.propertiesFile, key, defaultValue);
        FieldValue fieldValue = new FieldValue(key, value);
        return fieldValue;
    }
}