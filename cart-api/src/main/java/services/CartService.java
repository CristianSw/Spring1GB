package services;

import model.Cart;
import model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope("prototype")
public class CartService {
    private ProductService productService;

    private Cart cart;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init(){
        cart = new Cart();
    }

    public void addToCartByProductId(Long productId){
        Product product = productService.findById(productId).get();
        cart.add(product);
    }

    public void removeFromCartById(Long id){
        cart.removeById(id);
    }

    public Cart getCurrentCart(){
        return cart;
    }
}
