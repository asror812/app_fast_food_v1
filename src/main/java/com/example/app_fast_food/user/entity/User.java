package com.example.app_fast_food.user.entity;


import com.example.app_fast_food.check.entity.Check;
import com.example.app_fast_food.comment.Comment;
import com.example.app_fast_food.filial.entity.Region;
import com.example.app_fast_food.order.Order;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.user.permission.Permission;
import com.example.app_fast_food.user.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "`user`")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true , name = "phone_number")
    @NotNull
    @Pattern(regexp = "^\\+[0-9]{2} [0-9]{3}-[0-9]{2}-[0-9]{2}$")
    private String phoneNumber;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String password;

    private Region region;

    @OneToMany(mappedBy = "user")
    private List<Comment> comment;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Check> check;


    @ManyToMany
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    public Set<Product> favoriteProducts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Stream<Permission> role_permissions = roles
                .stream().map(Role::getPermissions)
                .flatMap(Collection::stream);


        return role_permissions
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
    }



    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
