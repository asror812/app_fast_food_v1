package com.example.app_fast_food.order;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.dto.OrderUpdateDTO;
import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.product.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderDTOMapper extends GenericMapper<Order , OrderCreateDTO, OrderResponseDTO , OrderUpdateDTO> {
    private final ModelMapper mapper;

    @Override
    public Order toEntity(OrderCreateDTO orderCreateDTO) {
        return mapper.map(orderCreateDTO, Order.class);
    }

    @Override
    public OrderResponseDTO toResponseDTO(Order order) {
        OrderResponseDTO responseDTO = mapper.map(order, OrderResponseDTO.class);

        double orderPrice = 0.;
        double discount = 0.;

        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            Discount mostPreferableDiscount = null;

            Set<Discount> activeDiscountsWithRequirements = product.getActiveDiscounts().stream()
                    .filter(p -> p.getRequiredQuantity() > 1)
                    .collect(Collectors.toSet());

            Set<Discount> activeDiscountWithNoRequirements = product
                    .getActiveDiscounts()
                    .stream().filter(p -> p.getRequiredQuantity() <= 1)
                    .collect(Collectors.toSet());

            //Discount without requirements
            for (Discount activeDiscounts : activeDiscountWithNoRequirements) {
                discount += (product.getPrice() * activeDiscounts.getPercentage() / 100) *
                        (item.getQuantity() / activeDiscounts.getRequiredQuantity());
            }

            //Discount with requirements : Find the most preferable discount
            for (Discount activeDiscount : activeDiscountsWithRequirements) {

                if (mostPreferableDiscount == null) {
                    mostPreferableDiscount = activeDiscount;
                } else {
                    if (mostPreferableDiscount.getRequiredQuantity() < activeDiscount.getRequiredQuantity()) {
                        mostPreferableDiscount = activeDiscount;
                    }
                }
            }

            //Check if there is discount with requirements available
            if (mostPreferableDiscount != null) {
                int productDiscount = item.getQuantity() / mostPreferableDiscount.getRequiredQuantity();

                discount += (productDiscount * product.getPrice() / 100) * item.getQuantity();
            }

            orderPrice += product.getPrice() * item.getQuantity();
        }

        responseDTO.setShippingCost(9000.);
        responseDTO.setOrderPrice(orderPrice - discount);
        responseDTO.setTotalPrice(orderPrice - discount + responseDTO.getShippingCost());

        return responseDTO;
    }

    @Override
    public void toEntity(OrderUpdateDTO orderUpdateDTO, Order order) {
        mapper.map(orderUpdateDTO, order);
    }
}
