package com.marketplace.repository;

import com.marketplace.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
     Optional<Media> findByName(String name);
}