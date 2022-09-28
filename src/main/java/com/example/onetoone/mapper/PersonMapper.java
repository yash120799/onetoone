package com.example.onetoone.mapper;

import com.example.onetoone.entity.PersonEntity;
import com.example.onetoone.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity personToEntity(Person person);
    Person entityToPerson(PersonEntity personEntity );
}
