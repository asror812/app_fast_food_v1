package com.example.app_fast_food;

import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.category.CategoryRepository;
import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.common.exceptions.RestException;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.discount.DiscountRepository;
import com.example.app_fast_food.filial.FilialRepository;
import com.example.app_fast_food.filial.entity.Filial;
import com.example.app_fast_food.filial.entity.Region;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.ProductRepository;
import com.example.app_fast_food.user.entity.Address;
import com.example.app_fast_food.user.permission.Permission;
import com.example.app_fast_food.user.permission.PermissionRepository;
import com.example.app_fast_food.user.role.Role;
import com.example.app_fast_food.user.role.RoleRepository;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Component
@RequiredArgsConstructor
public class DatabaseInitialDataAdd implements CommandLineRunner {

    final String IMAGES_FOLDER_PATH = "D:\\Java projects\\fast_food" ;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;
    private final FilialRepository filialRepository;

    @Override
    public void run(String... args) throws Exception {

        createPermissions();
        createRole();
        createAdmin();
        createDiscount();
        createProduct();
        createFilial();

        //todo
        //create 5 checks

        //create internalization
        //create real attachments
        //


    }

    private void createFilial() {

        if(categoryRepository.findAll().isEmpty()){
            Filial filial1 = new Filial(null , "Babur park filial" , "babur park" ,
                    LocalTime.of(9  , 0) , LocalTime.of(0, 0 ),
                    72.3422 , 40.7828 , Region.ANDIJON);

            Filial filial2 = new Filial(null , "Registan Square filial" , "registan square" ,
                    LocalTime.of(9  , 0) , LocalTime.of(0, 0 ),
                    66.9749 , 39.6542 , Region.SAMARQAND);

            Filial filial3 = new Filial(null , "Chorsu Bazaar filial" , "chorsu bazaar" ,
                    LocalTime.of(9 , 0) , LocalTime.of(0 , 0) ,
                    69.2435, 41.3270 , Region.TASHKENT);

            Filial filial4 = new Filial(null , "Tashkent Tower filial" , "tashkent tower" ,
                    LocalTime.of(9 , 0) , LocalTime.of(0 , 0) ,
                    9.3341 , 41.3385 , Region.TASHKENT);

            List<Filial> filials = Arrays.asList(filial1 , filial2 , filial3  ,filial4);


            String text = "Hello  world !" ;
            text.length();
            filialRepository.saveAll(filials);
        }
    }


    private void createProduct() {

        if (productRepository.findAll().isEmpty()) {

            Category lavashCategory = new Category("Lavash", null, Collections.emptyList());
            Category hamburgerCategory = new Category("Hamburger", null, Collections.emptyList());
            Category donersCategory = new Category("Doners", null, Collections.emptyList());
            Category pizzaCategory = new Category("Pizza", null, Collections.emptyList());
            Category drinkCategory = new Category("Drinks", null, Collections.emptyList());
            Category sauceCategory = new Category("Sauce", null, Collections.emptyList());
            Category dessertCategory = new Category("Dessert", null, Collections.emptyList());

            List<Category> categories = Arrays.asList(lavashCategory, hamburgerCategory, donersCategory,
                    pizzaCategory, drinkCategory, sauceCategory, dessertCategory);
            categoryRepository.saveAll(categories);


            Discount extra = discountRepository.findDiscountByName("Extra")
                    .orElseThrow(() -> new RestException("Discount not found"));

            Discount extra2 = discountRepository.findDiscountByName("Extra-2")
                    .orElseThrow(() -> new RestException("Discount not found"));

            Discount navruz = discountRepository.findDiscountByName("Navruz")
                    .orElseThrow(() -> new RestException("Discount not found"));


            UUID lavashId = UUID.randomUUID();
            Attachment lavashMiniAttachment = new Attachment(lavashId , "LavashMini" ,
                    IMAGES_FOLDER_PATH + lavashId ,
                    "jpg" , 10L , null);

            UUID lavashId1 = UUID.randomUUID();
            Attachment lavashMiniAttachment1 = new Attachment(lavashId1 , "LavashMini" ,
                    IMAGES_FOLDER_PATH + lavashId1 , "jpg" , 10L , null);


            UUID hamburgerId = UUID.randomUUID();
            Attachment hamburgerAttachment = new Attachment(hamburgerId , "Hamburger" ,
                    IMAGES_FOLDER_PATH + hamburgerId ,
                    "jpg" , 10L , null);

            UUID hamburgerId1 = UUID.randomUUID();
            Attachment hamburgerAttachment1 = new Attachment(hamburgerId1 , "Hamburger" ,
                    IMAGES_FOLDER_PATH + hamburgerId1 ,
                    "jpg" , 10L , null);

            UUID donerId = UUID.randomUUID();
            Attachment donerAttachment = new Attachment(donerId , "Doner" ,
                    IMAGES_FOLDER_PATH + donerId ,
                    "jpg" , 10L , null);

            UUID donerId1 = UUID.randomUUID();
            Attachment donerAttachment1 = new Attachment(donerId1 , "Doner" ,
                    IMAGES_FOLDER_PATH + donerId1 ,
                    "jpg" , 10L , null);

            UUID pizzaId = UUID.randomUUID();
            Attachment pizzaAttachment = new Attachment(pizzaId , "Pizza" ,
                    IMAGES_FOLDER_PATH + pizzaId ,
                    "jpg" , 10L , null);

            UUID pizzaId1 = UUID.randomUUID();
            Attachment pizzaAttachment1 = new Attachment(pizzaId1 , "Pizza" ,
                    IMAGES_FOLDER_PATH + pizzaId1 ,
                    "jpg" , 10L , null);


            //Create lavash mini
            Product lavashMini = new Product(null , "Lavash Mini",
                    20_000L, lavashCategory, 250, 0L,
                    Set.of(navruz, extra, extra2) , null ,
                    lavashMiniAttachment, lavashMiniAttachment1, Collections.emptyList()
            );


            //Create hamburger
            Product hamburger = new Product(null ,"Hamburger",
                    25_000L, hamburgerCategory, 300, 0L,
                    Set.of(extra, extra2), null,
                    hamburgerAttachment, hamburgerAttachment1, Collections.emptyList()
            );

            //Create a Doner
            Product doner = new Product(null ,"Coca Cola",
                    30_000L, drinkCategory, 500, 0L,
                    Set.of(navruz, extra), null,
                    donerAttachment, donerAttachment1, Collections.emptyList()
            );


            // Create a Dessert
            Product pizza = new Product(null , "Pizza",
                    65_000L , dessertCategory ,  150, 0L ,
                    Set.of(extra2), null,
                    pizzaAttachment, pizzaAttachment1, Collections.emptyList()
            );

            List<Product> products = Arrays.asList(pizza , lavashMini , hamburger , doner);

            productRepository.saveAll(products);
        }

    }


