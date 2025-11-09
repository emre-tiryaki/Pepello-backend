package org.pepello.common.request;

/**
 * Update request'leri için marker interface.
 * Tüm update request recordları bu interface'i implement etmelidir.
 * <p>
 * Örnek kullanım:
 * public record UserUpdateRequest(String name, String email) implements BaseUpdateRequest {}
 */
public interface BaseUpdateRequest {
}
