package main.java.threshold;

import java.util.ArrayList;

public class total {
    public static ArrayList<String> c = new ArrayList<>();
    public static void main(String[]args){

    }

    public static void addc(){
        c.add("app_mn1");
        c.add("app_mn2");
        c.add("app_mnae1");
        c.add("app_mnae2");
    }
    public static void get_cons(){
        String filename = "resp/" + con_name + "_response_time.txt";
        int replicas = 0;
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader r = new BufferedReader(fr);
            String line = "";
            try {
                while ((line = r.readLine()) != null) {
                    replicas = (int) Double.parseDouble(line);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
