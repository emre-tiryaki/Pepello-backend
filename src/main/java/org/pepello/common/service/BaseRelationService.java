package org.pepello.common.service;

import jakarta.transaction.Transactional;
import org.pepello.common.ICrud;
import org.pepello.common.repository.BaseRelationRepository;

import java.util.List;
import java.util.UUID;

/**
 * Entity'ler arası ilişkileri yöneten servisler için abstract base class.
 * RelationMethods interface'ini implement eder ve transaction yönetimi sağlar.
 *
 * @param <P>   Primary (birincil) entity tipi
 * @param <R>   Related (ilişkili) entity tipi
 * @param <REL> Relation (ilişki) entity tipi
 */
@Transactional
public abstract class BaseRelationService<P, R, REL> implements RelationMethods<P, R, REL> {

    /**
     * İlişki repository'sini döndürür.
     * Alt sınıflar kendi repository'lerini inject ederek bu metodu implement eder.
     *
     * @return ilişki repository'si
     */
    protected abstract BaseRelationRepository<REL> getRepository();

    /**
     * Primary entity servisi döndürür.
     * Alt sınıflar ilgili ICrud servisini inject ederek bu metodu implement eder.
     *
     * @return primary entity servisi
     */
    protected abstract ICrud<P, ?, ?> getPrimaryService();

    /**
     * Related entity servisi döndürür.
     * Alt sınıflar ilgili ICrud servisini inject ederek bu metodu implement eder.
     *
     * @return related entity servisi
     */
    protected abstract ICrud<R, ?, ?> getRelatedService();

    /**
     * İki entity'den yeni bir relation entity oluşturur.
     * Builder pattern kullanarak ilişki objesi yaratılmalıdır.
     *
     * @param primary birincil entity
     * @param related ilişkili entity
     * @return oluşturulan relation entity
     */
    protected abstract REL buildRelation(P primary, R related);

    /**
     * İki entity arasındaki ilişkiyi Optional olarak döndürür.
     * Alt sınıflar repository'den Optional döndürmeli, hata yönetimi base class'ta yapılır.
     *
     * @param primaryId birincil entity ID'si
     * @param relatedId ilişkili entity ID'si
     * @return ilişki Optional'ı
     */
    protected abstract java.util.Optional<REL> findRelationOptional(UUID primaryId, UUID relatedId);

    /**
     * Primary entity'ye ait tüm ilişkileri getirir.
     *
     * @param primaryId birincil entity ID'si
     * @return ilişki listesi
     */
    protected abstract List<REL> findByPrimaryId(UUID primaryId);

    /**
     * Related entity'ye ait tüm ilişkileri getirir.
     *
     * @param relatedId ilişkili entity ID'si
     * @return ilişki listesi
     */
    protected abstract List<REL> findByRelatedId(UUID relatedId);

    /**
     * Primary entity'nin tüm ilişkilerini toplu olarak siler.
     *
     * @param primaryId birincil entity ID'si
     */
    protected abstract void deleteAllByPrimaryId(UUID primaryId);

    /**
     * Related entity'nin tüm ilişkilerini toplu olarak siler.
     *
     * @param relatedId ilişkili entity ID'si
     */
    protected abstract void deleteAllByRelatedId(UUID relatedId);

    /**
     * İki entity arasında ilişki olup olmadığını kontrol eder.
     *
     * @param primaryId birincil entity ID'si
     * @param relatedId ilişkili entity ID'si
     * @return ilişki varsa true, yoksa false
     */
    protected abstract boolean existsRelation(UUID primaryId, UUID relatedId);

    /**
     * Primary entity'nin toplam ilişki sayısını döndürür.
     *
     * @param primaryId birincil entity ID'si
     * @return ilişki sayısı
     */
    protected abstract long countByPrimaryId(UUID primaryId);

    /**
     * Related entity'nin toplam ilişki sayısını döndürür.
     *
     * @param relatedId ilişkili entity ID'si
     * @return ilişki sayısı
     */
    protected abstract long countByRelatedId(UUID relatedId);

    /**
     * İlişki entity'sinden primary entity'yi çıkarır.
     *
     * @param relation ilişki entity'si
     * @return primary entity
     */
    protected abstract P extractPrimary(REL relation);

    /**
     * İlişki entity'sinden related entity'yi çıkarır.
     *
     * @param relation ilişki entity'si
     * @return related entity
     */
    protected abstract R extractRelated(REL relation);

    /**
     * İki entity arasında yeni bir ilişki oluşturur.
     * Entity'lerin varlığını kontrol eder ve ilişki zaten mevcutsa hata fırlatır.
     *
     * @param primaryId birincil entity'nin ID'si
     * @param relatedId ilişkili entity'nin ID'si
     * @return oluşturulan ilişki entity'si
     * @throws RuntimeException eğer ID'ler null ise, entity'ler bulunamazsa veya ilişki zaten mevcutsa
     */
    @Override
    public REL addRelation(UUID primaryId, UUID relatedId) {
        validateIds(primaryId, relatedId);

        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (existsRelation(primaryId, relatedId)) {
            throw new RuntimeException("İlişki zaten mevcut");
        }

        P existingPrimaryEntity = getPrimaryService().getById(primaryId);
        R existingRelatedEntity = getRelatedService().getById(relatedId);

        REL relation = buildRelation(existingPrimaryEntity, existingRelatedEntity);

        return getRepository().save(relation);
    }

