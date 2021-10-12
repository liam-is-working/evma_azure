package com.wrox.site.repositories;

import com.wrox.site.entities.Event;
import com.wrox.site.entities.EventStatus;
import com.wrox.site.entities.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long>,
          CustomEventRepository{
     Page<Event> getEventByUserProfileId(long userProfileId, Pageable p);
     Page<Event> getEventByStatusAndUserProfileId(EventStatus status,long userProfileId, Pageable p);
     Page<Event> getEventByStatus(EventStatus status, Pageable p);

}
