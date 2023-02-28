package com.lingventa.openmeteo.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@Entity
@IdClass(LogsId.class)
@NoArgsConstructor
@AllArgsConstructor
public class Logs {

    @Id
    @Enumerated(EnumType.STRING)
    private LogType type;

    @Id
    private Instant date;

    private String query;
}