    /**
     * iki id arası ilişkiyi döndürmek için metod
     *
     * @param primaryId birincil id
     * @param relatedId ikincil id
     */
    public REL findRelation(UUID primaryId, UUID relatedId) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        return findRelationOptional(primaryId, relatedId)
                .orElseThrow(() -> new RuntimeException(
                        "İlişki bulunamadı - Primary ID: " + primaryId + ", Related ID: " + relatedId
                ));
    }

    /**
     * ilişki entity'sini döndürme metodu
     *
     * @param primaryId birincil entity id'si
     * @param relatedId ikincil entity id'si
     */
    @Override
    public REL getRelation(UUID primaryId, UUID relatedId) {
        validateIds(primaryId, relatedId);
        return findRelation(primaryId, relatedId);
    }

    /**
     * ilişkiyi silme metodu
     *
     * @param primaryId birincil entity id'si
     * @param relatedId ikincil entity id'si
     */
    @Override
    public void removeRelation(UUID primaryId, UUID relatedId) {
        validateIds(primaryId, relatedId);

        REL relation = getRelation(primaryId, relatedId);

        getRepository().delete(relation);
    }

    /**
     * birincil entity'nin içinde bulunduğu  tüm ilişkileri döndürme metodu
     *
     * @param primaryId birincil entity id'si
     */
    @Override
    public List<REL> getAllRelations(UUID primaryId) {
        validateId(primaryId);
        return findByPrimaryId(primaryId);
    }

    /**
     * ikincil entity'nin içinde bulunduğu  tüm ilişkileri döndürme metodu
     *
     * @param relatedId ikincil entity id'si
     */
    @Override
    public List<REL> getAllRelationsByRelatedId(UUID relatedId) {
        validateId(relatedId);
        return findByRelatedId(relatedId);
    }

    /**
     * birincil entity'nin tüm ilişkilerini silme metodu
     *
     * @param primaryId birincil entity id'si
     */
    @Override
    public void removeAllRelations(UUID primaryId) {
        validateId(primaryId);
        deleteAllByPrimaryId(primaryId);
    }

    /**
     * ikincil entity'nin tüm ilişkilerini silme metodu
     *
     * @param relatedId ikincil entity id'si
     */
    @Override
    public void removeAllRelationsByRelatedId(UUID relatedId) {
        validateId(relatedId);
        deleteAllByRelatedId(relatedId);
    }

    /**
     * birincil entity'nin ilişkili olduğu entitylerin listesini döndüren metod
     *
     * @param primaryId birincil entity id'si
     */
    @Override
    public List<R> getRelatedEntities(UUID primaryId) {
        validateId(primaryId);
        return getAllRelations(primaryId).stream()
                .map(this::extractRelated)
                .toList();
    }

    /**
     * ikincil entity'nin ilişkili olduğu entitylerin listesini döndüren metod
     *
     * @param relatedId ikincil entity id'si
     */
    @Override
    public List<P> getPrimaryEntities(UUID relatedId) {
        validateId(relatedId);
        return getAllRelationsByRelatedId(relatedId).stream()
                .map(this::extractPrimary)
                .toList();
    }

    /**
     * arada bir ilişki var mı yok mu bakmak için metod
     *
     * @param primaryId birincil entity id'si
     * @param relatedId ikincil entity id'si
     */
    @Override
    public boolean relationExists(UUID primaryId, UUID relatedId) {
        validateIds(primaryId, relatedId);
        return existsRelation(primaryId, relatedId);
    }

    /**
     * birincil ilişkinin kaç ilişkisi olduğunu gösteren metod
     *
     * @param primaryId birincil entity id'si
     */
    @Override
    public long countRelations(UUID primaryId) {
        validateId(primaryId);
        return countByPrimaryId(primaryId);
    }

    /**
     * ikincil entity'nin kaç ilişkisi olduğunu gösteren metod
     *
     * @param relatedId ikincil entity id'si
     */
    @Override
    public long countRelationsByRelatedId(UUID relatedId) {
        validateId(relatedId);
        return countByRelatedId(relatedId);
    }

    /**
     * id değişkeninin varlığını kontrol eden metod
     *
     * @param id id işte
     */
    private void validateId(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
    }

    /**
     * id değişkenlerinin varlığını kontrol eden metod
     *
     * @param primaryId birincil id işte
     * @param relatedId ikincil id işte
     */
    private void validateIds(UUID primaryId, UUID relatedId) {
        validateId(primaryId);
        validateId(relatedId);
    }
}

