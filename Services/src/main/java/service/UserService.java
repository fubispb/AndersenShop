package service;

import DAO.UserDAO;
import entity.UserEntity;
import model.Product;
import model.User;

public class UserService {

    private UserDAO userDAO;
    private ConnectBaseService connectBaseService;
    private BucketService bucketService;

    public UserService(ConnectBaseService connectBaseService, BucketService bucketService, UserDAO userDAO) {
        this.connectBaseService = connectBaseService;
        this.bucketService = bucketService;
        this.userDAO = userDAO;
    }

    public User getUserById(long id) {
        UserEntity userEntity = userDAO.getUserById(id);
        return new User(id, userEntity.getName());
    }

    public void deleteProductFromBucketByName(User user, Product product) {
        bucketService.removeProductFromBucketByProductId(user.getId(), product.getId());
    }

}
