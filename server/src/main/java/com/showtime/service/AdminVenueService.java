package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showtime.common.BizException;
import com.showtime.common.PageResult;
import com.showtime.common.ResultCode;
import com.showtime.dto.AdminVenueRequest;
import com.showtime.entity.Venue;
import com.showtime.mapper.VenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminVenueService {

    private final VenueMapper venueMapper;

    public PageResult<Venue> list(String keyword, Integer page, Integer pageSize) {
        LambdaQueryWrapper<Venue> wrapper = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.and(w -> w.like(Venue::getName, keyword)
                              .or().like(Venue::getCity, keyword));
        }
        wrapper.orderByDesc(Venue::getCreatedAt);
        Page<Venue> result = venueMapper.selectPage(
            new Page<>(page != null ? page : 1, pageSize != null ? pageSize : 10), wrapper);
        return PageResult.of(result.getRecords(), result.getTotal(),
            (int) result.getCurrent(), (int) result.getSize());
    }

    public Venue create(AdminVenueRequest req) {
        Venue venue = new Venue();
        venue.setName(req.getName());
        venue.setCity(req.getCity());
        venue.setAddress(req.getAddress());
        venue.setDescription(req.getDescription());
        venueMapper.insert(venue);
        return venue;
    }

    public Venue update(Integer id, AdminVenueRequest req) {
        Venue venue = venueMapper.selectById(id);
        if (venue == null) throw new BizException(ResultCode.NOT_FOUND, "场馆不存在");
        venue.setName(req.getName());
        venue.setCity(req.getCity());
        venue.setAddress(req.getAddress());
        venue.setDescription(req.getDescription());
        venueMapper.updateById(venue);
        return venue;
    }

    public void delete(Integer id) {
        venueMapper.deleteById(id);
    }
}
