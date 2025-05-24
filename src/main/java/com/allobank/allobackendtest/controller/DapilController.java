package com.allobank.allobackendtest.controller;

import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.dto.DapilReq;
import com.allobank.allobackendtest.model.Dapil;
import com.allobank.allobackendtest.service.DapilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dapil")
@Slf4j
@RequiredArgsConstructor
public class DapilController {
    private final DapilService dapilService;

    @PostMapping
    public ResponseEntity<DataRes<Dapil>> createDapil(
            @Valid @RequestBody DapilReq body
    ) {
        log.info("DapilController | createDapil");
        DataRes<Dapil> response = dapilService.createDapil(body);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{dapilId}")
    public ResponseEntity<DataRes<Dapil>> updateDapil(
            @PathVariable("dapilId") UUID dapilId,
            @Valid @RequestBody DapilReq body
    ) {
        log.info("DapilController | updateDapil");
        DataRes<Dapil> response = dapilService.updateDapil(dapilId, body);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/activate/{dapilId}")
    public ResponseEntity<DataRes<Dapil>> activateDapil(
            @PathVariable("dapilId") UUID dapilId
    ) {
        log.info("DapilController | activateDapil");
        DataRes<Dapil> response = dapilService.activateDapil(dapilId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/deactivate/{dapilId}")
    public ResponseEntity<DataRes<Dapil>> deactivateDapil(
            @PathVariable("dapilId") UUID dapilId
    ) {
        log.info("DapilController | deactivateDapil");
        DataRes<Dapil> response = dapilService.deactivateDapil(dapilId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{dapilId}")
    public ResponseEntity<DataRes<Dapil>> getByDapilId(
            @PathVariable("dapilId") UUID dapilId
    ) {
        log.info("DapilController | getByDapilId");
        DataRes<Dapil> response = dapilService.getByDapilId(dapilId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<PageRes<Dapil>> getPageDapil(
            @RequestParam(name = "namaDapil", required = false) String namaDapil,
            @RequestParam(name = "provinsi", required = false) String provinsi,
            @RequestParam(name = "jumlahKursi", required = false) Integer jumlahKursi,
            @RequestParam(name = "deleted", required = false) Boolean deleted,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        log.info("DapilController | getPageDapil");
        PageRes<Dapil> response = dapilService.getPageDapil(namaDapil, provinsi, jumlahKursi, deleted, page, pageSize);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
