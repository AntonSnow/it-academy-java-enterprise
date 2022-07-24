package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.CreateProductRequest;
import by.it.academy.shop.dtos.product.requests.ProductRequest;
import by.it.academy.shop.dtos.product.requests.ListProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;

import java.util.List;

/**
 * Сервис обработки продукта.
 */

public interface ProductService {
    /**
     * Добавление нового продукта
     */
    Product addProduct(CreateProductRequest addProductRequests);

    /**
     * Нахождение списка продуктов соответствующий выборам пользователя
     */
    List<Product> showProduct(ListProductRequest showProductRequests);

    /**
     * Нахождение продукта по Id
     */
    Product showProductById(ProductRequest idProductRequest);

    /**
     * Обновление продукта
     */
    Product updateProduct(UpdateProductRequest updateProductRequest);

    /**
     * Удаление продукта по Id
     */
    boolean clearQuantityProduct(ProductRequest idProductRequest);
}
