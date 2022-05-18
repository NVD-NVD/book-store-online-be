package com.ute.bookstoreonlinebe.models.embadded;

import com.ute.bookstoreonlinebe.utils.EnumLanguage;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmbeddedDescription {
    private String language;

    private String description;

}
