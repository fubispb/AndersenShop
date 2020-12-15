package servlets;

import model.User;
import service.BucketService;
import service.ProductService;
import service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketServlet {


    private final BucketService bucketService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping
    public String getList(Model model){
        model.addAttribute("bucket", bucketService.getBucketByUserId(1));
        return "bucket";
    }

    @PostMapping
    public String index(Long id){
        User user = userService.getUserById(1);
        bucketService.removeProductFromBucketByProductId(user.getId(), id);
        userService.deleteProductFromBucketByName(user, productService.getProductById(id));
        return "redirect:bucket";
    }

}
