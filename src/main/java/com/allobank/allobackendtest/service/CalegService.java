package com.allobank.allobackendtest.service;

import com.allobank.allobackendtest.dto.CalegReq;
import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.model.Caleg;

import java.util.UUID;

public interface CalegService {
    DataRes<Caleg> createCaleg(CalegReq body);

    DataRes<Caleg> updateCaleg(UUID calegId, CalegReq body);

    DataRes<Caleg> activateCaleg(UUID calegId);

    DataRes<Caleg> deactivateCaleg(UUID calegId);

    DataRes<Caleg> getByCalegId(UUID calegId);

    PageRes<Caleg> getPageCaleg(String nama, Integer nomorUrut, String jenisKelamin, UUID dapilId, UUID partaiId, Boolean deleted, Integer page, Integer pageSize, String sortBy, String sortOrder);
}
