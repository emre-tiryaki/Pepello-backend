package org.pepello.mappers;

/*
 * Mapperlar&#x131;n implemente edecekleri interface
 * @param <E> entity
 * @param <D> Dto
 */
public interface BaseMapper<E, D> {
    //Dto'dan entity'e dönüştürmek
    E fromDto(D d);

    //entity'den dtoya dönüştürmek
    D toDto(E e);
}
