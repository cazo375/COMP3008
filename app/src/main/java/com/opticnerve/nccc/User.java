package com.opticnerve.nccc;


import java.util.Random;
/**
 * Created by casey on 4/5/2017.
 */

public class User {

    private static String user_name;

    private static int [] gmail_pass;
    private static int [] facebook_pass;
    private static int [] bank_pass;

    private static int [] gmail_attempts;
    private static int [] facebook_attempts;
    private static int [] bank_attempts;

    private static boolean[] gmail_successes;
    private static boolean[] facebook_successes;
    private static boolean[] bank_successes;


    public User(){

    }

    public User(String name){
        user_name = name;
        generatePasswords();
    }

    public void generatePasswords(){
        gmail_pass = passCreator();
        facebook_pass = passCreator();
        bank_pass = passCreator();
        gmail_attempts = new int [3];
        facebook_attempts = new int [3];
        bank_attempts = new int [3];
        gmail_successes = new boolean[3];
        facebook_successes = new boolean[3];
        bank_successes = new boolean[3];
        for(int i = 0; i<3; i++){
            gmail_attempts[i] = 0;
            facebook_attempts[i] = 0;
            bank_attempts[i] = 0;
            gmail_successes[i] = false;
            facebook_successes[i] = false;
            facebook_successes[i] = false;
        }
    }

    public void recordResults(int type, int itr, int attemps, boolean outcome){
        if(type == 0){
            gmail_attempts[itr] = attemps;
            gmail_successes[itr] = outcome;
        }
        else if(type == 1){
            facebook_attempts[itr] = attemps;
            facebook_successes[itr] = outcome;
        }
        else if(type == 2){
            bank_attempts[itr] = attemps;
            bank_successes[itr] = outcome;
        }
    }

    public String printResults(){
        String out = "Username: " + user_name + "\n\n"+
                "Gmail: [" + gmail_attempts[0] + ", " + gmail_attempts[1] + ", " + gmail_attempts[2] + "]\n" +
                "Pass?: [" + gmail_successes[0] + ", " + gmail_successes[1] + ", " + gmail_successes[2] + "]\n\n" +

                "Facebook: [" + gmail_attempts[0] + ", " + gmail_attempts[1] + ", " + gmail_attempts[2] + "]\n" +
                "Pass?: [" + gmail_successes[0] + ", " + gmail_successes[1] + ", " + gmail_successes[2] + "]\n\n" +

                "Bank: [" + gmail_attempts[0] + ", " + gmail_attempts[1] + ", " + gmail_attempts[2] + "]\n" +
                "Pass?: [" + gmail_successes[0] + ", " + gmail_successes[1] + ", " + gmail_successes[2] + "]\n\n\n";


        return out;
    }


    public int hourCreator(){
        Random rand = new Random();
        int  hour = rand.nextInt(24) + 1;
        return hour;
    }

    public int minCreator(){
        Random rand = new Random();
        int  min = rand.nextInt(59);
        return min;
    }

    public int[] passCreator(){
        int[] pass = new int[8];
        for(int i = 0; i < 8; i++) {
            if(i%2 == 0) {
                pass[i] = hourCreator();
            }else{
                pass[i] = minCreator();
            }
        }
        return pass;
    }


    public String getUser_name() {
        return user_name;
    }

    public int[] getGmail_pass(){
        return gmail_pass;
    }

    public int[] getFacebook_pass() {
        return facebook_pass;
    }

    public int[] getBank_pass() {
        return bank_pass;
    }

    public int[] getGmail_attempts(){
        return gmail_attempts;
    }

    public int[] getFacebook_attempts(){
        return facebook_attempts;
    }

    public int[] getBank_attempts(){
        return bank_attempts;
    }
}
