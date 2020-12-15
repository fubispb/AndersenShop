package servlets;

import model.Product;
import service.BucketService;
import service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("goodlist")
@RequiredArgsConstructor
public class GoodListServlet {

    private final ProductService productService;
    private final BucketService bucketService;

    @GetMapping
    public String getList(Model model) {
        List<Product> listOfProducts = productService.getProducts();
        model.addAttribute("product", listOfProducts);
        return "goodlist";
    }

    @PostMapping
    public String index(Long id, Integer count) {
        bucketService.insertInBucketByProductId(id, count);
        return "redirect:goodlist";
    }

}
