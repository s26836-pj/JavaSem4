package org.example.zad1_zjazd4;

import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface FlightMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "flights", ignore = true)
    Passenger toEntity(FlightRequest request);

    FlightResponse toResponse(Flight flight);





}
