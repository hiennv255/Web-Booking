package com.vti.bookinghospitalbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Position {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "position_sequence";

    private String positionName;

}
