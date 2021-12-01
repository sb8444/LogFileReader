package com.filereader.logfilereader.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogDbModel {
    @Getter @Setter
    @Id
    private String id;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String host;
    @Getter @Setter
    private Long duration;
    @Getter @Setter
    private Boolean alertFlag;
}
