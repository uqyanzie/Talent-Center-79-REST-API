package com.tujuhsembilan.app.repository.specs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchCriteria {
        
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria() {
    }
}
