package com.wrox.site.services;

import com.wrox.site.SearchCriteria;
import com.wrox.site.entities.Category;
import com.wrox.site.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Validated
public interface EventService {
     Page<Event> getPublishedEvent(Pageable page);
     Event getEventDetail(long eventId);
     Event saveEvent(@Valid Event event, Set<Long> categories, int status);
     Page<Event> getPublishedEvent(long ownerId, Pageable p);
     Page<Event> getEventByStatus(String statusName,long ownerId, Pageable p);
     Page<Event> searchEvent(SearchCriteria criteria, Pageable p);
     Page<Event> searchEvent(String title, Set<Category> categorySet, Set<String> nameSet,
                             Set<String> tagSet, Instant startDate, Instant endDate, Pageable p);
}
