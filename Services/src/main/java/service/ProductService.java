package service;

import DAO.ProductDAO;
import entity.ProductEntity;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductService {

    private ConnectBaseService connectBaseService;
    private ProductDAO productDAO;
    private List<Product> generalProductList;

    public ProductService(ConnectBaseService connectBaseService, ProductDAO productDAO) {
        this.connectBaseService = connectBaseService;
        this.productDAO = productDAO;
    }

    public List<Product> getProducts() {
        if (Objects.isNull(generalProductList)) {
            generalProductList = new ArrayList<>();
            List<ProductEntity> entityProductsList = productDAO.getGeneralProductList();
            for (ProductEntity products : entityProductsList) {
                generalProductList.add(new Product(
                        products.getId(),
                        products.getName(),
                        products.getPrice(),
                        products.getCountry().getName()
                ));
            }
        }
        return generalProductList;
    }

    public Product getProductById(long id) {
        if (Objects.isNull(generalProductList)) getProducts();
        for (Product product : generalProductList) {
            if (product.getId() == id) {
                return product;

            }
        }
        return null;
    }

}
