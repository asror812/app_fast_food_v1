package com.example.app_fast_food.role;


import com.example.app_fast_food.user.Permission;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "role")
public class Role {
    @Id
    private String name;

    @JdbcTypeCode(SqlTypes.ARRAY)
    private Set<Permission> permissions;

}
