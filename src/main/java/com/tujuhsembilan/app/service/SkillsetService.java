package com.tujuhsembilan.app.service;

import java.util.List;

import com.tujuhsembilan.app.dto.skillset.PopularTagsResponse;
import com.tujuhsembilan.app.dto.skillset.SearchTagsResponse;
import com.tujuhsembilan.app.dto.skillset.SkillsetResponse;
import com.tujuhsembilan.app.dto.skillset.UpdateTagsCounterRequest;

public interface SkillsetService {    

    List<PopularTagsResponse> getTop5PopularTags();

    List<SearchTagsResponse> searchAutoCompleteTagsByName(String skillsetName);

    void updateTagsCounter(UpdateTagsCounterRequest request);

    List<SkillsetResponse> searchSkillsetBySkillsetTypeName(String skillsetTypeName);
}
