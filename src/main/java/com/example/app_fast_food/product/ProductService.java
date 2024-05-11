package com.example.app_fast_food.product;

import com.example.app_fast_food.attachment.AttachmentRepository;
import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.bonus.BonusDTOMapper;
import com.example.app_fast_food.bonus.BonusRepository;
import com.example.app_fast_food.bonus.dto.BonusResponseDTO;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.discount.DiscountMapper;
import com.example.app_fast_food.discount.DiscountRepository;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.product.dto.ProductUpdateDTO;
import com.example.app_fast_food.product.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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


        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product product : products) {
            Attachment attachment = attachmentRepository
                    .findByProduct_IdAndMainIsTrue(product.getId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Attachment of product " + product.getId() + " not found"));

            List<Discount> discounts = discountRepository.findByProductId(product.getId());

            Set<DiscountResponseDTO> discountResponseDTOS = discounts.stream()
                    .map(discountMapper::toResponseDTO)
                    .collect(Collectors.toSet());


            Double resultPrice = product.getPrice();

            if (!discountResponseDTOS.isEmpty()) {
                for (DiscountResponseDTO discountResponseDTO : discountResponseDTOS) {
                    resultPrice = (100 - discountResponseDTO.getPercentage()) * resultPrice;
                }
            }

            Set<BonusResponseDTO> bonuses = product.getBonuses()
                    .stream().map(bonusMapper::toResponseDTO)
                    .collect(Collectors.toSet());

            ProductResponseDTO dto = new ProductResponseDTO(
                    product.getId(), product.getSold(), product.getCategory(),
                    attachment.getDownloadUrl(),
                    discountResponseDTOS.stream().toList(),
                    bonuses.stream().toList(), resultPrice);

            productResponseDTOS.add(dto);
        }

        return CommonResponse.succeed(productResponseDTOS);
    }

    public CommonResponse<List<ProductResponseDTO>> get4PopularProducts() {
        List<Product> popularProducts = repository.find4TopSoldProducts();

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product p : popularProducts) {

            Attachment attachment = attachmentRepository
                    .findByProduct_IdAndMainIsTrue(p.getId())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Attachment of product " + p.getId() + " not found")
                    );

            List<Discount> discounts = discountRepository.findByProductId(p.getId());

            Set<DiscountResponseDTO> discountResponseDTOS = discounts.stream()
                    .map(discountMapper::toResponseDTO)
                    .collect(Collectors.toSet());

            Double resultPrice = p.getPrice();

            if (!discountResponseDTOS.isEmpty()) {
                for (DiscountResponseDTO discountResponseDTO : discountResponseDTOS) {
                    resultPrice = (100 - discountResponseDTO.getPercentage()) * resultPrice;
                }
            }

            Set<BonusResponseDTO> bonuses = p.getBonuses()
                    .stream().map(bonusMapper::toResponseDTO)
                    .collect(Collectors.toSet());


            ProductResponseDTO dto =
                    new ProductResponseDTO(p.getId(), p.getSold(), p.getCategory(),
                            attachment.getDownloadUrl(),
                            discountResponseDTOS.stream().toList(),
                            bonuses.stream().toList(), resultPrice);

            productResponseDTOS.add(dto);
        }

        return CommonResponse.succeed(productResponseDTOS);
    }

    public CommonResponse<List<ProductResponseDTO>> getCampaignProducts() {

        List<Product> campaignProducts = repository.getCampaignProducts();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();


        for (Product p : campaignProducts) {

            Attachment attachment = attachmentRepository
                    .findByProduct_IdAndMainIsTrue(p.getId())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Attachment of product " + p.getId() + " not found")
                    );

            List<Discount> discounts = discountRepository.findByProductId(p.getId());

            Set<DiscountResponseDTO> discountResponseDTOS = discounts.stream()
                    .map(discountMapper::toResponseDTO)
                    .collect(Collectors.toSet());

            Double resultPrice = p.getPrice();

            if (!discountResponseDTOS.isEmpty()) {
                for (DiscountResponseDTO discountResponseDTO : discountResponseDTOS) {
                    resultPrice = (100 - discountResponseDTO.getPercentage()) * resultPrice;
                }
            }

            Set<BonusResponseDTO> bonuses = p.getBonuses()
                    .stream().map(bonusMapper::toResponseDTO)
                    .collect(Collectors.toSet());


            ProductResponseDTO dto =
                    new ProductResponseDTO(p.getId(), p.getSold(), p.getCategory(),
                            attachment.getDownloadUrl(),
                            discountResponseDTOS.stream().toList(),
                            bonuses.stream().toList(), resultPrice);

            productResponseDTOS.add(dto);
        }

        return CommonResponse.succeed(productResponseDTOS);
    }
}
