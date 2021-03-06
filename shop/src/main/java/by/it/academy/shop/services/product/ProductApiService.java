package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.CreateProductRequest;
import by.it.academy.shop.dtos.product.requests.ListProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;

import by.it.academy.shop.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * Реализация сервиса обработки продукта.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApiService implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Product addProduct(CreateProductRequest addProductRequest) {
        final Product product = buildProduct(addProductRequest);
        return productRepository.save(product);
    }

    @Override
    public Product showProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Product> showProduct(ListProductRequest showProductRequest) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(productRepository, showProductRequest);
        return productSearch.getProducts();
    }

    @Transactional
    @Override
    public Product updateProduct(UpdateProductRequest updateProductRequest) {
        productRepository.findById(updateProductRequest.getId()).orElseThrow(EntityNotFoundException::new);
        Product product = buildUpdateProduct(updateProductRequest);

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public boolean clearQuantityProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.setQuantity(0);
        productRepository.save(product);
        return true;
    }

    private Product buildProduct(CreateProductRequest request) {
        return Product.builder()
                .imagePath(request.getImagePath())
                .name(request.getName())
                .productCategory(request.getProductCategory())
                .productType(request.getProductType())
                .productColour(request.getProductColour())
                .productDetails(request.getProductDetails())
                .sizeClothes(request.getSizeClothes())
                .price(Integer.parseInt(request.getPrice()))
                .quantity(Integer.parseInt(request.getQuantity()))
                .build();
    }

    private Product buildUpdateProduct(UpdateProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .imagePath(request.getImagePath())
                .name(request.getName())
                .productCategory(request.getProductCategory())
                .productType(request.getProductType())
                .productColour(request.getProductColour())
                .productDetails(request.getProductDetails())
                .sizeClothes(request.getSizeClothes())
                .price(Integer.parseInt(request.getPrice()))
                .quantity(Integer.parseInt(request.getQuantity()))
                .build();
    }
}
