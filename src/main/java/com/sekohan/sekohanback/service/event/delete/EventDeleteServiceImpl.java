package com.sekohan.sekohanback.service.event.delete;

import com.sekohan.sekohanback.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventDeleteServiceImpl implements EventDeleteService{

    private final EventRepository eventRepository;
    @Override
    public void remove(Long eid){
        eventRepository.deleteById(eid);
    }

}
