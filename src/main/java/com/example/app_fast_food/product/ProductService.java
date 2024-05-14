package com.example.app_fast_food.product;

import com.example.app_fast_food.attachment.AttachmentRepository;
import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.bonus.BonusDTOMapper;
import com.example.app_fast_food.bonus.BonusRepository;
import com.example.app_fast_food.bonus.dto.BonusResponseDTO;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.discount.DiscountMapper;
import com.example.app_fast_food.discount.DiscountRepository;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.product.dto.ProductUpdateDTO;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class ProductService extends GenericService<Product , UUID , ProductResponseDTO , ProductCreateDTO , ProductUpdateDTO > {

    private final ProductRepository repository;
    private final Class<Product> entityClass = Product.class;
    private final ProductDTOMapper mapper;
    private final AttachmentRepository attachmentRepository;
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;
    private final BonusDTOMapper bonusMapper;
    private final BonusRepository bonusRepository;
    private final UserRepository userRepository;

    @Override
    protected CommonResponse<ProductResponseDTO> internalCreate(ProductCreateDTO productCreateDTO) {
        Product entity = mapper.toEntity(productCreateDTO);
        repository.save(entity);
        return CommonResponse.succeed(mapper.toResponseDTO(entity));
    }

    @Override
    protected CommonResponse<ProductResponseDTO> internalUpdate(UUID uuid, ProductUpdateDTO productUpdateDTO) {
        return null;
    }

    public CommonResponse<List<ProductResponseDTO>> getByCategory(String categoryName) {
        List<Product> products = repository.findProductsByCategoryName(categoryName);


        List<ProductResponseDTO> productResponseDTOS = getProductResponseDTOS(products);

        return CommonResponse.succeed(productResponseDTOS);
    }

    public CommonResponse<List<ProductResponseDTO>> get4PopularProducts() {
        List<Product> popularProducts = repository.find4TopSoldProducts();

        List<ProductResponseDTO> productResponseDTOS = getProductResponseDTOS(popularProducts);

        return CommonResponse.succeed(productResponseDTOS);
    }

    public CommonResponse<List<ProductResponseDTO>> getCampaignProducts() {

        List<Product> campaignProducts = repository.getCampaignProducts();
        List<ProductResponseDTO> productResponseDTOS = getProductResponseDTOS(campaignProducts);

        return CommonResponse.succeed(productResponseDTOS);
    }


    public List<ProductResponseDTO> getProductResponseDTOS(List<Product> popularProducts) {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product p : popularProducts) {

            Attachment attachment = attachmentRepository
                    .findByProduct_IdAndMainIsTrue(p.getId())
                    .orElseThrow(
                            () ->
                            new EntityNotFoundException("Attachment of product " + p.getId() + " not found")) ;

            List<DiscountResponseDTO> discountResponseDTOS = discountRepository
                    .findByProductId(p.getId()).stream()
                    .map(discountMapper::toResponseDTO)
                    .toList();

            Double resultPrice = p.getPrice();

            if (!discountResponseDTOS.isEmpty()) {
                for (DiscountResponseDTO discountResponseDTO : discountResponseDTOS) {
                    resultPrice -= discountResponseDTO.getPercentage() * p.getPrice();
                }
            }

            List<BonusResponseDTO> bonuses = p.getBonuses()
                    .stream().map(bonusMapper::toResponseDTO)
                    .toList();


            ProductResponseDTO dto =
                    new ProductResponseDTO(p.getId(), p.getSold(), p.getCategory(),
                            attachment.getDownloadUrl(),
                            discountResponseDTOS.stream().toList(),
                            bonuses , resultPrice);

            productResponseDTOS.add(dto);
        }
        return productResponseDTOS;
    }

    public CommonResponse<ProductResponseDTO> getSpecificProduct(UUID id) {
        Product product = repository.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));

        ProductResponseDTO dto = getProductResponseDTOS(List.of(product))
                .get(0);

        return CommonResponse.succeed(dto);
    }
}
