package global;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClusterSize {

    private String code;

    public ClusterSize(String code){
        this.code=code;
    }

    public int getMemberCount(){
        Pattern pattern = Pattern.compile("M[0-9]+");
        Matcher matcher = pattern.matcher(code);
        if (matcher.find())
        {
            String intStr = matcher.group().substring(1);
            return Integer.parseInt(intStr);
        }
        return 0;
    }

    public int getClientCount( ){
        Pattern pattern = Pattern.compile("C[0-9]+");
        Matcher matcher = pattern.matcher(code);
        if (matcher.find())
        {
            String intStr = matcher.group().substring(1);
            return Integer.parseInt(intStr);
        }
        return 0;
    }

    public  int dedicatedMemberBox( ){
        if ( code.contains("D") ){
            return getMemberCount();
        }
        return 0;
    }
}



