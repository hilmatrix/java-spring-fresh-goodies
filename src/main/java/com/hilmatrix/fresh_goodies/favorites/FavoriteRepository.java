package com.hilmatrix.fresh_goodies.favorites;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, String> {

}