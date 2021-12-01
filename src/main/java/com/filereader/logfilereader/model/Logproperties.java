package com.filereader.logfilereader.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Logproperties {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String state;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String host;
    @Getter @Setter
    private Long timestamp;
}
