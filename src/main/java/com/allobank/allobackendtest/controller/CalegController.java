package com.allobank.allobackendtest.controller;

import com.allobank.allobackendtest.dto.CalegReq;
import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.model.Caleg;
import com.allobank.allobackendtest.service.CalegService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/caleg")
@Slf4j
@RequiredArgsConstructor
public class CalegController {
    private final CalegService calegService;

    @PostMapping
    public ResponseEntity<DataRes<Caleg>> createCaleg(
            @Valid @RequestBody CalegReq body
    ) {
        log.info("CalegController | createCaleg");
        DataRes<Caleg> response = calegService.createCaleg(body);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{calegId}")
    public ResponseEntity<DataRes<Caleg>> updateCaleg(
            @PathVariable("calegId") UUID calegId,
            @Valid @RequestBody CalegReq body
    ) {
        log.info("CalegController | updateCaleg");
        DataRes<Caleg> response = calegService.updateCaleg(calegId, body);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/activate/{calegId}")
    public ResponseEntity<DataRes<Caleg>> activateCaleg(
            @PathVariable("calegId") UUID calegId
    ) {
        log.info("CalegController | activateCaleg");
        DataRes<Caleg> response = calegService.activateCaleg(calegId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/deactivate/{calegId}")
    public ResponseEntity<DataRes<Caleg>> deactivateCaleg(
            @PathVariable("calegId") UUID calegId
    ) {
        log.info("CalegController | deactivateCaleg");
        DataRes<Caleg> response = calegService.deactivateCaleg(calegId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{calegId}")
    public ResponseEntity<DataRes<Caleg>> getByCalegId(
            @PathVariable("calegId") UUID calegId
    ) {
        log.info("CalegController | getByCalegId");
        DataRes<Caleg> response = calegService.getByCalegId(calegId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<PageRes<Caleg>> getPageCaleg(
            @RequestParam(name = "nama", required = false) String nama,
            @RequestParam(name = "nomorUrut", required = false) Integer nomorUrut,
            @RequestParam(name = "jenisKelamin", required = false) String jenisKelamin,
            @RequestParam(name = "dapilId", required = false) UUID dapilId,
            @RequestParam(name = "partaiId", required = false) UUID partaiId,
            @RequestParam(name = "deleted", required = false) Boolean deleted,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "nomor_urut") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder
    ) {
        log.info("CalegController | getPageCaleg");
        PageRes<Caleg> response = calegService.getPageCaleg(nama, nomorUrut, jenisKelamin, dapilId, partaiId, deleted, page, pageSize, sortBy, sortOrder);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
