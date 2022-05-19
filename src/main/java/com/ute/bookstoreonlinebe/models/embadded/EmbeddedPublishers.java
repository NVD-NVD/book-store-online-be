package com.ute.bookstoreonlinebe.models.embadded;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmbeddedPublishers {
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
}
