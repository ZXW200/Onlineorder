package org.partssale.domain.dto;

import java.sql.Timestamp;

public class Feedback {
    private Long id;
    private Long userId;
    private Long orderId;
    private String comment;
    private Integer rating;
    private Timestamp feedbackTime;

    // Getters and setters
}
