package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.ChosenPaymentMethod;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Customer;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Order;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.PizzaOrder;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOrderResource {

    private ModelMapper modelMapper;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderStatusRepository orderStatusRepository;
    private AddressRepository addressRepository;
    private ChosenPaymentMethodRepository chosenPaymentMethodRepository;
    private PizzaOrderRepository pizzaOrderRepository;
    private PizzaRepository pizzaRepository;

    @Autowired
    public CustomerOrderResource(ModelMapper modelMapper,
                                 OrderRepository orderRepository,
                                 CustomerRepository customerRepository,
                                 OrderStatusRepository orderStatusRepository,
                                 AddressRepository addressRepository,
                                 ChosenPaymentMethodRepository chosenPaymentMethodRepository,
                                 PizzaOrderRepository pizzaOrderRepository,
                                 PizzaRepository pizzaRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressRepository = addressRepository;
        this.chosenPaymentMethodRepository = chosenPaymentMethodRepository;
        this.pizzaOrderRepository = pizzaOrderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public List<OrderDTO> getCustomerOrders(long customerId) {
        return orderRepository.findAllByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getCustomerOrder(long customerId, long orderId) {
        return convertToDTO(orderRepository.findByCustomerId(customerId, orderId));
    }

    public OrderDTO createOrder(long customerId, OrderDTO orderDTO) throws EntityNotFoundException {
        Order toSave = new Order();
        toSave.setOrderStatus(orderStatusRepository.findById(orderDTO.getOrderStatus().getId()).orElseThrow(EntityNotFoundException::new));
        toSave.setLastStatusUpdateTime(Time.valueOf(LocalTime.now()));
        toSave.setAddress(addressRepository.findById(orderDTO.getAddress().getId()).orElseThrow(EntityNotFoundException::new));
        toSave.setChosenPaymentMethod(chosenPaymentMethodRepository.findById(orderDTO.getChosenPaymentMethod().getId()).orElseThrow(EntityNotFoundException::new));
        toSave.setCustomer((Customer) customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new));
        toSave.setCommentary(orderDTO.getCommentary());
        toSave.setOrderDate(new Date(System.currentTimeMillis()));
        toSave.setOrderTime(Time.valueOf(LocalTime.now()));

        toSave = orderRepository.save(toSave);

        List<PizzaOrder> pizzaOrderList = convertToEntity(orderDTO).getProductOrderList();
        for (PizzaOrder pizzaOrder : pizzaOrderList) {
            pizzaOrder.setOrder(toSave);
            pizzaOrder.setProduct(pizzaRepository.findById(pizzaOrder.getProduct().getId()).orElseThrow(EntityNotFoundException::new));
        }
        pizzaOrderRepository.saveAll(pizzaOrderList);

        return convertToDTO(toSave);
    }

    private OrderDTO convertToDTO(Order entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }

    private Order convertToEntity(OrderDTO dto) {
        return modelMapper.map(dto, Order.class);
    }

}
