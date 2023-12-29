package com.tujuhsembilan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.app.dto.SuccessResponse;
import com.tujuhsembilan.app.dto.talent.PositionResponse;
import com.tujuhsembilan.app.dto.talent.TalentLevelResponse;
import com.tujuhsembilan.app.dto.talent.TalentYOEResponse;
import com.tujuhsembilan.app.service.TalentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TalentController {
    
    @Autowired
    private TalentService talentService;

    @GetMapping("/api/master-management/talent-position-option-lists")
    public ResponseEntity<SuccessResponse<List<PositionResponse>>> getTalentPositionList() {
        try{
            List<PositionResponse> response = talentService.getPositionList();
            
            return ResponseEntity.<SuccessResponse<List<PositionResponse>>>ok(
                SuccessResponse.<List<PositionResponse>>builder()
                    .message(String.format("Retrieved total of %d talent position", response.size()))
                    .status(HttpStatus.OK)
                    .data(response)
                    .build()
            );
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
        }
    }
    
    @GetMapping("/api/master-management/year-experience-option-lists")
    public ResponseEntity<SuccessResponse<List<TalentYOEResponse>>> getTalentYOEList() {
        try{
            List<TalentYOEResponse> response = talentService.getTalentYOEList();

            return ResponseEntity.<SuccessResponse<List<TalentYOEResponse>>>ok(
                SuccessResponse.<List<TalentYOEResponse>>builder()
                .message(String.format("Retrieved total of %d distinct data", response.size()))
                .status(HttpStatus.OK)
                .data(response)
                .build()
            );
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex);
        }
    }
    

    @GetMapping("/api/master-management/talent-level-option-lists") 
    public ResponseEntity<SuccessResponse<List<TalentLevelResponse>>> getTalentLevelList(){

        try{
            List<TalentLevelResponse> response = talentService.getTalentLevelList();

            return ResponseEntity.<SuccessResponse<List<TalentLevelResponse>>>ok(
                SuccessResponse.<List<TalentLevelResponse>>builder()
                    .message(String.format("Retrieved total of %d talent level", response.size()))
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
