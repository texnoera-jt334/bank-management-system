package az.banking.bankmanagementsystem.mapper;

import az.banking.bankmanagementsystem.dao.entity.Customer;
import az.banking.bankmanagementsystem.dto.CustomerCreatRequest;
import az.banking.bankmanagementsystem.dto.CustomerCreatResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "finCode", source = "finCode")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Customer toCustomerEntity(CustomerCreatRequest CustomerCreatRequest);


    @Mapping(target = "customerId", source = "finCode")
    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "nextMessenger", constant = "Hesab yaradildi")
    @Mapping(target = "createdAt", ignore = true)
    CustomerCreatResponse toCustomerCreatResponse(Customer customer);

    @AfterMapping
    default void toCustomerCreatResponse(@MappingTarget CustomerCreatResponse customerCreatResponse,
                                         Customer customer) {

        customerCreatResponse.setFullName(customer.getName() + " " + customer.getSurname());
    }


}


