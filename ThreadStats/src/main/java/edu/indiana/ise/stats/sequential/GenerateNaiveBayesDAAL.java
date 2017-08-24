package edu.indiana.ise.stats.sequential;

import edu.indiana.ise.stats.util.WriteFileSingleton;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by vibhatha on 8/4/17.
 */
public class GenerateNaiveBayesDAAL {
    public static void main(String args[]) throws InterruptedException, IOException {
        if(args.length!=4){
            System.out.println("args "+args.length);
            System.out.println(args[0]+":"+args[1]+":"+args[2]);
            System.out.println("Arguments : <base-file-path> <samples> <features>: ( (string) positive integers = (features +1 ): {0}=Regression Value, {0-features+1}=features) (file location) (positive integer) (positive integer)");
        }
        else{
            long start_time = System.currentTimeMillis();
            System.out.println(args[0]+":"+args[1]+":"+args[2]+":"+args[3]);
            int samples = Integer.parseInt(args[1]);
            int features = Integer.parseInt(args[3]);
            //generating the directories
            String basePath = args[0];
            //generate basePath directory
            generateDirectories(basePath);
            //generate the subdirectories within the basePath
            generateDirectories(basePath+"/train");
            generateDirectories(basePath+"/test");
            generateDirectories(basePath+"/groundTruth");

            String filepath = basePath+"/train/"+"mat_"+samples+"_"+features;
            String testFilePath = basePath+"/test/"+"mat_"+samples+"_"+features;
            String testGroundTruthPath = basePath+"/groundTruth/"+"mat_"+samples+"_"+features;
            generateData(samples, features, filepath);
            generateData(samples,features,testFilePath);
            generateData(samples,1,testGroundTruthPath);
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
                    row += new Random().nextDouble()+",";
                }
                WriteFileSingleton.getInstance().writeToFile(filename, row+" \n");

            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateDirectories(String basePath) throws IOException {
        File file = new File(basePath);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }


}
