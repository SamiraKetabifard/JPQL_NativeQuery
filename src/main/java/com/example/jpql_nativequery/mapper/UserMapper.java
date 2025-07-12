package com.example.jpql_nativequery.mapper;

import com.example.jpql_nativequery.dto.UserDto;
import com.example.jpql_nativequery.entity.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDto> toDtoList(List<User> users);

}

