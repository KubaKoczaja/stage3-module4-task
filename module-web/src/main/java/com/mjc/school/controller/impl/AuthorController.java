package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseRestController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.AuthorRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/authors", consumes = {"application/JSON"}, produces = {"application/JSON"})
public class AuthorController implements BaseRestController<AuthorRequestDto, AuthorModelDto, Long> {
		private final AuthorService authorService;

		@Override
		@GetMapping
		public ResponseEntity<List<AuthorModelDto>> readAllPagedAndSorted(@Min(1) @RequestParam int page,
																												@RequestParam(required = false, defaultValue = "10") int size,
																												@RequestParam(name = "sort_by", required = false, defaultValue = "name::asc") String sortBy) {

				return new ResponseEntity<>(authorService.readAllPagedAndSorted(page, size, sortBy), HttpStatus.OK);
		}
		@Override
		@GetMapping("/{id}")
		public ResponseEntity<AuthorModelDto> readById(@PathVariable Long id) {
				return new ResponseEntity<>(authorService.readById(id), HttpStatus.OK);
		}

		@Override
		@PostMapping("/create")
		public ResponseEntity<AuthorModelDto> create(@Valid @RequestBody AuthorRequestDto createRequest) {
				return new ResponseEntity<>(authorService.create(createRequest), HttpStatus.CREATED);
		}

		@Override
		@PutMapping("/update")
		public ResponseEntity<AuthorModelDto> update(@Valid @RequestBody AuthorRequestDto updateRequest) {
				return new ResponseEntity<>(authorService.update(updateRequest), HttpStatus.ACCEPTED);
		}

		@Override
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
				return new ResponseEntity<>(authorService.deleteById(id), HttpStatus.OK);
		}

		@GetMapping("/by-news/{newsId}")
		public ResponseEntity<AuthorModelDto> readByNewsId(@PathVariable Long newsId) {
				return new ResponseEntity<>(authorService.readByNewsId(newsId), HttpStatus.OK );
	}
}
