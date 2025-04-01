package com.example.cinema.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cinema.dto.ReviewDTO;
import com.example.cinema.model.Review;
import com.example.cinema.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@Tag(name = "Отзывы", description = "Контроллер для работы с отзывами пользователей о фильмах")
public class ReviewRestController extends GenericRestController<Review, ReviewDTO> {

    public ReviewRestController(ReviewService reviewService) {
        service = reviewService;
    }
}
