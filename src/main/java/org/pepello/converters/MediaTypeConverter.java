package org.pepello.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.pepello.entities.enums.MediaType;

@Converter(autoApply = true)
public class MediaTypeConverter implements AttributeConverter<MediaType, String> {

    @Override
    public String convertToDatabaseColumn(MediaType attribute) {
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public MediaType convertToEntityAttribute(String dbData) {
        return dbData != null ? MediaType.valueOf(dbData) : null;
    }
}