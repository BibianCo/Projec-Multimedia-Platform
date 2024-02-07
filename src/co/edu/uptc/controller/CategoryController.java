package co.edu.uptc.controller;
import java.util.ArrayList;
import co.edu.uptc.model.Category;

public class CategoryController {

    public boolean add(Category category) {
        
        return true; 
    }
    
    public boolean delete(int id) {
        
        return true; 
    }
    

    public Category get(int id) {
        return new Category(id, null);
    }
    

    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();
        return categories; 
    }
}
