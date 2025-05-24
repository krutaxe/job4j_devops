package ru.job4j.devops.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "calc_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalcEvents {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    private Integer first;
    private Integer second;
    private Integer result;

    @Column(name = "create_date")
    private LocalDate createDate;

    private String type;
}
