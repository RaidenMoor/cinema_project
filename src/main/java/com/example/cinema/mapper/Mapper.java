package com.example.cinema.mapper;

import com.example.cinema.dto.GenericDTO;
import com.example.cinema.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {

  E toEntity(D dto);

  D toDTO(E entity);

  List<D> toDTOs(List<E> entities);
}
