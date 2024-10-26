package com.hilmatrix.fresh_goodies.favorites;

import com.hilmatrix.fresh_goodies.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping
    public ApiResponse<List<FavoriteEntity>> getFavoriteItems() throws IOException {
        return ApiResponse.Success(
                FavoriteConstants.MESSAGE_SUCCESS_GET_ALL,
                favoriteRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<FavoriteEntity> getFavoriteItem(@PathVariable int id) {
        if (!favoriteRepository.existsById(String.valueOf(id)))
            return new ApiResponse<>().NotFound(FavoriteConstants.MESSAGE_FAILED_NOT_FOUND, null);

        return  ApiResponse.Success(
                FavoriteConstants.MESSAGE_SUCCESS_GET,
                favoriteRepository.getById(String.valueOf(id))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<FavoriteEntity> createFavoriteItem(@RequestBody FavoriteEntity favoriteItem) {
        if (favoriteRepository.existsById(String.valueOf(favoriteItem.getId())))
            return ApiResponse.Conflict(
                    FavoriteConstants.MESSAGE_FAILED_ALREADY_EXIST + " = " + favoriteItem.getId(),
                    favoriteItem);

        FavoriteEntity createdFavoriteItem = favoriteRepository.save(favoriteItem);

        return  ApiResponse.Success(
                FavoriteConstants.MESSAGE_SUCCESS_CREATE,
                createdFavoriteItem
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<FavoriteEntity> updateFavoriteItem(@RequestBody FavoriteEntity updatedFavoriteItem) {
        String id = updatedFavoriteItem.getId();

        if (!favoriteRepository.existsById(id))
            return ApiResponse.NotFound(FavoriteConstants.MESSAGE_FAILED_NOT_FOUND + " = " + id, null);

        FavoriteEntity favoriteFromDB = favoriteRepository.getById(id);
        favoriteFromDB.copyFrom(updatedFavoriteItem);
        FavoriteEntity savedFavoriteItem = favoriteRepository.save(favoriteFromDB);

        return ApiResponse.Success(
                FavoriteConstants.MESSAGE_SUCCESS_UPDATE,
                savedFavoriteItem
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteFavoriteItem(@PathVariable String id) {
        if (!favoriteRepository.existsById(id))
            return ApiResponse.NotFound(FavoriteConstants.MESSAGE_FAILED_NOT_FOUND + " = " + id, null);

        FavoriteEntity favoriteItem = favoriteRepository.getById(id);
        favoriteRepository.delete(favoriteItem);

        return ApiResponse.Success(FavoriteConstants.MESSAGE_SUCCESS_DELETED + " = " + id, null);
    }
}
