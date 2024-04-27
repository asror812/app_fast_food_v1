package com.example.app_fast_food.common.service;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class GenericService<ENTITY , ID , RESPONSE_TO , CREATE_DTO , UPDATE_DTO> {

    protected abstract GenericRepository<ENTITY,ID> getRepository();
    protected abstract Class<ENTITY> getEntityClass();
    protected abstract GenericMapper<ENTITY , CREATE_DTO , RESPONSE_TO , UPDATE_DTO> getMapper();

    protected abstract RESPONSE_TO internalCreate(CREATE_DTO createDto);
    protected abstract RESPONSE_TO internalUpdate(ID id , UPDATE_DTO updateDto);

    public RESPONSE_TO create(CREATE_DTO createDto){return internalCreate(createDto);}
    public RESPONSE_TO update(ID id , UPDATE_DTO updateDto){return internalUpdate(id, updateDto);}

    @Transactional
    public List<RESPONSE_TO> getAll(){
         return getRepository().findAll().stream().map(entity -> getMapper().toResponseDTO(entity)).toList();
    }

    @Transactional
    public RESPONSE_TO getById(ID id){
        ENTITY entity = getRepository().findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("%s with id: %s not found".
                                formatted(getEntityClass().getSimpleName() , id)));

        return getMapper().toResponseDTO(entity);
    }

    @Transactional
    public void delete(ID id){
        ENTITY entity = getRepository().findById(id).orElseThrow(
                () -> new EntityNotFoundException("%s with id: %s not found".
                        formatted(getEntityClass() , id)));

        getRepository().delete(entity);
    }


}
