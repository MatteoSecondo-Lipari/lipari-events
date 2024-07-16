package com.lipari.events.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultsDTO {
    private List<EventWithSubcategoryWithoutloopDTO> events;
    private List<EntertainerNNEventsDTO> entertainers;
}
