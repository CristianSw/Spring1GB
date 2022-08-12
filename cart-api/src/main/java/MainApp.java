import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CartService;
import services.ProductService;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CartService cartService = context.getBean(CartService.class);
        ProductService productService = context.getBean(ProductService.class);

        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean isTrue = true;

        System.out.println("Hello :");
        System.out.println("Your cart now :" + cartService.getCurrentCart());
        System.out.println("Cart elements are :" + productService.findAll());
        System.out.println("Enter your action!");
        while(isTrue){
            System.out.println("1 - create new cart | 2 - add product to cart by id | 3 - remove product from cart by id | -1 to exit");
            value = scanner.nextInt();
            if (value == 1){
                cartService = context.getBean(CartService.class);
                System.out.println("Your cart now : " + cartService.getCurrentCart());
            } else if (value == 2){
                System.out.println("Enter product ID's to add them to cart or enter -1 to finish adding. ");
                while (true) {
                    Long id = scanner.nextLong();
                    if (!id.equals(-1L)){
                        cartService.addToCartByProductId(id);
                        System.out.println("Your cart now : " + cartService.getCurrentCart());
                    }else break;
                }
            }else if (value == 3) {
                System.out.println("Enter product ID's to remove them or enter -1 to finish removing.");
                while (true) {
                    Long id = scanner.nextLong();
                    if (!id.equals(-1L)){
                        cartService.removeFromCartById(id);
                        System.out.println("Your cart now : " + cartService.getCurrentCart());
                    }else break;
                }
            } else if (value == -1) {
                isTrue = false;
            }
        }
        context.close();
    }
}
