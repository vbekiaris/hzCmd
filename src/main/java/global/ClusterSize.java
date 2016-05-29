package global;

public enum ClusterSize {
    M4C40, M4C80, M4C400, M4C4000, M4C6000, M4C8000, M4, XS, S, M, L, XL, XXL;

    public static int getMemberCount(ClusterSize size){
        switch (size){
            case M4C40:
            case M4C80:
            case M4C400:
            case M4C4000:
            case M4C6000:
            case M4C8000:
                return 4;
            case M4:
                return 4;
            case XS:
                return 4;
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
            case M4C40:
                return 40;
            case M4C80:
                return 80;
            case M4C400:
                return 400;
            case M4C4000:
                return 4000;
            case M4C6000:
                return 6000;
            case M4C8000:
                return 8000;
            case M4:
                return 0;
            case XS:
                return 4;
            case S:
                return 6;
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



