package services;

import bean.Product;
import db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private static ProductService instance;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public List<Product> getListProduct() {
        return JDBIConnector.get().withHandle(handle -> {
                    return handle.createQuery("select * from `product`").mapToBean(Product.class).stream().collect(Collectors.toList());
                }
        );
    }

    public int nextId() {
        return 1 + JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT MAX(`id`) as numberProduct FROM `product`").mapTo(Integer.class).one();
        });
    }

    public void addProduct(Product product) {
        JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO product VALUES (:id, :name, :description,:price,:imageSrc, :status);")
                    .bind("productId", nextId())
                    .bind("name", product.getName())
                    .bind("description", product.getDescription())
                    .bind("price", product.getPrice())
                    .bind("imageSrc", product.getImageSrc())
                    .bind("status", product.getStatus())
                    .execute();
        });
    }
}
