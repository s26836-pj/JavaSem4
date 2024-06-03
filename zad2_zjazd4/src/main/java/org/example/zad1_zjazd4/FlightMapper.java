package org.example.zad1_zjazd4;

import io.swagger.client.model.FlightRequest;
import io.swagger.client.model.FlightResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Builder;


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface FlightMapper {

    @Mapping(target = "id", ignore = true)
    Flight mapToEntity(FlightRequest request);

    FlightResponse mapEntityToResponse(Flight entity);

    @Mapping(target = "id", ignore = true)
    Flight update(FlightRequest request, @MappingTarget Flight flight);

}
