package local;

public class IpPair{
    public String pub;
    public String pri;

    public IpPair(String pub, String pri){
        this.pub=pub;
        this.pri=pri;
    }

    public String toString() {
        return "IpPair{" +
                "pub='" + pub + '\'' +
                ", pri='" + pri + '\'' +
                '}';
    }
}

