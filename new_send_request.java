package happysorry.src.main.java.threshold;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class new_send_request implements Runnable{
    public static ArrayList<Long> stop = new ArrayList<>();
    public static ArrayList<String> stage = new ArrayList<>();
    public static String filename = "";
    public static String output_filename = "";
    public static double sim_time = 0;
    
    public new_send_request(String filename){
        this.filename = filename;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        read();
        add_stage();
        send();
    }
    
    public static void main(String[]args){
        read();
        add_stage();
        send();
        // System.out.println(stop.size());
    }

    public static void read(){
        // filename = "input/exp20.dat";
        System.out.println("filename = " + filename);
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader r = new BufferedReader(fr);
            String line = "";
            
            try {
                while ((line = r.readLine()) != null) {
                    stop.add(Long.parseLong(line));
                }
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            
        }
    }

    public static void send(){
        double st = System.nanoTime();
        ExecutorService es = Executors.newFixedThreadPool(40);
        System.out.println(stop.size());
        for(int i=0;i<stop.size();i++){
            long t = stop.get(i);
            int ind = i % stage.size();
            String s = stage.get(ind);
            es.execute(new send_req(s));
            Wait(t);
        }
        
        System.out.println("send over");
        Thread.currentThread().interrupt();
    }

    public static void add_stage() {
        stage.add("RFID_Container_for_stage0");
        stage.add("RFID_Container_for_stage1");
        stage.add("Liquid_Level_Container");
        stage.add("RFID_Container_for_stage2");
        stage.add("Color_Container");
        stage.add("RFID_Container_for_stage3");
        stage.add("Contrast_Data_Container");
        stage.add("RFID_Container_for_stage4");
    }
    public static void Wait(long time) {

        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }
}
