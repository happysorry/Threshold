package happysorry.src.main.java.threshold;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import main.java.threshold.health_check;

public class Start {
    public static void main(String[]args){
        int iter = 1;
        double sim_time = 3600;
        String input_file = "input/exp_60.dat";
        ExecutorService es = Executors.newCachedThreadPool();
        restart r = new restart();
        for(int i=0;i<iter;i++){
            r.res();
            Wait(180000);
            es.execute(new get_all_use(sim_time));
            es.execute(new get_all_res(sim_time));
            es.execute(new new_send_request(input_file));
            Wait(10000);
            es.execute(new health_check(sim_time));
            es.execute(new service("app_mn1", sim_time));
            es.execute(new service("app_mn2", sim_time));
            es.execute(new service("app_mnae1", sim_time));
            es.execute(new service("app_mnae2", sim_time));
            try {
                es.awaitTermination(60, TimeUnit.SECONDS);//wait until thread terminate
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Wait((long) (sim_time * 1000));
        }
    }

    public static void Wait(long t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
