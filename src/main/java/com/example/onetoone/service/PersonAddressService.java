package com.example.onetoone.service;

import com.example.onetoone.entity.AddressEntity;
import com.example.onetoone.entity.PersonEntity;
import com.example.onetoone.mapper.AddressMapper;
import com.example.onetoone.mapper.PersonMapper;
import com.example.onetoone.model.Address;
import com.example.onetoone.model.Person;
import com.example.onetoone.model.PersonResponse;
import com.example.onetoone.repository.AddressRepository;
import com.example.onetoone.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class PersonAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    public PersonResponse createPerson(Person person) {

        PersonEntity personEntity = personMapper.personToEntity(person);

        AddressEntity addressEntity = addressMapper.addressToEntity(person.getAddress());
        personEntity.setAddress(addressEntity);
        addressEntity.setPerson(personEntity);
//        personEntity.getAddress().setPerson(personEntity);
        personRepository.save(personEntity);
//        addressRepository.save(addressEntity);

        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getId());

        log.info("user name{}{} ", personEntity.getFirstName(), personEntity.getLastName() + " created successful");
        return personResponse;


    }

    public Person getPerson(Long id) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        Person person =new Person();
        Address address;
        if (personEntityOptional.isPresent()) {
            AddressEntity addressEntity = personEntityOptional.get().getAddress();
            PersonEntity personEntity = personEntityOptional.get();
            person = personMapper.entityToPerson(personEntity);
            address = addressMapper.entityToAddress(addressEntity);
            person.setAddress(address);


        } else {
            log.info("Person id" + id + "Not found");
        }
        return person;
    }

    public void updatePerson(Long id, Person person) {
        PersonEntity personEntity=personRepository.findById(id).get();
        AddressEntity addressEntity=addressRepository.findById(personEntity.getAddress().getId()).get();

        addressEntity.setAddress1(person.getAddress().getAddress1());
        addressEntity.setAddress2(person.getAddress().getAddress2());
        addressEntity.setCity(person.getAddress().getCity());
        addressEntity.setState(person.getAddress().getState());
        addressEntity.setZipcode(person.getAddress().getZipCode());
        addressRepository.save(addressEntity);

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personRepository.save(personEntity);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
