package com.showtime.vo;

import lombok.Data;

import java.util.List;

@Data
public class EventDetailVO {
    private Integer id;
    private String title;
    private String description;
    private String posterUrl;
    private Integer duration;
    private Integer categoryId;
    private String categoryName;
    private VenueInfoVO venue;
    private List<SessionVO> sessions;
}
