package com.mjc.school.service.implementation;

import com.mjc.school.repository.CommentRepository;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.CommentModel;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.CommentModelDto;
import com.mjc.school.service.dto.CommentRequestDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
		private final CommentRepository commentRepository;
		private final CommentMapper commentMapper;
		private final NewsRepository newsRepository;
		@Override
		public List<CommentModelDto> readAll() {
				return commentRepository.readAll().stream().map(commentMapper::commentToCommentDto).toList();
		}

		@Override
		public CommentModelDto readById(Long id) {
				return commentMapper.commentToCommentDto(commentRepository
								.readById(id)
								.orElseThrow(() -> new NoSuchEntityException("No comment with such id!")));
		}

		@Override
		public CommentModelDto create(CommentRequestDto createRequest) {
				createRequest.setCreated(LocalDateTime.now());
				createRequest.setModified(LocalDateTime.now());
				CommentModel commentToBeSaved = commentMapper.commentRequestToComment(createRequest);
				commentToBeSaved.setNewsModel(newsRepository.readById(createRequest.getNewsId()).orElseThrow());
				return commentMapper.commentToCommentDto(commentRepository.create(commentToBeSaved));
		}

		@Override
		public CommentModelDto update(CommentRequestDto updateRequest) {
				CommentModel commentToBeUpdated = commentRepository.readById(updateRequest.getId()).orElseThrow();
				commentToBeUpdated.setContent(updateRequest.getContent());
				commentToBeUpdated.setModified(LocalDateTime.now());
				return commentMapper.commentToCommentDto(commentRepository.update(commentToBeUpdated));
		}

		@Override
		public boolean deleteById(Long id) {
				return commentRepository.deleteById(id);
		}

		@Override
		public List<CommentModelDto> readByNewsId(Long newsId) {
				return commentRepository.readByNewsId(newsId).stream().map(commentMapper::commentToCommentDto).toList();
		}
}
