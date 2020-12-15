package service;

import DAO.BucketDAO;
import entity.BucketEntity;
import model.Product;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BucketService {

    private BucketDAO bucketDAO;
    private ConnectBaseService connectBaseService;
    private ProductService productService;


    public BucketService(ConnectBaseService connectBaseService, ProductService productService, BucketDAO bucketDAO) {
        this.connectBaseService = connectBaseService;
        this.productService = productService;
        this.bucketDAO = bucketDAO;
    }

    public Map<Product, Integer> getBucketByUserId(long id) {
        List<BucketEntity> list = bucketDAO.getUserBucketEntityById(id);
        Map<Product, Integer> bucket = new TreeMap<>();
        for (BucketEntity o : list) {
            bucket.put(productService.getProductById(o.getProducts_id()), o.getCount());
        }
        return bucket;
    }

    public void insertInBucketByProductId(long id, int count) {
        bucketDAO.insertInUserBucket(id, count);
    }

    public void removeProductFromBucketByProductId(long idUser, long idProduct) {
        bucketDAO.deleteFromUserBucket(idUser, idProduct);
    }
}
