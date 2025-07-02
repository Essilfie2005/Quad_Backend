package com.quad.backend.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.util.List;

@Embeddable
@Data
public class Poll {
    @ElementCollection
    private List<String> options;

    private int durationDays;
}