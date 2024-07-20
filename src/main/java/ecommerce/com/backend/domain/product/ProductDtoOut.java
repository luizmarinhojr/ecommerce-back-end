package ecommerce.com.backend.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.com.backend.domain.picture.ProductPicture;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record ProductDtoOut (

    @JsonProperty(value = "external_id")
    UUID externalId,

    String name,

    String description,

    @JsonProperty(value = "main_picture")
    String mainPicture,

    Set<String> pictures,

    int quantity
) {

    public ProductDtoOut (Product product) {
        this(
                product.getExternalId(),
                product.getName(),
                product.getDescription(),
                product.getMainPicture() != null ? product.getMainPicture().getUrl() : null,
                product.getProductPictures() != null ?
                        product.getProductPictures().stream().map(ProductPicture::getUrl).collect(Collectors.toSet()) : null,
                product.getInventory().getQuantity()
        );
    }
}
