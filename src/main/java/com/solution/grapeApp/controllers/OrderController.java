package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Category;
import com.solution.grapeApp.entities.Order;
import com.solution.grapeApp.entities.OrderProduct;
import com.solution.grapeApp.entities.requests.OrderDTO;
import com.solution.grapeApp.repositories.EmployeeRepository;
import com.solution.grapeApp.repositories.OrderProductRepository;
import com.solution.grapeApp.repositories.OrderRepository;
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
    OrderRepository orderRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/getFirstCreatedOrder")
    public ResponseEntity<List<Order>> getFirstCreatedOrder() {
        return ResponseEntity.ok(orderService.getCreatedOrders());
    }

    @PutMapping("/updateCheckerOrder")
    public ResponseEntity<Order> updateCheckerOrder(@RequestBody Order order) {
        try {
            Optional<Order> optionalOrder = orderService.getOrderById(order.getId());

            if (optionalOrder.isPresent()) {
                employeeRepository.setEmployeeAvailability(order.getCheckedBy().getId(), false);
                orderRepository.setOrderAsUnderPackaging(order.getId(), order.getCheckedBy().getId());
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateOrderStatus")
    public ResponseEntity<Order> updateOrderStatus(@RequestBody Order order) {
        try {
            Optional<Order> optionalOrder = orderService.getOrderById(order.getId());

            if (optionalOrder.isPresent()) {
                employeeRepository.setEmployeeAvailability(order.getCheckedBy().getId(), true);
                orderRepository.setOrderstatus(order.getId(), order.getStatus().name());
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateDeliveryOrder")
    public ResponseEntity<Order> updateDeliveryOrder(@RequestBody Order order) {
        try {
            Optional<Order> optionalOrder = orderService.getOrderById(order.getId());

            if (optionalOrder.isPresent()) {
                employeeRepository.setEmployeeAvailability(order.getDeliveredBy().getId(), false);
                orderRepository.setOrderAsPickuped(order.getId(),
                        order.getDeliveredBy().getId());
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getFirstPackedOrder")
    public ResponseEntity<List<Order>> getFirstPackedOrder() {
        return ResponseEntity.ok(orderService.getPackedOrders());
    }

    @GetMapping("/getOrderById")
    public ResponseEntity<Order> getOrdersById(@RequestParam String id) {
        Optional<Order> optionalOrder = orderService.getOrderById(id);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getOrderByChecker")
    public ResponseEntity<Order> getOrderByChecker(@RequestParam String employeeId) {
        Optional<Order> optionalOrder = orderRepository.findOrderUnderCheckingBy(employeeId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getOrderByDelivery")
    public ResponseEntity<Order> getOrderByDelivery(@RequestParam String employeeId) {
        Optional<Order> optionalOrder = orderRepository.findOrderPickupedBy(employeeId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.noContent().build();
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
                            product.getProductImageUrl(), product.getProductPrice(), product.getShelfNumber(),
                            savedOrder, product.getBarCode()));
                    productRepository.updateProductStock(product.getProductCount(), product.getId());
                });
            }
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cancelOrder")
    public ResponseEntity<Void> cancelOrder(@RequestParam Order order) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(order.getId());

            if (optionalOrder.isPresent()) {
                orderService.deleteOrderById(order.getId(), optionalOrder.get().getStatus().name());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
