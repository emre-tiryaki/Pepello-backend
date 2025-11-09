package org.pepello.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Relation repository'leri için marker interface.
 * <p>
 * Bu interface sadece type-safety ve kod organizasyonu için kullanılır.
 * Her relation repository bu interface'i extend etmeli ve kendi entity'lerinin
 * property isimlerine göre JPA query metodlarını tanımlamalıdır.
 * <p>
 * Bu metodlar BaseRelationService tarafından abstract metodlar aracılığıyla kullanılır.
 * Her servis implementasyonu kendi repository metodlarını çağırır.
 */
@NoRepositoryBean
public interface BaseRelationRepository<T> extends JpaRepository<T, UUID> {
    // Her relation repository kendi entity property isimlerine göre metodları tanımlayacak
    // Bu interface sadece marker interface olarak kullanılır
}
