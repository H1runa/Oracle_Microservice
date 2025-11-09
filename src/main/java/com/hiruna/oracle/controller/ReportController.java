package com.hiruna.oracle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiruna.oracle.service.ReportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/report")
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }

    @GetMapping("/monthlyexpenditure")
    public ResponseEntity<?> getMonthlyExpenditure(@RequestParam Long id) {
        try{
            return ResponseEntity.ok(reportService.getMonthlyExpenditure(id));
        } catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
}
