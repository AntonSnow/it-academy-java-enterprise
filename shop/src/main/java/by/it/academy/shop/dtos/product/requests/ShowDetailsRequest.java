package by.it.academy.shop.dtos.product.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Обработка запроса на просмотр деталей продукта.
 * Присутствует валидация по полю.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDetailsRequest {

    @NotNull
    private UUID id;
}
