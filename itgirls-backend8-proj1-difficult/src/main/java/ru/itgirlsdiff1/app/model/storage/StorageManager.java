package ru.itgirlsdiff1.app.model.storage;

import ru.itgirlsdiff1.app.model.entity.Catalog;
import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.storage.util.Search;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class StorageManager {
    private Storage storage;
    private final Search search = new Search();

    public StorageManager() {
        this.storage = Storage.getInstance();
    }

    public User getUser(long userID) {
        Optional<User> optionalUser = getUserList().stream()
                .filter(user -> user.getUserId() == userID)
                .findFirst();
        return optionalUser.orElse(null);
    }

    public void addUser(User user) {
        if (!checkUser(user)) {
            storage.getUserList().add(user);
            System.out.println("User " + user.getFullName() + " was added");
            //для проверки добавления, потом уберем
//            System.out.println(storage.getUserList());
        } else {
            System.out.println("User with such id  exists ");
        }
    }

    public ArrayList<User> getUserList() {
        return storage.getUserList();
    }

    public ArrayList<User> readAllUsers() {
        return storage.getUserList();
    }

    public User readUser(Long userId) {
        ArrayList<User> users = storage.getUserList().stream()
                .sorted(Comparator.comparing(User::getUserId))
                .collect(Collectors.toCollection(ArrayList::new));

        BiFunction<Long, User, Integer> comparator = (id, user) -> id.compareTo(user.getUserId());
        int resultIndex = search.searchBinary(users, 0, users.size() - 1, userId, comparator);
        if (resultIndex >= 0) {
            return users.get(resultIndex);
        } else {
            System.out.println("No user found");
            return null;
        }
    }

    public User readUser(String userName) {
        ArrayList<User> users = storage.getUserList().stream()
                .sorted(Comparator.comparing(User::getFullName))
                .collect(Collectors.toCollection(ArrayList::new));

        BiFunction<String, User, Integer> comparator = (name, user) -> name.compareTo(user.getFullName());
        int resultIndex = search.searchBinary(users, 0, users.size() - 1, userName, comparator);
        if (resultIndex >= 0) {
            return users.get(resultIndex);
        } else {
            System.out.println("No user found");
            return null;
        }
    }

    public boolean checkUser(User user) {
        if (storage.getUserList().stream().noneMatch(userStream -> Objects.equals(userStream.getUserId(), user.getUserId()))) {
            return false;
        } else {
            return true;
        }
    }

    public void updateUser(long userID, String newName, String newEmail, String newBirthday,
                           String newGender, String newRole) {
        getUser(userID).setFullName(newName);
        getUser(userID).setEmail(newEmail);
        getUser(userID).setBirthday(newBirthday);
        getUser(userID).setGender(newGender);
        getUser(userID).setRole(newRole);
    }


    public void deleteUserById(Long id) {
        Iterator<User> userIterator = storage.getUserList().iterator();
        while (userIterator.hasNext()) {
            User nextUser = userIterator.next();
            if (nextUser.getUserId() == id) {
                userIterator.remove();
                System.out.println("User with id " + id + "has been deleted");
                //для проверки, потом удалить
                System.out.println(storage.getUserList());
                return;
            }
        }
        System.out.println("There is no user with this id");
    }


    public ArrayList<Catalog> getCatalogList() {
        return storage.getCatalogList();
    }

    public Catalog getCatalog(long catalogID) {
        Optional<Catalog> optionalCatalog = getCatalogList().stream()
                .filter(catalog -> catalog.getCatalogID() == catalogID)
                .findFirst();
        return optionalCatalog.orElse(null);
    }

    public void addCatalog(Catalog catalog) {
        storage.getCatalogList().add(catalog);
        System.out.println("Catalog " + catalog.getCatalogName() + " was added.");
    }


    public void deleteCatalog(long id) {
        Iterator<Catalog> catalogIterator = storage.getCatalogList().iterator();
        while (catalogIterator.hasNext()) {
            Catalog catalogNext = catalogIterator.next();
            if (catalogNext.getCatalogID() == id) {
                catalogIterator.remove();
                System.out.println("Catalog " + catalogNext.getCatalogName() + " with id " + id + " has been deleted");
                //для проверки, потом убрать
                System.out.println(storage.getCatalogList());
                return;
            }
        }
        System.out.println("Catalog with this id does not exist");
    }

    public void updateCatalogName(String oldCatalogName, String newCatalogName) {
        storage.getCatalogList().stream()
                .filter(catalog -> catalog.getCatalogName().equalsIgnoreCase(oldCatalogName))
                .forEach(catalog -> catalog.setCatalogName(newCatalogName));
    }

    public Catalog readCatalog(long catalogID) {
        Optional<Catalog> optionalCatalog = getCatalogList().stream()
                .filter(catalog -> catalog.getCatalogID() == catalogID)
                .findFirst();
        return optionalCatalog.orElse(null);
    }

    public void createProduct(String productName, double price, int quantityInStock, long catalogID) {
        Catalog catalog = getCatalog(catalogID);
        if (catalog != null && catalog.getProductsList() != null) {
            getCatalog(catalogID).getProductsList().add(new Product(productName, price, quantityInStock));
            System.out.println("Product has been successfully added!");
        } else {
            System.out.println("Error: Catalog or products list is null.");
        }
    }


    public ArrayList<Product> readAllProducts() {
        return storage.getCatalogList().stream()
                .flatMap(catalog -> catalog.getProductsList().stream())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Product readProduct(String productName) {
        ArrayList<Product> products = storage.getCatalogList().stream()
                .flatMap(catalog -> catalog.getProductsList().stream())
                .sorted(Comparator.comparing(Product::getProductName))
                .collect(Collectors.toCollection(ArrayList::new));

        BiFunction<String, Product, Integer> comparator = (name, product) -> name.compareTo(product.getProductName());
        int resultIndex = search.searchBinary(products, 0, products.size() - 1, productName, comparator);
        if (resultIndex >= 0) {
            return products.get(resultIndex);
        } else {
            System.out.println("No product found");
            return null;
        }
    }

    public ArrayList<Product> readProductListInCatalog(String catalogName) {
        ArrayList<Catalog> catalogs = storage.getCatalogList().stream()
                .sorted(Comparator.comparing(Catalog::getCatalogName))
                .collect(Collectors.toCollection(ArrayList::new));

        BiFunction<String, Catalog, Integer> comparator = (name, catalog) -> name.compareTo(catalog.getCatalogName());
        int resultIndex = search.searchBinary(catalogs, 0, catalogs.size() - 1, catalogName, comparator);
        if (resultIndex >= 0) {
            return catalogs.get(resultIndex).getProductsList();
        } else {
            System.out.println("No products found in catalog " + catalogName);
            return null;
        }
    }

    public void updateProduct(String catalogName, String oldProductName, String newProductName, double oldPrice, double newPrice, int oldQuantity, int newQuantity) {
        storage.getCatalogList().stream()
                .filter(catalog -> catalog.getCatalogName().equalsIgnoreCase(catalogName))
                .flatMap(catalog -> catalog.getProductsList().stream())
                .filter(product -> product.getProductName().equalsIgnoreCase(oldProductName))
                .peek(product -> product.setProductName(newProductName))
                .peek(product -> product.setPrice(newPrice))
                .forEach(product -> product.setQuantityInStock(newQuantity));
    }

    public void addProductToBasket(String catalogName, String productName, int quantity) {
        Catalog catalog = getCatalogByName(catalogName);
        if (catalog != null) {
            Product product = getProductByName(productName);
            if(product!=null && product.getQuantity() >= quantity) {
                product.setQuantityInStock(product.getQuantity() - quantity);
            }
        } else {
            System.out.println("Catalog does not exist.");
        }
    }

    public void addProductToCatalog(Product product, long catalogID) {
        Catalog catalog = getCatalog(catalogID);
        if (catalog != null) {
            getCatalog(catalogID).getProductsList().add(product);
        } else {
            System.out.println("Error: Catalog with ID " + catalogID + " not found.");
        }
    }

    public Product getProductFromCatalog(String catalogName, String productName) {
        Catalog catalog = getCatalogByName(catalogName);
        if (catalog != null) {
            return catalog.getProductsList().stream()
                    .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                    .findFirst()
                    .orElse(null);
        } else {
            System.out.println("Catalog was not found.");
        }
        return null;
    }

    private Catalog getCatalogByName(String catalogName) {
        return getCatalogList().stream()
                .filter(catalog -> catalog.getCatalogName().equalsIgnoreCase(catalogName))
                .findFirst()
                .orElse(null);
    }

    public Product getProductByName(String productName) {
        return getCatalogList().stream()
                .flatMap(catalog -> catalog.getProductsList().stream())
                .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public List<Product> filterProdAscByPrice(String catalogName, double minPrice, double maxPrice){
        return storage.getCatalogList().stream()
                .filter(catalog -> catalog.getCatalogName().equalsIgnoreCase(catalogName))
                .flatMap(catalog -> catalog.getProductsList().stream())
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .sorted(Comparator.comparing(Product::getPrice)).toList();
    }

    public List<Product> filterProdDescByName(String catalogName, double minPrice, double maxPrice){
        return storage.getCatalogList().stream()
                .filter(catalog -> catalog.getCatalogName().equalsIgnoreCase(catalogName))
                .flatMap(catalog -> catalog.getProductsList().stream())
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .sorted(Comparator.comparing(Product::getPrice).reversed()).toList();
    }
}
