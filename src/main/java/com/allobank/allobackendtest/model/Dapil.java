package com.allobank.allobackendtest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dapil")
public class Dapil {
    @Id
    @Builder.Default
    @Column(nullable = false, unique = true)
    private UUID id = UUID.randomUUID();

    @Column(name = "nama_dapil", nullable = false)
    private String namaDapil;

    @Column(name = "provinsi", nullable = false)
    private String provinsi;

    @Column(name = "wilayah_dapil_list", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> wilayahDapilList;

    @Column(name = "jumlah_kursi", nullable = false)
    private int jumlahKursi;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted_at", columnDefinition = "timestamp")
    private LocalDateTime deletedAt;
}
