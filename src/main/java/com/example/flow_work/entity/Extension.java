package com.example.flow_work.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "extension")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "ext", length = 20, nullable = false)
    private String ext; // e.g. "exe" (without dot)

    @Column(name = "blocked", nullable = false)
    private boolean blocked; // checkbox state (true = 차단)

    @Column(name = "fixed", nullable = false)
    private boolean fixed; // fixed list vs custom (fixed = true : 고정 확장자)

    public Extension() {}

    public Extension(String ext, boolean blocked, boolean fixed) {
        this.ext = ext;
        this.blocked = blocked;
        this.fixed = fixed;
    }

}
