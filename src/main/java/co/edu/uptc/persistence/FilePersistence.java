package co.edu.uptc.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import co.edu.uptc.model.Entity;

public class FilePersistence<T> implements Persistence<T> {
    private Type classType;
    private File file;
    private Gson gson;
    private PrintWriter pw;
    private static final String filePath = "src/main/java/co/edu/uptc/files/";
    private static final String fileExtension = ".json";
    private String fileName;

    public FilePersistence(Type classType, String fileName) {
        this.classType = classType;
        this.fileName = fileName;
    }

    public boolean createFile() {
        file = new File(this.fileName);
        try {
            if (isFileExist()) {
                return true;
            }
            pw = new PrintWriter(new FileWriter(filePath + file + fileExtension));
            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean isFileExist() {
        file = new File(filePath + this.fileName + fileExtension);
        return file.exists();
    }

    public JsonReader readFile() {
        file = new File(this.fileName);
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(filePath + file + fileExtension));
            return jsonReader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean persist(T value) {

        if (value != null) {
            ArrayList<T> result = obtainAll();
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
            gb.setPrettyPrinting();
            gson = gb.create();

            try {
                pw = new PrintWriter(new FileWriter(filePath + file + fileExtension));
                result.add(value);
                pw.println(gson.toJson(result, this.classType));
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
    public boolean persist(int index, T saveData) {
        ArrayList<T> result = obtainAll();

        if (index >= 0 && index < result.size()) {
            result.set(index, saveData);
            try {
                pw = new PrintWriter(new FileWriter(filePath + file + fileExtension));
                pw.println(gson.toJson(result, this.classType));
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
    public boolean erase(int id) {
        ArrayList<T> result = obtainAll();
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                T obj = result.get(i);
                if (obj instanceof Entity && ((Entity) obj).getId() == id) {
                    result.remove(i);
                    try {
                        pw = new PrintWriter(new FileWriter(filePath + fileName + fileExtension));
                        pw.println(gson.toJson(result));
                        pw.close();
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public T obtainById(int id) {
        ArrayList<T> result = obtainAll();
        if (result != null) {
            for (T obj : result) {
                if (obj instanceof Entity && ((Entity) obj).getId() == id) {
                    return obj;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<T> obtainAll() {
        ArrayList<T> result = new ArrayList<>();

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gson = gb.create();
        result = gson.fromJson(readFile(), this.classType);
        if (result == null) {
            return new ArrayList<>();
        }

        return result;

    }

    public void deleteFile() {
        file = new File(filePath + this.fileName + fileExtension);
        file.delete();
    }

}