    private void createDiscount() {

        if (discountRepository.findAll().isEmpty()) {
            Discount discount = new Discount
                    (null, "Navruz", 10, LocalDate.now() ,
                            LocalDate.now().plusDays(10),
                            0, true, null);

            Discount discount1 = new Discount(null, "Extra", 15,LocalDate.now() ,
                    LocalDate.now().plusDays(10), 3, true, null);

            discountRepository.save(discount1);

            Discount discount2 = new Discount(null, "Extra-2", 5, LocalDate.now() ,
                    LocalDate.now().plusDays(5),
                    2, true, null);

            List<Discount> discounts = Arrays.asList(discount1 , discount2 , discount);


            discountRepository.saveAll(discounts);
        }
    }


    private void createAdmin() {

        Role role = roleRepository.findByName("Admin")
                .orElseThrow(() -> new RuntimeException("No admin role found"));

        Address address = new Address( null , BigDecimal.valueOf(69.24007340) , BigDecimal.valueOf(41.29949580)   );

        User admin = new User(
                null, "+97 675-00-00", "asror", passwordEncoder.encode("123"),
                Region.ANDIJON, new ArrayList<>(), Set.of(role), new ArrayList<>(), new HashSet<>()
                ,address
                );

        if (!userRepository.existsByPhoneNumber(admin.getPhoneNumber())) {
            userRepository.save(admin);
        }
    }

    private void createRole() {
        Optional<Role> existingUserRole = roleRepository.findByName("User");
        Optional<Role> existingAdminRole = roleRepository.findByName("Admin");
        Optional<Role> existingStaffRole = roleRepository.findByName("Staff");

        if (existingUserRole.isEmpty()) {
            Set<Permission> userPermissions = permissionRepository.findAllByNameIn(
                    Set.of("order:create", "menu:view", "profile:edit", "order_history:view"));
            Role userRole = new Role("User", userPermissions, null);

            roleRepository.save(userRole);
        }

        if (existingAdminRole.isEmpty()) {
            Set<Permission> adminPermissions = new HashSet<>(permissionRepository.findAll());

            Role adminRole = new Role("Admin", adminPermissions, Collections.emptySet());

            roleRepository.save(adminRole);
        }

        if (existingStaffRole.isEmpty()) {

            Set<Permission> staffPermissions = permissionRepository.findAllByNameIn(
                    Set.of("order:update", "profile:manage"));

            Role staffRole = new Role("Staff", staffPermissions, Collections.emptySet());

            roleRepository.save(staffRole);
        }
    }

    private void createPermissions() {
        if (permissionRepository.findAll().isEmpty()) {
            Set<Permission> permissions = new HashSet<>();


            //USER PERMISSIONS
            permissions.add(new Permission(null, "order:create", Collections.emptySet()));
            permissions.add(new Permission(null, "menu:view", Collections.emptySet()));
            permissions.add(new Permission(null, "profile:manage", Collections.emptySet()));
            permissions.add(new Permission(null, "order_history:view", Collections.emptySet()));


            //COURIER PERMISSIONS
            permissions.add(new Permission(null, "order:update", Collections.emptySet()));

            //STAFF PERMISSIONS
            permissions.add(new Permission(null, "order:update", Collections.emptySet()));
            permissions.add(new Permission(null, "menu:view", Collections.emptySet()));

            permissions.add(new Permission(null, "user:manage", Collections.emptySet()));
            permissions.add(new Permission(null, "permission:set", Collections.emptySet()));

            permissionRepository.saveAll(permissions);
        }
    }
}
