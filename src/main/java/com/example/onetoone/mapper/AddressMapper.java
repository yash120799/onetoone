package com.example.onetoone.mapper;

import com.example.onetoone.entity.AddressEntity;
import com.example.onetoone.entity.PersonEntity;
import com.example.onetoone.model.Address;
import com.example.onetoone.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity addressToEntity(Address address);
    Address entityToAddress(AddressEntity addressEntity);
}
