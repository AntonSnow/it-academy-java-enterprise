package by.it.academy.shop.controllers.product;

import by.it.academy.shop.dtos.product.requests.CreateProductRequest;
import by.it.academy.shop.dtos.product.requests.ListProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер обработки запросов продуктов.
 */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Метод showProducts прослушивает адресс /products/catalog, в случае наличия списков параметров
     * "productCategories", "productTypes", "productColours", "userInputProductName" возвращаемых с WEB-страницы
     * выполнит поиск продуктов в базе данных с учетом соответствия всем данным параметрам, в случае их отсутствия вернет
     * все продукты имеющиеся в базе данных. Передаст полученные в базе данных продукты в виде списка для их отображения
     * на странице. В случае наличия параметров, но отсутствия совпадений в базе данный вернет сообщение об не нахождения
     * продуктов по заданному запросу.
     */
    @RequestMapping("/catalog")
    @GetMapping
    public List<Product> showProducts(@RequestBody ListProductRequest showProductRequests) {
        return productService.showProduct(showProductRequests);
    }

    /**
     * Метод showProductById прослушивает адресс /products/details/, принимает поступающий "id" продукта в виде UUID,
     * производит поиск совпадения в базе данных.
     * По результатам поиска вернет искомый продукт или вернет сообщение об не найденном объекте в случае не успешного поиска.
     */
    @RequestMapping("/details/{id}")
    @GetMapping
    public Product showProductById(@PathVariable UUID id) {
        return productService.showProductById(id);
    }

    /**
     * Метод createProduct прослушивает адресс /products/admin/new-product, в случае корректности введеных данных, добавит
     * новый продукт в базу дынных. По результатам добавления вернет новый продукт или вернет сообщение о не корректных
     * введенных данных в случае не успешного добавления.
     */
    @RequestMapping("/admin/new-product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Validated CreateProductRequest createProductRequest) {
        return productService.addProduct(createProductRequest);
    }

    /**
     * Метод updateProduct прослушивает адресс /products/admin, принимает поступающий "id" продукта в виде UUID и новые
     * значения продукта, в случае корректности введеных данных, обновит продукт в базе дынных.
     * По результатам обновления вернет обновленный продукт или вернет сообщение об не найденном объекте в случае не корректного id.
     */
    @RequestMapping("/admin")
    @PutMapping
    public Product updateProduct(@RequestBody @Validated UpdateProductRequest updateProductRequest) {
        return productService.updateProduct(updateProductRequest);
    }

    /**
     * Метод updateQuantityProduct прослушивает адресс /products/admin/no-product, принимает поступающий "id" продукта
     * в виде UUID, в случае корректности введеных данных, обнулит количество продукта на складе в базе дынных.
     * По результатам обнуления вернет сообщение о успешности операции или вернет сообщение об не найденном объекте
     * в случае не корректного id.
     */
    @RequestMapping("/admin/no-product/{id}")
    @PutMapping
    public boolean updateQuantityProduct(@PathVariable UUID id) {
        return productService.clearQuantityProduct(id);
    }
}
