package ru.itgirlsdiff1.app._main;

import ru.itgirlsdiff1.app.controller.CatalogController;
import ru.itgirlsdiff1.app.controller.ProductController;
import ru.itgirlsdiff1.app.controller.UserController;
import ru.itgirlsdiff1.app.model.entity.Catalog;
import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.service.CatalogService;
import ru.itgirlsdiff1.app.model.service.ProductService;
import ru.itgirlsdiff1.app.model.service.UserService;
import ru.itgirlsdiff1.app.model.storage.StorageManager;
import ru.itgirlsdiff1.app.model.storage.util.HashMapFromArrayListUsers;
import ru.itgirlsdiff1.app.model.storage.util.ReadFromFile;
import ru.itgirlsdiff1.app.view.View;
import ru.itgirlsdiff1.app.model.storage.util.WriteToFile;

import java.util.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        StorageManager storageManager = new StorageManager();
        UserService userService = new UserService(storageManager);
        View view = new View();
        UserController userController = new UserController(userService, view);
        CatalogService catalogService = new CatalogService(storageManager);
        CatalogController catalogController = new CatalogController(catalogService, view);
        ProductService productService = new ProductService(storageManager);
        ProductController productController = new ProductController(productService, view);
        ReadFromFile fileReader = new ReadFromFile(productController, catalogController);

        User user1 = userController.createUser("Петров Александер Владимирович", "aleks.petrov@gmail.com", "мужcкой", "02.03.1989", "admin");
        User user2 = userController.createUser("Сорокина Анна Адреевна", "anna.sorokinka@gmail.com", "женский", "04.15.1992", "vendor");
        User user3 = userController.createUser("Родионов Валентин Кириллович", "val.rodionov@gmail.com", "мужской", "09.12.1981", "client");
        User user4 = userController.createUser("Федорова Мария Александровна", "masha.20@gmail.com", "женский", "03.03.2000", "client");
        User user5 = userController.createUser("Киселев Василий Георгиевич", "vasiliy.kisilev@gmail.com", "мужской", "05.19.1985", "client");

        Catalog catalog1 = catalogController.createCatalog("Детская одежда");
        Catalog catalog2 = catalogController.createCatalog("Женская одежда");
        Catalog catalog3 = catalogController.createCatalog("Мужская одежда");
        Catalog catalog4 = catalogController.createCatalog("Обувь");
        Catalog catalog5 = catalogController.createCatalog("Аксессуары");

        Product product1 = productController.createProduct("Платье для девочки", 2000, 7);
        Product product2 = productController.createProduct("Штанишки для мальчика", 1500, 12);
        Product product3 = productController.createProduct("Платье детское демисезонное для девочки", 3500, 5);
        Product product4 = productController.createProduct("Футболка для мальчика", 800, 10);
        Product product5 = productController.createProduct("Куртка детская утепленная для мальчика", 4500, 3);
        Product product6 = productController.createProduct("Платье женское вечернее", 7000, 4);
        Product product7 = productController.createProduct("Платье женское коктейльное", 5000, 6);
        Product product8 = productController.createProduct("Платье женское офисное", 3500, 8);
        Product product9 = productController.createProduct("Блузка женская", 2500, 10);
        Product product10 = productController.createProduct("Юбка женская", 3000, 7);
        Product product11 = productController.createProduct("Футболка мужская", 1200, 15);
        Product product12 = productController.createProduct("Рубашка мужская", 3500, 8);
        Product product13 = productController.createProduct("Джинсы мужские", 4000, 5);
        Product product14 = productController.createProduct("Костюм мужской", 8500, 2);
        Product product15 = productController.createProduct("Куртка мужская зимняя", 7000, 4);
        Product product16 = productController.createProduct("Туфли женские", 4500, 9);
        Product product17 = productController.createProduct("Кроссовки мужские", 3500, 12);
        Product product18 = productController.createProduct("Ботинки женские осенние", 6000, 5);
        Product product19 = productController.createProduct("Сапоги женские зимние", 9000, 3);
        Product product20 = productController.createProduct("Кеды мужские", 2500, 15);
        Product product21 = productController.createProduct("Ремень мужской", 1000, 20);
        Product product22 = productController.createProduct("Шапка женская", 1500, 10);
        Product product23 = productController.createProduct("Шарф женский", 1200, 15);
        Product product24 = productController.createProduct("Перчатки женские", 1500, 8);
        Product product25 = productController.createProduct("Бижутерия", 500, 30);

        userController.addUser(user1,user1);
        userController.addUser(user1,user2);
        userController.addUser(user1,user3);
        userController.addUser(user1,user4);
        userController.addUser(user1,user5);

        userController.getUsers();

        catalogController.addCatalog(user1, catalog1);
        catalogController.addCatalog(user1, catalog2);
        catalogController.addCatalog(user1, catalog3);
        catalogController.addCatalog(user1, catalog4);
        catalogController.addCatalog(user1, catalog5);

        catalogController.addProductToCatalog(product1, user2, 1);
        catalogController.addProductToCatalog(product2, user2, 1);
        catalogController.addProductToCatalog(product3, user2, 1);
        catalogController.addProductToCatalog(product4, user2, 1);
        catalogController.addProductToCatalog(product5, user2, 1);
        catalogController.addProductToCatalog(product6, user2, 2);
        catalogController.addProductToCatalog(product7, user2, 2);
        catalogController.addProductToCatalog(product8, user2, 2);
        catalogController.addProductToCatalog(product9, user2, 2);
        catalogController.addProductToCatalog(product10, user2, 2);
        catalogController.addProductToCatalog(product11, user2, 3);
        catalogController.addProductToCatalog(product12, user2, 3);
        catalogController.addProductToCatalog(product13, user2, 3);
        catalogController.addProductToCatalog(product14, user2, 3);
        catalogController.addProductToCatalog(product15, user2, 3);
        catalogController.addProductToCatalog(product16, user1, 4);
        catalogController.addProductToCatalog(product17, user1, 4);
        catalogController.addProductToCatalog(product18, user1, 4);
        catalogController.addProductToCatalog(product19, user1, 4);
        catalogController.addProductToCatalog(product20, user1, 4);
        catalogController.addProductToCatalog(product21, user1, 5);
        catalogController.addProductToCatalog(product22, user1, 5);
        catalogController.addProductToCatalog(product23, user1, 5);
        catalogController.addProductToCatalog(product24, user1, 5);
        catalogController.addProductToCatalog(product25, user1, 5);


        catalogController.printCatalogs();

        productController.deleteProduct(user2, 2);
        productController.updateProduct(user2, "Мужская одежда","Джинсы мужские", "Джинсы мужские",4000, 3000,5,7 );


        catalogController.printCatalogs();

        addProdFromFile(fileReader, user1);
        view.printProductList(productController.readProductListInCatalog("Детская одежда"));

        Random random = new Random();
        for (User user : storageManager.getUserList()) {
            int numProductsToAdd = random.nextInt(3) + 1;
            for (int i = 0; i < numProductsToAdd; i++) {
                Catalog randomCatalog = storageManager.getCatalogList().get(random.nextInt(storageManager.getCatalogList().size()));
                List<Product> productsInCatalog = randomCatalog.getProductsList();
                Product randomProduct = productsInCatalog.get(random.nextInt(productsInCatalog.size()));
                userController.addProductToBasket(user, randomCatalog.getCatalogName(), randomProduct.getProductName(), numProductsToAdd);
            }
        }

        for (User user : storageManager.getUserList()){
            userController.showProductsInBasket(user);
        }

        writeOutHashmap(storageManager);
    }

    public static void addProdFromFile(ReadFromFile fileReader, User user) {
        String fileName = "src/resources/Детская одежда.txt";
        fileReader.addProd(fileName, user, 1);
    }

    public static void writeOutHashmap (StorageManager storageManager) {
        HashMapFromArrayListUsers hashmapConvert = new HashMapFromArrayListUsers();
        LinkedHashMap <Integer, User> usersHashmap = new LinkedHashMap<>();
        usersHashmap = hashmapConvert.convertArrayListToHashMap(storageManager, usersHashmap);
        WriteToFile.writeOut(usersHashmap);
    }
}
