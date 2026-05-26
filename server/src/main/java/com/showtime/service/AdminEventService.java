package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showtime.common.BizException;
import com.showtime.common.PageResult;
import com.showtime.common.ResultCode;
import com.showtime.dto.AdminEventRequest;
import com.showtime.entity.Category;
import com.showtime.entity.Event;
import com.showtime.entity.Venue;
import com.showtime.mapper.CategoryMapper;
import com.showtime.mapper.EventMapper;
import com.showtime.mapper.VenueMapper;
import com.showtime.vo.AdminEventVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventService {

    private final EventMapper eventMapper;
    private final CategoryMapper categoryMapper;
    private final VenueMapper venueMapper;

    public PageResult<AdminEventVO> list(String keyword, Integer status, Integer page, Integer pageSize) {
        LambdaQueryWrapper<Event> wrapper = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.like(Event::getTitle, keyword);
        }
        if (status != null) {
            wrapper.eq(Event::getStatus, status);
        }
        wrapper.orderByDesc(Event::getCreatedAt);
        Page<Event> result = eventMapper.selectPage(
            new Page<>(page != null ? page : 1, pageSize != null ? pageSize : 10), wrapper);

        List<AdminEventVO> vos = result.getRecords().stream().map(e -> {
            AdminEventVO vo = new AdminEventVO();
            vo.setId(e.getId());
            vo.setTitle(e.getTitle());
            vo.setPosterUrl(e.getPosterUrl());
            vo.setDuration(e.getDuration());
            vo.setStatus(e.getStatus());
            vo.setCreatedAt(e.getCreatedAt());
            Category cat = categoryMapper.selectById(e.getCategoryId());
            if (cat != null) vo.setCategoryName(cat.getName());
            Venue venue = venueMapper.selectById(e.getVenueId());
            if (venue != null) vo.setVenueName(venue.getName());
            return vo;
        }).toList();

        return PageResult.of(vos, result.getTotal(), (int) result.getCurrent(), (int) result.getSize());
    }

    public Event create(AdminEventRequest req) {
        Event event = new Event();
        event.setTitle(req.getTitle());
        event.setDescription(req.getDescription());
        event.setPosterUrl(req.getPosterUrl());
        event.setDuration(req.getDuration());
        event.setCategoryId(req.getCategoryId());
        event.setVenueId(req.getVenueId());
        event.setStatus(req.getStatus() != null ? req.getStatus() : 0);
        eventMapper.insert(event);
        return event;
    }

    public Event update(Integer id, AdminEventRequest req) {
        Event event = eventMapper.selectById(id);
        if (event == null) throw new BizException(ResultCode.NOT_FOUND, "演出不存在");
        event.setTitle(req.getTitle());
        event.setDescription(req.getDescription());
        event.setPosterUrl(req.getPosterUrl());
        event.setDuration(req.getDuration());
        event.setCategoryId(req.getCategoryId());
        event.setVenueId(req.getVenueId());
        event.setStatus(req.getStatus());
        eventMapper.updateById(event);
        return event;
    }

    public void delete(Integer id) {
        eventMapper.deleteById(id);
    }
}
