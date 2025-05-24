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
@Table(name = "caleg")
public class Caleg {
    @Id
    @Builder.Default
    @Column(nullable = false, unique = true)
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "dapil_id", referencedColumnName = "id")
    private Dapil dapil;

    @ManyToOne
    @JoinColumn(name = "partai_id", referencedColumnName = "id")
    private Partai partai;

    @Column(name = "nomor_urut", nullable = false)
    private Integer nomorUrut;

    @Column(nullable = false)
    private String nama;

    @Enumerated(EnumType.STRING)
    @Column(name = "jenis_kelamin", nullable = false, columnDefinition = "varchar")
    private JenisKelamin jenisKelamin;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted_at", columnDefinition = "timestamp")
    private LocalDateTime deletedAt;
}
