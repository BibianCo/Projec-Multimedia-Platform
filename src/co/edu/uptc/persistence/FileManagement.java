package co.edu.uptc.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import co.edu.uptc.model.Plan;

public class FileManagement<T> implements Persistence<T> {
    private Type classType;
    private File file;
    private Gson gson;
    private PrintWriter pw;
    private BufferedReader br;
    private static final String filePath = "src\\co\\edu\\uptc\\files\\";
    private static final String fileExtension = ".json";
    private String fileName;

    public FileManagement(Type classType, String fileName) {
        this.classType = classType;
        this.fileName = fileName;
    }

    public boolean createFile() {
        file = new File(this.fileName);
        try {
            pw = new PrintWriter(new FileWriter(filePath + file + fileExtension, true));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String readFile() {
        file = new File(this.fileName);
        StringBuilder result = new StringBuilder();

        try {
            br = new BufferedReader(new FileReader(filePath + file + fileExtension));
            String line = br.readLine();
            while (line != null) {
                result.append(line);
                line = br.readLine();
            }
            br.close();
            return result.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean persist(T value) {

        if (value != null) {
            ArrayList<T> result = obtainAll();
            GsonBuilder gb = new GsonBuilder();
            gb.setPrettyPrinting();
            gb.setDateFormat("YYYY-MM-DD");
            gson = gb.create();

            try {
                pw = new PrintWriter(new FileWriter(filePath + file + fileExtension));
                result.add(value);
                pw.println(gson.toJson(result));
                pw.close();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean persist(int index, Object saveData) {
        return false;
    }

    @Override
    public boolean erase(int id) {
        return false;
    }

    @Override
    public T obtainById(int id) {
        return null;
    }

    @Override
    public ArrayList<T> obtainAll() {
        ArrayList<T> result = new ArrayList<>();
        gson = new Gson();
        result = gson.fromJson(readFile(), this.classType);

        return result;

    }

}
