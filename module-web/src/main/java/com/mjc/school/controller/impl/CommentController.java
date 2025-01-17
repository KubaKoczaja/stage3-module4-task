package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseRestController;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.CommentModelDto;
import com.mjc.school.service.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments", consumes = {"application/JSON"}, produces = {"application/JSON"})
public class CommentController implements BaseRestController<CommentRequestDto, CommentModelDto, Long> {
		private final CommentService commentService;
		@Override
		@GetMapping
		public ResponseEntity<List<CommentModelDto>> readAllPagedAndSorted(@Min(1) @RequestParam int page,
																																	 @RequestParam(required = false, defaultValue = "10") int size,
																																	 @RequestParam(name = "sort_by", required = false, defaultValue = "content::asc") String sortBy) {

				return new ResponseEntity<>(commentService.readAllPagedAndSorted(page, size, sortBy), HttpStatus.OK);
		}

		@Override
		@GetMapping("/{id}")
		public ResponseEntity<CommentModelDto> readById(@PathVariable Long id) {
				return new ResponseEntity<>(commentService.readById(id), HttpStatus.OK);
		}

		@Override
		@PostMapping("/create")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<CommentModelDto> create(@Valid @RequestBody CommentRequestDto createRequest) {
				return new ResponseEntity<>(commentService.create(createRequest), HttpStatus.CREATED);
		}

		@Override
		@PutMapping("/update/{id}")
		public ResponseEntity<CommentModelDto> update(@PathVariable Long id, @Valid @RequestBody CommentRequestDto updateRequest) {
				updateRequest.setId(id);
				return new ResponseEntity<>(commentService.update(updateRequest), HttpStatus.ACCEPTED);
		}

		@Override
		@DeleteMapping("/delete/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteById(@PathVariable Long id) {
				commentService.deleteById(id);
		}
		@GetMapping("/by-news/{newsId}")
		public ResponseEntity<List<CommentModelDto>> readByNewsId(@PathVariable Long newsId) {
				return new ResponseEntity<>(commentService.readByNewsId(newsId), HttpStatus.OK);
		}
}
