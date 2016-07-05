package local.properties;

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
public class OrderedProperties extends Properties {

    private AtomicBoolean loaded = new AtomicBoolean(false);

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


    public void load(String file) throws IOException{
        File f = new File(file);
        f.createNewFile();

        FileInputStream in = new FileInputStream(f);
        load(in);
        in.close();
    }

    public void store(String file) throws IOException{
        File f = new File(file);
        FileOutputStream out = new FileOutputStream(f);
        store(out, null);
        out.close();
    }

    public void writePropertie(String file, String key, String value) throws IOException {
        load(file);
        setProperty(key, value);
        store(file);
    }

    public String readPropertie(String file, String key, String defaultValue) throws IOException {
        if ( loaded.compareAndSet(false, true) ){
            load(file);
        }
        return getProperty(key, defaultValue);
    }

    private Vector _names;

}