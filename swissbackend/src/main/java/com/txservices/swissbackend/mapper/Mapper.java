package com.txservices.swissbackend.mapper;

import com.txservices.swissbackend.dto.ConnectionDTO;
import com.txservices.swissbackend.entity.Connection;
import org.mapstruct.factory.Mappers;

/**
 * Mapper Interface used for Mapping DTO to Entity
 *
 * @author Dusan Batinica
 *
 */
@org.mapstruct.Mapper
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    Connection connectionDTOToConnection(ConnectionDTO connectionDTO);

}
