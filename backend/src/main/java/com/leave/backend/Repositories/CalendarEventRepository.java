package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.CalendarEvent;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer>{
    
}
