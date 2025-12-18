package org.pepello.common.service;

import java.util.List;
import java.util.UUID;

/**
 * ilişki servisleri için generic interface
 *
 * @param <T> Birincil entity tipi (örn: Project, Team)
 * @param <R> İkincil entity tipi (örn: State, User)
 * @param <K> geri dönecek entity
 */
public interface RelationMethods<T, R, K> {
    /**
     * ilişki ekleme metodu
     *
     * @param primaryId birincil entity id'si
     * @param relatedId ikincil entity id'si
     */
    K addRelation(UUID primaryId, UUID relatedId);

    /**
     * ikinci ilişki ekleme metodu
     *
     * @param relation ilişki entity'si
     */
    K addRelation(K relation);

    /**
     * ilişkiyi bulma metodu
     *
     * @param primaryId birincil entity id'si
     * @param relatedId ikincil entity id'si
     */
    K getRelation(UUID primaryId, UUID relatedId);

    /**
     * ilişki silme metodu
     *
     * @param primaryId birincil entity id'si
     * @param relatedId ikincil entity id'si
     */
    void removeRelation(UUID primaryId, UUID relatedId);

    /**
     * birincil entity'nin bulunduğu tüm ilişkileri getirme metodu
     *
     * @param primaryId birincil entity id'si
     */
    List<K> getAllRelations(UUID primaryId);

    /**
     * ikincil entity'nin bulunduğu tüm ilişkileri getirme metodu
     *
     * @param relatedId birincil entity id'si
     */
    List<K> getAllRelationsByRelatedId(UUID relatedId);

    /**
     * birincil entity'nin tüm ilişkilerini silme metodu
     *
     * @param primaryId birincil entity id'si
     */
    void removeAllRelations(UUID primaryId);

    /**
     * ikincil entity'nin tüm ilişkilerini silme metodu
     *
     * @param relatedId birincil entity id'si
     */
    void removeAllRelationsByRelatedId(UUID relatedId);

    /**
     * birincil entity'nin ilişkilerini getirmek için metod
     *
     * @param primaryId birincil entity id'si
     */
    List<R> getRelatedEntities(UUID primaryId);

    /**
     * ikincil entity'nin ilişkilerini getirmek için metod
     *
     * @param relatedId ikincil entity id'si
     */
    List<T> getPrimaryEntities(UUID relatedId);

    /**
     * aradaki ilişkinin varlığının kontrolü
     *
     * @param primaryId birincil entity id'si
     * @param relatedId ikincil entity id'si
     */
    boolean relationExists(UUID primaryId, UUID relatedId);

    /**
     * birincil entity'nin kaç ilişkisi olduğunu getiren metod
     *
     * @param primaryId birincil entity id'si
     */
    long countRelations(UUID primaryId);

    /**
     * ikincil entity'nin kaç ilişkisi olduğunu getiren metod
     *
     * @param relatedId ikincil entity id'si
     */
    long countRelationsByRelatedId(UUID relatedId);
}
