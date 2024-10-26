package com.example.app_fast_food.bonus;

import com.example.app_fast_food.bonus.dto.BonusCreateDTO;
import com.example.app_fast_food.bonus.dto.BonusResponseDTO;
import com.example.app_fast_food.bonus.dto.BonusUpdateDTO;
import com.example.app_fast_food.check.CheckRepository;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.order.Order;
import com.example.app_fast_food.order.OrderRepository;
import com.example.app_fast_food.order.OrderService;
import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Getter
@RequiredArgsConstructor
public class BonusService extends GenericService<Bonus , UUID , BonusResponseDTO , BonusCreateDTO , BonusUpdateDTO> {

    private final BonusRepository repository;
    private final Class<Bonus> entityClass = Bonus.class;
    private final BonusDTOMapper mapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CheckRepository checkRepository;
    private final OrderService orderService;

    @Override
    protected CommonResponse<BonusResponseDTO> internalCreate(BonusCreateDTO bonusCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<BonusResponseDTO> internalUpdate(UUID uuid, BonusUpdateDTO bonusUpdateDTO) {
        return null;
    }

    public Set<Bonus> getOrderBonuses(User user , Order order)  {
        Set<Bonus> applicableBonuses = new HashSet<>();

        for (Bonus bonus : repository.findAll()){
            if(isBonusApplicable(bonus , user , order)){
                applicableBonuses.add(bonus);
            }
        }

        return applicableBonuses;
    }

    private boolean isBonusApplicable(Bonus bonus, User user, Order order) {

        return switch (bonus.getConditionType()) {
            case HOLIDAY_BONUS, EVENT_BONUS -> true;
            case FIRST_TIME_PURCHASE -> checkRepository.getPurchasesCountOfUser(user.getId()) == 0;
            case MINIMUM_ORDER_VALUE -> orderService.checkOrderPrice(order , bonus.getConditionValue());
            case PRODUCT_BONUS -> isProductBonusApplicable(bonus, order);
            default -> false;
        };

    }

    private boolean isProductBonusApplicable(Bonus bonus, Order order) {
        Set<Product> bonusProducts = bonus.getBonusProducts();
        for(OrderItem orderItem : order.getOrderItems()){
            if(bonusProducts.contains(orderItem.getProduct())){
                return true;
            }
        }
        return false;
    }

/*    private Set<BonusResponseDTO> getResponseDTOS(Set<Bonus> bonuses) {

        Set<BonusResponseDTO> dtos = new HashSet<>();
        for (Bonus bonus : bonuses) {
            dtos.add(mapper.toResponseDTO(bonus));
        }

        return dtos;
    }*/
}
