package local;


import main.HzCmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * <a href="OrderedProperties.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class OrderedProperties extends Properties {

    public OrderedProperties() {
        super ();

        _names = new Vector();
    }

    public Enumeration propertyNames() {
        return _names.elements();
    }

    public Object put(Object key, Object value) {
        if (_names.contains(key)) {
            _names.remove(key);
        }

        _names.add(key);

        return super .put(key, value);
    }

    public Object remove(Object key) {
        _names.remove(key);

        return super .remove(key);
    }

    public void writePropertie(String file, String key, String value) throws IOException {

        File yourFile = new File(HzCmd.propertiesFile);
        yourFile.createNewFile();


        FileInputStream in = new FileInputStream(yourFile);
        load(in);
        in.close();

        setProperty(key, value);

        FileOutputStream out = new FileOutputStream(yourFile);
        store(out, null);
        out.close();
    }

    private Vector _names;

}