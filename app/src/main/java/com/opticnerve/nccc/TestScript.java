/*
TestScript.java

used to return and array of 3 unique ints pre each test round.

 */


package com.opticnerve.nccc;

import java.util.Random;

/**
 * Created by casey on 4/5/2017.
 */

public class TestScript {
    static int []test_order;

    public TestScript() {
    }
    public void generateScript(){
        Random rand = new Random();
        int first_int = rand.nextInt(3);
        int next_int = first_int;
        while(next_int == first_int){
            next_int = rand.nextInt(3);
        }
        int last_int = first_int;
        while(last_int == first_int || last_int == next_int){
            last_int = rand.nextInt(3);
        }
        test_order = new int [3];
        test_order[0] = first_int;
        test_order[1] = next_int;
        test_order[2] = last_int;
    }

    public static int[] getTest_order() {
        return test_order;
    }
}
