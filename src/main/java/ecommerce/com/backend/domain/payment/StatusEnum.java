package ecommerce.com.backend.domain.payment;

public enum StatusEnum {
    PENDING_PAYMENT("Pending Payment"),
    PROCESSING("Processing"),
    PAID("Paid"),
    FAILED("Failed"),
    CANCELLED("Cancelled"),
    REFUNDED("Refunded"),
    PARTIALLY_REFUNDED("Partially Refunded"),
    ON_HOLD("On Hold"),
    COMPLETED("Completed"),
    AUTHORIZED("Authorized"),
    DECLINED("Declined");

    private final String description;

    StatusEnum (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
