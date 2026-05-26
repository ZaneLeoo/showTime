package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.showtime.common.BizException;
import com.showtime.common.ResultCode;
import com.showtime.dto.AdminSessionRequest;
import com.showtime.entity.EventSession;
import com.showtime.entity.Seat;
import com.showtime.mapper.EventSessionMapper;
import com.showtime.mapper.SeatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSessionService {

    private final EventSessionMapper sessionMapper;
    private final SeatMapper seatMapper;

    public List<EventSession> listByEvent(Integer eventId) {
        LambdaQueryWrapper<EventSession> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EventSession::getEventId, eventId)
               .orderByAsc(EventSession::getSessionTime);
        return sessionMapper.selectList(wrapper);
    }

    @Transactional
    public EventSession create(Integer eventId, AdminSessionRequest req) {
        EventSession session = new EventSession();
        session.setEventId(eventId);
        session.setSessionTime(req.getSessionTime());
        session.setStatus(req.getStatus() != null ? req.getStatus() : 1);
        sessionMapper.insert(session);

        String[] rowLabels = {"A","B","C","D","E","F","G","H","I","J"};
        for (var zone : req.getZones()) {
            for (int r = 0; r < zone.getRows(); r++) {
                for (int c = 1; c <= zone.getCols(); c++) {
                    Seat seat = new Seat();
                    seat.setSessionId(session.getId());
                    seat.setZoneName(zone.getName());
                    seat.setSeatRow(rowLabels[r]);
                    seat.setSeatCol(c);
                    seat.setPrice(zone.getPrice());
                    seat.setStatus("available");
                    seatMapper.insert(seat);
                }
            }
        }
        return session;
    }

    public EventSession update(Integer id, AdminSessionRequest req) {
        EventSession session = sessionMapper.selectById(id);
        if (session == null) throw new BizException(ResultCode.NOT_FOUND, "场次不存在");
        session.setSessionTime(req.getSessionTime());
        session.setStatus(req.getStatus());
        sessionMapper.updateById(session);
        return session;
    }

    @Transactional
    public void delete(Integer id) {
        seatMapper.delete(new LambdaQueryWrapper<Seat>().eq(Seat::getSessionId, id));
        sessionMapper.deleteById(id);
    }
}
