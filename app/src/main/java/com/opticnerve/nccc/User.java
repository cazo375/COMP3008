/*
User.java is our Main data collection and storage class

It generates random starting passwords upon user creation via username.
But when using an empty constructor it is used as a user pointer
User class stores username, each type of passwords, attempts per test,
starts and ends of each test, and the success of each test.

PrintResuts used to dump data to log file.
RecordResults used to update user with new test data.

*/



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

    private static String[] gmail_starts;
    private static String[] facebook_starts;
    private static String[] bank_starts;

    private static String[] gmail_ends;
    private static String[] facebook_ends;
    private static String[] bank_ends;


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
        gmail_starts = new String[3];
        facebook_starts = new String[3];
        bank_starts = new String[3];
        gmail_ends = new String[3];
        facebook_ends = new String[3];
        bank_ends = new String[3];
        for(int i = 0; i<3; i++){
            gmail_attempts[i] = 0;
            facebook_attempts[i] = 0;
            bank_attempts[i] = 0;
            gmail_successes[i] = false;
            facebook_successes[i] = false;
            facebook_successes[i] = false;
            gmail_starts[i] = "";
            facebook_starts[i] = "";
            bank_starts[i] = "";
            gmail_ends[i] = "";
            facebook_ends[i] = "";
            bank_ends[i] = "";
        }
    }

    public void recordResults(int type, int itr, int attemps, boolean outcome, String start, String end){
        if(type == 0){
            gmail_attempts[itr] = attemps;
            gmail_successes[itr] = outcome;
            gmail_starts[itr] = start;
            gmail_ends[itr] = end;
        }
        else if(type == 1){
            facebook_attempts[itr] = attemps;
            facebook_successes[itr] = outcome;
            facebook_starts[itr] = start;
            facebook_ends[itr] = end;
        }
        else if(type == 2){
            bank_attempts[itr] = attemps;
            bank_successes[itr] = outcome;
            bank_starts[itr] = start;
            bank_ends[itr] = end;
        }
    }

    public String printResults(){
        String out = "Username: " + user_name + "\n\n"+
                "Gmail: [" + gmail_attempts[0] + ", " + gmail_attempts[1] + ", " + gmail_attempts[2] + "]\n" +
                "Pass?: [" + gmail_successes[0] + ", " + gmail_successes[1] + ", " + gmail_successes[2] + "]\n" +
                "Start: [" + gmail_starts[0] + ", " + gmail_starts[1] + ", " + gmail_starts[2] + "]\n" +
                "End:   [" + gmail_ends[0] + ", " + gmail_ends[1] + ", " + gmail_ends[2] + "]\n\n" +

                "Facebook: [" + facebook_attempts[0] + ", " + facebook_attempts[1] + ", " + facebook_attempts[2] + "]\n" +
                "Pass?: [" + facebook_successes[0] + ", " + facebook_successes[1] + ", " + facebook_successes[2] + "]\n\n" +
                "Start: [" + facebook_starts[0] + ", " + facebook_starts[1] + ", " + facebook_starts[2] + "]\n" +
                "End:   [" + facebook_ends[0] + ", " + facebook_ends[1] + ", " + facebook_ends[2] + "]\n\n" +

                "Bank: [" + bank_attempts[0] + ", " + bank_attempts[1] + ", " + bank_attempts[2] + "]\n" +
                "Pass?: [" + bank_successes[0] + ", " + bank_successes[1] + ", " + bank_successes[2] + "]\n" +
                "Start: [" + bank_starts[0] + ", " + bank_starts[1] + ", " + bank_starts[2] + "]\n" +
                "End:   [" + bank_ends[0] + ", " + bank_ends[1] + ", " + bank_ends[2] + "]\n\n\n";


        return out;
    }


    public int hourCreator(){
        Random rand = new Random();
        int  hour = rand.nextInt(23);
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

}
