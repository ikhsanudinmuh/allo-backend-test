package com.allobank.allobackendtest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "partai")
public class Partai {
    @Id
    @Builder.Default
    @Column(nullable = false, unique = true)
    private UUID id = UUID.randomUUID();

    @Column(name = "nama_partai", nullable = false)
    private String namaPartai;

    @Column(name = "nomor_urut", nullable = false)
    private Integer nomorUrut;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted_at", columnDefinition = "timestamp")
    private LocalDateTime deletedAt;
}
