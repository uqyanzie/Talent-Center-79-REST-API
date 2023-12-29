package com.tujuhsembilan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.app.dto.SuccessResponse;
import com.tujuhsembilan.app.dto.skillset.PopularTagsResponse;
import com.tujuhsembilan.app.dto.skillset.SearchTagsResponse;
import com.tujuhsembilan.app.dto.skillset.SkillsetResponse;
import com.tujuhsembilan.app.dto.skillset.UpdateTagsCounterRequest;
import com.tujuhsembilan.app.service.SkillsetService;

import io.jsonwebtoken.lang.Objects;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class SkillSetController {

    @Autowired
    private SkillsetService skillSetService;

    @GetMapping("/api/tags-management/popular-tags-option-lists")
    public ResponseEntity<SuccessResponse<List<PopularTagsResponse>>> getTop5PopularTagsList() {
        try{
            List<PopularTagsResponse> response = skillSetService.getTop5PopularTags();

            return ResponseEntity.<SuccessResponse<List<PopularTagsResponse>>>ok(
                SuccessResponse.<List<PopularTagsResponse>>builder()
                .message(String.format("Retrieved total of %d popular tags", response.size()))
                .status(HttpStatus.OK)
                .data(response)
                .build()
            );
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
        }     
    }
    
    @GetMapping("/api/tags-management/tags-option-lists")
    public ResponseEntity<SuccessResponse<List<SearchTagsResponse>>> searchAutoCompleteTags(@RequestParam String tagsName) {
        try{

            List<SearchTagsResponse> response = skillSetService.searchAutoCompleteTagsByName(tagsName);

            return ResponseEntity.<SuccessResponse<List<SearchTagsResponse>>>ok(
                SuccessResponse.<List<SearchTagsResponse>>builder()
                .message(String.format("Retrieved total of %d tags", response.size()))
                .status(HttpStatus.OK)
                .data(response)
                .build()
            );
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
        }     
    }

    @PutMapping("/api/tags-management/tags")
    public ResponseEntity<?> updateTagsCounter(@RequestBody UpdateTagsCounterRequest request) {
        
        try{
            skillSetService.updateTagsCounter(request);

            return ResponseEntity.ok(
                SuccessResponse.builder()
                .message(String.format("Total of %d Tags updated", request.getTags().size()))
                .status(HttpStatus.OK)     
                .build()
            );
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
        }
    }
    

    @GetMapping("/api/master-management/skill-set-option-lists")
    public ResponseEntity<SuccessResponse<List<SkillsetResponse>>> getSkillsetOptionList(@RequestParam String type) {
        try{

            List<SkillsetResponse> response = skillSetService.searchSkillsetBySkillsetTypeName(type);

            return ResponseEntity.<SuccessResponse<List<SkillsetResponse>>>ok(
                SuccessResponse.<List<SkillsetResponse>>builder()
                .message(String.format("Retrieved total of %d skillset", response.size()))
                .status(HttpStatus.OK)
                .data(response)
                .build()
            );
            
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
        }     
    }
}
