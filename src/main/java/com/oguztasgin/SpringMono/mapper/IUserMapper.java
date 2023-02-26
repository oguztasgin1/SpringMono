package com.oguztasgin.SpringMono.mapper;

import com.oguztasgin.SpringMono.dto.request.RegisterRequestDto;
import com.oguztasgin.SpringMono.dto.response.UserControllerFindAllResponseDto;
import com.oguztasgin.SpringMono.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Class->Dto ya da Dto->Class tür dönüşümleri için ilk olarak ilgili interface üzerinde
 * "@Mapper" anotasyonumuzu ekliyoruz. Burada dikkat edilmesi gerek iki nokta var bunlardan
 * 1. mapper spring framework un uygulama çatısına uygun olarak entegre olsun diye component
 * model olarak spring i ekliyor.
 * 2. olarakta ister istemez tür dönüşümlerinde alan adları bire bir uyuşmamaktadır. Bu nedenle
 * eşleşmeyen alanların nasıl davranacağının belirlenmesi gereklidir. Bunu sağlamak için hedef
 * politikalarının seçilmesine ihtiyaç vardır.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);


    User toUser(final RegisterRequestDto dto);
    //"@//"Mapping(source = "username",target = "kullanicininadi")
    //"@//"Mappings(@Mapping(),@Mapping())

    UserControllerFindAllResponseDto userControllerFindAllResponseDtoFromUser(final User user);
}
