package com.example.core.enums.converter;

import com.example.core.enums.ValueEnum;

import javax.persistence.AttributeConverter;

/**
 * Abstract converter.
 *
 * @param <E> enum generic
 * @param <V> value generic
 * @author johnniang
 * @date 12/6/18
 */
public abstract class AbstractConverter<E extends ValueEnum<V>, V> implements AttributeConverter<E, V> {

    private final Class<E> clazz;

    protected AbstractConverter(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public V convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public E convertToEntityAttribute(V dbData) {
        return dbData == null ? null : ValueEnum.valueToEnum(clazz, dbData);
    }
}
