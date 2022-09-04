package com.springbootrabbit.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Data
public class Notification implements Serializable {
    private Long notificationId;
    private String message;
    private boolean readState;
    private Date createdAt;
}
