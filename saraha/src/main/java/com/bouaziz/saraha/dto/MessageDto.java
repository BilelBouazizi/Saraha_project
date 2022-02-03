package com.bouaziz.saraha.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto implements Serializable {
    private Integer id;
    private boolean publicMsg;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime createdDate;
    @NotNull(message="you need to write the content")
    private String content;
    private boolean favori;
    private String typeMsg;
    private Integer senderId;
    @NotNull(message="you need to write the id receiver")
    private Integer receiverId;
}
