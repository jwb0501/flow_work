package com.example.flow_work.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "extension", uniqueConstraints = {@UniqueConstraint(columnNames = {"ext"})})
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(length = 20, nullable = false, unique = true)
    private String ext; // e.g. "exe" (without dot)

    @Column(nullable = false)
    private boolean blocked; // checkbox state (true = 차단)

    @Column(nullable = false)
    private boolean fixed; // fixed list vs custom (fixed = true : 고정 확장자)

    public Extension() {}

    public Extension(String ext, boolean blocked, boolean fixed) {
        this.ext = ext;
        this.blocked = blocked;
        this.fixed = fixed;
    }

}
