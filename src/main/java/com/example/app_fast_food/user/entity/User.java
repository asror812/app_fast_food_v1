package com.example.app_fast_food.user.entity;


import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.check.entity.Check;
import com.example.app_fast_food.comment.Comment;
import com.example.app_fast_food.filial.entity.Region;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.role.Role;
import com.example.app_fast_food.user.Permission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;
import java.util.stream.Collectors;

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
    @NotNull @NotBlank
    private String phoneNumber;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String password;

    private Region region;

    @ManyToOne
    @JoinColumn(name = "catory_id")
    private Category category;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissions = new ArrayList<>();
        for (Role role : roles) {
            permissions.addAll(role.getPermissions());
        }

        return permissions
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.name()))
                .collect(Collectors.toSet());
    }

    @ManyToMany
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    public Set<Product> favoriteProducts;

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
