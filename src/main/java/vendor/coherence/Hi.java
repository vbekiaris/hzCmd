package vendor.coherence;


import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;


public class Hi {
    public static void main(String[] args) {

        String key = "JavaHonk";
        String value = "Java Honk Hello World!";

        NamedCache cache = CacheFactory.getCache("javahonk-hello-world");

        cache.put(key, value);
        System.out.println((String)cache.get(key));
    }
}