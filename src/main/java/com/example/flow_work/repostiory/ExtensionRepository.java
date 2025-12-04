package com.example.flow_work.repostiory;

import com.example.flow_work.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    Optional<Extension> findByExt(String ext);
    List<Extension> findAllByFixedTrueOrderByExtAsc();
    List<Extension> findAllByFixedFalseOrderByExtAsc();
    long countByFixedFalse();
}
