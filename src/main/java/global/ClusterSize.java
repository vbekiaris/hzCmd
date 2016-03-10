package global;

public enum ClusterSize {
    S, M, L, XL, XXL;

    public static int getMemberCount(ClusterSize size){
        switch (size){
            case S:
                return 6;
            case M:
                return 8;
            case L:
                return 16;
            case XL:
                return 100;
            case XXL:
                return 500;
            default:
                return 1;
        }
    }

    public static int getClientCount(ClusterSize size){
        switch (size){
            case S:
                return 8;
            case M:
                return 32;
            case L:
                return 64;
            case XL:
                return 1000;
            case XXL:
                return 5000;
            default:
                return 1;
        }
    }
}



