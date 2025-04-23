package com.A4Team.GamesShop.controller;

import com.A4Team.GamesShop.dto.CommentDTO;
import com.A4Team.GamesShop.model.response.BaseResponse;
import com.A4Team.GamesShop.services.comments.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public BaseResponse<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.findAllDTO();
        return BaseResponse.success(comments, "Fetched all comments successfully");
    }

    @GetMapping("/limit")
    public BaseResponse<List<CommentDTO>> getLimitedComments(@RequestParam(defaultValue = "10") int limit) {
        List<CommentDTO> comments = commentService.findLimitedDTO(limit);
        return BaseResponse.success(comments, "Fetched comments with limit " + limit);
    }

    @GetMapping("/{id}")
    public BaseResponse<CommentDTO> getCommentById(@PathVariable int id) {
        CommentDTO comment = commentService.findByIdDTO(id);
        if (comment == null) {
            return BaseResponse.error(
                    HttpStatus.NOT_FOUND,
                    "Comment not found",
                    List.of("No comment with ID = " + id));
        }
        return BaseResponse.success(comment, "Fetched comment successfully");
    }

    @GetMapping("/by-game")
    public BaseResponse<List<CommentDTO>> getCommentsByGameId(@RequestParam int gameId) {
        List<CommentDTO> comments = commentService.findByGameIdDTO(gameId);
        return BaseResponse.success(comments, "Fetched comments for gameId = " + gameId);
    }
}