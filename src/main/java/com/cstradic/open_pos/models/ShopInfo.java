package com.cstradic.open_pos.models;

import java.time.LocalDateTime;

/**
 * Projection for {@link Shop}
 */
public interface ShopInfo {
    Long getId();

    String getName();

    String getAddress();

    String getLogoUrl();

    String getEmail();

    String getContact();

    LocalDateTime getCreatedAt();
}