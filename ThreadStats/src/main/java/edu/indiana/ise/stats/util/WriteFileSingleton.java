package edu.indiana.ise.stats.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by vibhatha on 8/1/17.
 */

public class WriteFileSingleton {
    private static final WriteFileSingleton inst= new WriteFileSingleton();

    private WriteFileSingleton() {
        super();
    }

    public synchronized void writeToFile(String filepath, String data) throws IOException{
        // Do whatever
        File file = new File(filepath);
        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
            System.out.println("Data is being written to File : "+filepath+" ...");
        }
        FileOutputStream out=new FileOutputStream(filepath, true);
        out.write(data.getBytes());
        //Create the file
        /*if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }*/

        //Write Content
        /*FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.close();*/

    }

    public static WriteFileSingleton getInstance() {
        return inst;
    }

}