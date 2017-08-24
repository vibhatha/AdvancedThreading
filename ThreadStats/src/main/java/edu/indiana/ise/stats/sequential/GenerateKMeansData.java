package edu.indiana.ise.stats.sequential;

import edu.indiana.ise.stats.util.WriteFileSingleton;

import java.io.IOException;
import java.util.Random;

/**
 * Created by vibhatha on 8/1/17.
 */
public class GenerateKMeansData {

    public static void main(String args[]) throws InterruptedException {
        if(args.length!=3){
            System.out.println("Arguments : <file-path> <samples> <features>: ( positive integers) (file location) (positive integer) (positive integer)");
        }
        else{
            long start_time = System.currentTimeMillis();
            String filepath = args[0];
            int samples = Integer.parseInt(args[1]);
            int features = Integer.parseInt(args[2]);
            generateData(samples, features, filepath);
            long end_time = System.currentTimeMillis();
            System.out.println("Execution Time : "+(end_time-start_time)/1000.0+" s");
        }

    }

    public static void generateData(int samples, int features, String filename){

        try {
            //WriteFile writeFile = new WriteFile();
            for (int i = 0; i < samples; i++) {
                String row ="";
                for (int j = 0; j < features; j++) {
                    row += new Random().nextDouble()+" ";
                }
                WriteFileSingleton.getInstance().writeToFile(filename, row+" \n");

            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}

//Execution Time : 75.703 s => 10K,1K
