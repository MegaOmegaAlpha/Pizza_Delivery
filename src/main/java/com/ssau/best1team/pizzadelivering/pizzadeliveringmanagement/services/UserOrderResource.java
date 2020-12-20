package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.PizzaOrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.*;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserOrderResource {

    private ModelMapper modelMapper;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderStatusRepository orderStatusRepository;
    private AddressRepository addressRepository;
    private ChosenPaymentMethodRepository chosenPaymentMethodRepository;
    private PizzaOrderRepository pizzaOrderRepository;
    private PizzaRepository pizzaRepository;
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public UserOrderResource(ModelMapper modelMapper,
                             OrderRepository orderRepository,
                             CustomerRepository customerRepository,
                             OrderStatusRepository orderStatusRepository,
                             AddressRepository addressRepository,
                             ChosenPaymentMethodRepository chosenPaymentMethodRepository,
                             PizzaOrderRepository pizzaOrderRepository,
                             PizzaRepository pizzaRepository,
                             PaymentMethodRepository paymentMethodRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressRepository = addressRepository;
        this.chosenPaymentMethodRepository = chosenPaymentMethodRepository;
        this.pizzaOrderRepository = pizzaOrderRepository;
        this.pizzaRepository = pizzaRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<OrderDTO> getUserOrders(long customerId) {
        return orderRepository.findAllByUserId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getUserOrder(long customerId, long orderId) {
        return convertToDTO(orderRepository.findByCustomerId(customerId, orderId));
    }

    @Transactional
    public OrderDTO createOrder(long userId, OrderDTO orderDTO) throws EntityNotFoundException {
        User user = customerRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        Order toSave = new Order();
        toSave.setOrderStatus(orderStatusRepository.findByName(OrderStatus.PROCESSED).orElseThrow(EntityNotFoundException::new));
        toSave.setLastStatusUpdateTime(Time.valueOf(LocalTime.now()));
        toSave.setAddress(addressRepository.findById(orderDTO.getAddress().getId()).orElseThrow(EntityNotFoundException::new));

        ChosenPaymentMethod chosenPaymentMethod = new ChosenPaymentMethod();
        chosenPaymentMethod.setCardNumber(orderDTO.getChosenPaymentMethod().getCardNumber());
        chosenPaymentMethod.setCvc(orderDTO.getChosenPaymentMethod().getCvc());
        chosenPaymentMethod.setUser(user);
        chosenPaymentMethod.setPaymentMethod(paymentMethodRepository.findById(orderDTO.getChosenPaymentMethod().getPaymentMethod().getId()).orElseThrow(EntityNotFoundException::new));

        chosenPaymentMethod = chosenPaymentMethodRepository.save(chosenPaymentMethod);

        toSave.setChosenPaymentMethod(chosenPaymentMethod);

        toSave.setUser(user);
        toSave.setCommentary(orderDTO.getCommentary());
        toSave.setOrderDate(new Date(System.currentTimeMillis()));
        toSave.setOrderTime(Time.valueOf(LocalTime.now()));

        for (PizzaOrderDTO pizzaOrderDTO : orderDTO.getPizzaOrderList()) {
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setPizza(pizzaRepository.findById(pizzaOrderDTO.getPizza().getId()).orElseThrow(EntityNotFoundException::new));
            pizzaOrder.setPizzaSize(pizzaOrderDTO.getPizzaSize());
            pizzaOrder.setAmount(pizzaOrderDTO.getAmount());
            pizzaOrder.setDoughType(pizzaOrderDTO.getDoughType());
            toSave.getPizzaOrderList().add(pizzaOrder);
        }

        toSave = orderRepository.save(toSave);

        return convertToDTO(toSave);
    }

    private OrderDTO convertToDTO(Order entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }

    private Order convertToEntity(OrderDTO dto) {
        return modelMapper.map(dto, Order.class);
    }

}
