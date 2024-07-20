package ecommerce.com.backend.domain.picture;

import jakarta.validation.constraints.NotEmpty;

public record ProductPictureDtoIn(

        @NotEmpty
        String url
) {

}
