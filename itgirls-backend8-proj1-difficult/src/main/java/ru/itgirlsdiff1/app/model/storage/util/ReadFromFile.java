package ru.itgirlsdiff1.app.model.storage.util;

import ru.itgirlsdiff1.app.controller.CatalogController;
import ru.itgirlsdiff1.app.controller.ProductController;
import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;

import java.io.*;

public class ReadFromFile {
    private ProductController productController;
    private CatalogController catalogController;

    public ReadFromFile(ProductController productController, CatalogController catalogController){
        this.productController = productController;
        this.catalogController = catalogController;
    }

    public void addProd(String fileOutputName, User user, int cataligId) {
        try (BufferedReader bufReader = new BufferedReader(new FileReader(fileOutputName))) {
            int count = linesCount(fileOutputName);
            while (count != 0) {
                String line = bufReader.readLine();
                String[] lines = line.split(",");
                Product prod1 = productController.createProduct(lines[1], Double.valueOf(lines[2]), Integer.valueOf(3));
                catalogController.addProductToCatalog(prod1, user, cataligId);
                count--;
            }
        } catch (IOException e) {
            System.out.println("Файл не найден");
            throw new RuntimeException(e);
        }
    }

    private int linesCount(String fileOutputName){
        int count = 0;
        try(BufferedReader bufReader = new BufferedReader(new FileReader(fileOutputName))){
            String line;
            while ((line = bufReader.readLine()) != null) {
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Ошибка во время чтения файла");
            throw new RuntimeException(e);
        }
        return count;
    }
}