package com.bouaziz.saraha.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notification {
    @Id
    @GeneratedValue()
    private Integer id;
@Column(name="create_date")
    private LocalDateTime createDate;
    private String notification;
    private boolean consulted;
@ManyToOne
    @JoinColumn(name="userId")
    private User user;

}
