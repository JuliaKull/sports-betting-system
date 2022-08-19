package com.betpawa.wallet.mapper;


import java.util.List;
import java.util.stream.Collectors;

public interface WebMapper <D,E> {
    D toDTO(E entity);

    E toEntity(D dto);

    default List<D> toDtos(List<E> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    default List<E> toEntities(List<D> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(dto -> toEntity(dto)).collect(Collectors.toList());
    }
}
