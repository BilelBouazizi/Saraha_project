package com.bouaziz.saraha.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {

    @Id
    @GeneratedValue()
    private Integer id;
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createDate;
    private String content;
    private boolean favori;
    private boolean publicMsg;
private String typeMsg;
@ManyToOne
@JoinColumn(name="senderId")
private User sender;
    @ManyToOne
    @JoinColumn(name="receiverId")
private User receiver;



}
