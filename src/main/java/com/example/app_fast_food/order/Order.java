package com.example.app_fast_food.order;


import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "`order`")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_discount",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private Set<Discount> discounts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_bonuses",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id")
    )
    private Set<Bonus> bonuses;

    public Order(UUID id, List<OrderItem> orderItems, OrderStatus orderStatus,
                 PaymentType paymentType, User user , Set<Discount> discounts,
                 Set<Bonus> bonuses) {
        this.id = id;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.user = user;
        this.discounts = discounts;
        this.bonuses = bonuses;
    }

}
