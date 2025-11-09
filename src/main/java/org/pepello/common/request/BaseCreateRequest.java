package org.pepello.common.request;

/**
 * Create request'leri için marker interface.
 * Tüm create request recordları bu interface'i implement etmelidir.
 * <p>
 * Örnek kullanım:
 * public record UserCreateRequest(String name, String email) implements BaseCreateRequest {}
 */
public interface BaseCreateRequest {
}
