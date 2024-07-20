package ecommerce.com.backend.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.com.backend.domain.inventory.InventoryDtoIn;
import ecommerce.com.backend.domain.picture.ProductPictureDtoIn;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record ProductDtoIn(

        @NotEmpty
        String name,

        @NotEmpty
        String description,

        @JsonProperty(value = "main_picture")
        ProductPictureDtoIn mainPicture,

        @NotEmpty
        InventoryDtoIn inventory,

        @JsonProperty(value = "product_pictures")
        Set<ProductPictureDtoIn> productPictures
) {
}
