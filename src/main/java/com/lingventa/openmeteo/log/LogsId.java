package com.lingventa.openmeteo.log;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
public class LogsId implements Serializable {

    private LogType type;
    private Instant date;
}
