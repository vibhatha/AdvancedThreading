package edu.indiana.ise.stats.util;

/**
 * Created by vibhatha on 8/1/17.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class WriteFile {

    private String fileName;
    private String data;


    public WriteFile(String fileName, String data) {
        this.fileName = fileName;
        this.data = data;
    }


    public WriteFile(){

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WriteFile{" +
                "fileName='" + fileName + '\'' +
                ", data='" + data + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WriteFile)) return false;

        WriteFile writeFile = (WriteFile) o;

        if (!getFileName().equals(writeFile.getFileName())) return false;
        return getData().equals(writeFile.getData());
    }

    @Override
    public int hashCode() {
        int result = getFileName().hashCode();
        result = 31 * result + getData().hashCode();
        return result;
    }

    public static void createFileUsingFileClass(String filepath, String data) throws IOException
    {
        File file = new File(filepath);

        //Create the file
        if (file.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }

        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.close();
    }
}
