package com.A4Team.GamesShop.services.comments;

import com.A4Team.GamesShop.dto.CommentDTO;
import com.A4Team.GamesShop.mapper.CommentMapper;
import com.A4Team.GamesShop.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // @Cacheable("allComments")
    public List<CommentDTO> findAllDTO() {
        return commentRepository.findAll().stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // @Cacheable(value = "limitedComments", key = "#limit")
    public List<CommentDTO> findLimitedDTO(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return commentRepository.findAll(pageable).getContent().stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO findByIdDTO(int id) {
        return commentRepository.findById(id)
                .map(CommentMapper::toDTO)
                .orElse(null);
    }

    public List<CommentDTO> findByGameIdDTO(Integer gameId) {
        return commentRepository.findByGameId(gameId).stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
    }
}