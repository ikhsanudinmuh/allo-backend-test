package com.allobank.allobackendtest.controller;

import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.dto.PartaiReq;
import com.allobank.allobackendtest.model.Partai;
import com.allobank.allobackendtest.service.PartaiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/partai")
@Slf4j
@RequiredArgsConstructor
public class PartaiController {
    private final PartaiService partaiService;

    @PostMapping
    public ResponseEntity<DataRes<Partai>> createPartai(
            @Valid @RequestBody PartaiReq body
    ) {
        log.info("PartaiController | createPartai");
        DataRes<Partai> response = partaiService.createPartai(body);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{partaiId}")
    public ResponseEntity<DataRes<Partai>> updatePartai(
            @PathVariable("partaiId") UUID partaiId,
            @Valid @RequestBody PartaiReq body
    ) {
        log.info("PartaiController | updatePartai");
        DataRes<Partai> response = partaiService.updatePartai(partaiId, body);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/activate/{partaiId}")
    public ResponseEntity<DataRes<Partai>> activatePartai(
            @PathVariable("partaiId") UUID partaiId
    ) {
        log.info("PartaiController | activatePartai");
        DataRes<Partai> response = partaiService.activatePartai(partaiId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/deactivate/{partaiId}")
    public ResponseEntity<DataRes<Partai>> deactivatePartai(
            @PathVariable("partaiId") UUID partaiId
    ) {
        log.info("PartaiController | deactivatePartai");
        DataRes<Partai> response = partaiService.deactivatePartai(partaiId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{partaiId}")
    public ResponseEntity<DataRes<Partai>> getByPartaiId(
            @PathVariable("partaiId") UUID partaiId
    ) {
        log.info("PartaiController | getByPartaiId");
        DataRes<Partai> response = partaiService.getByPartaiId(partaiId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<PageRes<Partai>> getPagePartai(
            @RequestParam(name = "namaPartai", required = false) String namaPartai,
            @RequestParam(name = "nomorUrut", required = false) Integer nomorUrut,
            @RequestParam(name = "deleted", required = false) Boolean deleted,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        log.info("PartaiController | getPagePartai");
        PageRes<Partai> response = partaiService.getPagePartai(namaPartai, nomorUrut, deleted, page, pageSize);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
