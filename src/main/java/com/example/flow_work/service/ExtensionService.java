package com.example.flow_work.service;

import com.example.flow_work.entity.Extension;
import com.example.flow_work.repostiory.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtensionService {

    private final ExtensionRepository repo;
    private static final int MAX_CUSTOM = 200;
    private static final int MAX_LENGTH = 20;

    public List<Extension> getFixed() {
        return repo.findAllByFixedTrueOrderByExtAsc();
    }

    public List<Extension> getCustom() {
        return repo.findAllByFixedFalseOrderByExtAsc();
    }

    @Transactional
    public Extension addCustom(String ext) {
        if (ext == null) throw new IllegalArgumentException("extension is null");
        String clean = ext.trim().toLowerCase();
        if (clean.length() == 0 || clean.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("확장자 길이는 1~" + MAX_LENGTH + "자여야 합니다.");
        }
        long current = repo.countByFixedFalse();
        if (current >= MAX_CUSTOM) {
            throw new IllegalStateException("커스텀 확장자 수는 최대 " + MAX_CUSTOM + "개입니다.");
        }
        // 중복 체크
        if (repo.findByExt(clean).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 확장자입니다.");
        }
        Extension e = new Extension(clean, true, false); // 추가하면 기본적으로 차단됨
        return repo.save(e);
    }

    @Transactional
    public void deleteCustom(Long id) {
        repo.findById(id).ifPresent(e -> {
            if (!e.isFixed()) repo.delete(e);
            else throw new IllegalArgumentException("고정 확장자는 삭제할 수 없습니다.");
        });
    }

    @Transactional
    public void toggleFixed(Long id, boolean blocked) {
        repo.findById(id).ifPresent(e -> {
            if (e.isFixed()) {
                e.setBlocked(blocked);
                repo.save(e);
            } else {
                throw new IllegalArgumentException("요청한 항목은 고정 확장자가 아닙니다.");
            }
        });
    }
}
