package com.example.app_fast_food.discount;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.discount.dto.DiscountCreateDTO;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;
import com.example.app_fast_food.discount.dto.DiscountUpdateDTO;
import com.example.app_fast_food.order.Order;
import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class DiscountService extends GenericService<Discount , UUID  , DiscountResponseDTO, DiscountCreateDTO, DiscountUpdateDTO> {

    private final DiscountRepository repository;
    private final Class<Discount> entityClass = Discount.class;
    private final DiscountMapper mapper;

    @Override
    protected GenericMapper<Discount, DiscountCreateDTO, DiscountResponseDTO, DiscountUpdateDTO> getMapper() {
        return null;
    }

    @Override
    protected CommonResponse<DiscountResponseDTO> internalCreate(DiscountCreateDTO discountCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<DiscountResponseDTO> internalUpdate(UUID uuid, DiscountUpdateDTO discountUpdateDTO) {
        return null;
    }

    //todo create jpql
    public Set<Discount> getOrderDiscounts(Order order) {

        //Discount without requirements


        Set<Discount> discounts = repository.findAll()
                .stream().filter(r -> r.getRequiredQuantity() < 1).collect(Collectors.toSet());

        //Product discounts
        for(OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();

            discounts.addAll(product.getActiveDiscounts());
        }

        return discounts;
    }
}
