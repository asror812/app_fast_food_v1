package com.example.app_fast_food.order;


import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;


    //@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
}
