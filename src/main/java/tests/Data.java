package tests;

import java.io.Serializable;

/**
 * Created by danny on 14/12/2015.
 */
public class Data implements Serializable{

    long now;

    byte[] load1 = new byte[10000];
    char[] load2 = new char[10000];
}
