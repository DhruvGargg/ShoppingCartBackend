package com.app.shoppingcartbackend.repository.ImageRepository;

import com.app.shoppingcartbackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
