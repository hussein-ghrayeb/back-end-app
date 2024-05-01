package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Order;
import com.solution.grapeApp.entities.OrderProduct;
import com.solution.grapeApp.entities.requests.OrderDTO;
import com.solution.grapeApp.repositories.OrderProductRepository;
import com.solution.grapeApp.repositories.ProductRepository;
import com.solution.grapeApp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/getOrderById")
    public ResponseEntity<Order> getOrdersById(@RequestParam String id) {
        Optional<Order> optionalOrder = orderService.getOrderById(id);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getOrdersByCustomerId")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@RequestParam String customerId) {
        return ResponseEntity.ok(orderService.findOrdersByCustomerId(customerId));
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        try {
            Order savedOrder = orderService.saveOrder(new Order(order.getTotalPrice(), order.getDetails(),
                    order.getCode(), order.getDeliveryInstruction(), order.getCustomer(), order.getAddress()));
            if (savedOrder.getId() != null) {
                order.getProducts().forEach(product -> {
                    orderProductRepository.save(new OrderProduct(product.getProductCount(), product.getProductName(),
                            product.getProductImageUrl(), product.getProductPrice(), savedOrder));
                    productRepository.updateProductStock(product.getProductCount(), product.getId());
                });
            }
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrder(@RequestParam String id) {
        try {
            if (orderService.isOrderExists(id)) {
                orderService.deleteOrderById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
