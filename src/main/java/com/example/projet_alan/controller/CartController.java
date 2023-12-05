package com.example.projet_alan.controller;

import com.example.projet_alan.entity.*;
import com.example.projet_alan.repository.CartRepository;
import com.example.projet_alan.repository.OrderRepository;
import com.example.projet_alan.repository.ProductRepository;
import com.example.projet_alan.repository.UserRepository;
import com.example.projet_alan.utils.NavUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.juli.logging.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.Security;
import java.util.*;

@Controller
@RequestMapping("/panier")
public class CartController {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    public CartController(
            ProductRepository productRepository,
            UserRepository userRepository,
            CartRepository cartRepository,
            OrderRepository orderRepository
    ) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository=orderRepository;
    }


    @GetMapping(value="",name="app_cart")
    public String index(Model model){

        //@TODO

        User user = userRepository.findById(1L).get();

        Order order = orderRepository.findOrderByUserAndState(user,1);
        List<Cart> cartItems = new ArrayList<>();

        if(order != null) {
            cartItems = new ArrayList<>(order.getCarts());
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("order", order);
        NavUtils.loadNavUtils(model, user);

        return "/cart";
    }

    @PostMapping(value="/increment",name="app_panier_add_cart")
    public ResponseEntity<?> incrementQuantity() {
        Map<String, Object> response = new LinkedHashMap<>();
        String message = "";
        int status = 200;

        response.put("status", status);
        response.put("message", message);

        return ResponseEntity.ok(response);

        /*try {
            User LOGIN_USER = null; //(User) session.getAttribute("LOGIN_USER");

            if (LOGIN_USER != null) {
                Cart cart = cartRepository.findById(Long.parseLong(request.getParameter("cartId"))).get();

                boolean success = false;

                success = cart.incrementQuantity();

                if(success)
                    cartRepository.save(cart);

                message = success ? "Quantité incrémentée !" : "minimum de quantité atteinte !";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
            status = 404;
        }

        response.put("status", status);
        response.put("message", message);

        return response;*/
    }

   /* @PostMapping(value="",name="app_panier_increment_quantity")
    public String addCart(){
        return "redirect:/panier";
    }

    protected void doPost(HttpServletRequest request, Session session) throws ServletException, IOException {

        try {
            User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");

            if (LOGIN_USER != null) {

                CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());

                Product product = productRepository.findById(Long.parseLong(request.getParameter("productId"))).get();
                Order order = orderRepository.findOrderByUserAndState(LOGIN_USER, 1);
                int quantity = Integer.parseInt(request.getParameter("quantity"));


                //int order_id = dao.getActiveOrderId(user_id);

                boolean success = false;

                String message = "";
                String action = request.getParameter("action");

                Cart cart = new Cart();
                cart.setProduct(product);
                cart.setOrder(order);


                System.out.println(product.getId() +""+ LOGIN_USER.getId() +""+quantity+" "+ order.getId());
                switch (action) {
                    case "add":
                        System.out.println("add Case");
                        success = dao.addToCart(cart, user_id);
                        session.setAttribute("ORDER_ID",order_id);
                        message = success ? "Produit ajouté au panier !" : "maximum de quantité atteinte !";
                        break;
                    case "decrement":
                        success = dao.decrementQuantity(cart, user_id);
                        message = success ? "Quantité décrémentée !" : "maximum de quantité atteinte !";
                        break;
                    case "increment":
                        success = dao.incrementQuantity(cart, user_id);
                        message = success ? "Quantité incrémentée !" : "minimum de quantité atteinte !";
                        break;
                    case "delete":
                        dao.deleteCartItem(cart, order_id);
                        success = true;
                        message = "produit supprime";
                        break;
                    case "updateQuantity":
                        System.out.println("updateQuantity case");
                        dao.updateQuantityInCart(cart.getProduct_id(),order_id,quantity);
                        success = true;
                        message = "quantite modifie !";
                }

                List<Cart> cartItems = dao.getCartItems(user_id);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                String jsonResponse = String.format("{"success": %s, "message": "%s"}",success, message);
                response.getWriter().write(jsonResponse);

            } else {
                response.sendRedirect("/login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
