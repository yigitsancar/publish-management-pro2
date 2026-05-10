package com.yigit.publish.repository;

import com.yigit.publish.entity.Publish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishRepository extends JpaRepository<Publish, Long> {
}