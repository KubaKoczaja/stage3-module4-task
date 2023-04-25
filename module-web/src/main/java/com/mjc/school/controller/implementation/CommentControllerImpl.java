package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.CommandBody;
import com.mjc.school.controller.CommandHandler;
import com.mjc.school.controller.CommandParam;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.CommentModelDto;
import com.mjc.school.service.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentControllerImpl implements BaseController<CommentRequestDto, CommentModelDto, Long> {
		private final CommentService commentService;
		@Override
		@CommandHandler(id = 19)
		public List<CommentModelDto> readAll() {
				return commentService.readAll();
		}

		@Override
		@CommandHandler(id = 20)
		public CommentModelDto readById(@CommandParam Long id) {
				return commentService.readById(id);
		}

		@Override
		@CommandHandler(id = 21)
		public CommentModelDto create(@CommandBody CommentRequestDto createRequest) {
				return commentService.create(createRequest);
		}

		@Override
		@CommandHandler(id = 22)
		public CommentModelDto update(@CommandBody CommentRequestDto updateRequest) {
				return commentService.update(updateRequest);
		}

		@Override
		@CommandHandler(id = 23)
		public boolean deleteById(@CommandParam Long id) {
				return commentService.deleteById(id);
		}
		@CommandHandler(id = 24)
		public List<CommentModelDto> readByNewsId(@CommandParam Long newsId) {
				return commentService.readByNewsId(newsId);
		}
}
