package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseRestController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.dto.NewsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/news", consumes = {"application/JSON"}, produces = {"application/JSON"})
public class NewsController implements BaseRestController<NewsRequestDto, NewsModelDto, Long> {
		private final NewsService newsService;
		@Override
		@GetMapping
		public ResponseEntity<List<NewsModelDto>> readAllPagedAndSorted(@Min(1) @RequestParam int page,
																																			 @RequestParam(required = false, defaultValue = "10") int size,
																																			 @RequestParam(name = "sort_by", required = false, defaultValue = "title::asc") String sortBy) {

				return new ResponseEntity<>(newsService.readAllPagedAndSorted(page, size, sortBy), HttpStatus.OK);
		}

		@Override
		@GetMapping("/{id}")
		public ResponseEntity<NewsModelDto> readById(@PathVariable Long id) {
				return new ResponseEntity<>(newsService.readById(id), HttpStatus.OK);
		}

		@Override
		@PostMapping("/create")
		public ResponseEntity<NewsModelDto> create(@Valid @RequestBody NewsRequestDto createRequest) {
				return new ResponseEntity<>(newsService.create(createRequest), HttpStatus.CREATED);
		}

		@Override
		@PutMapping("/update")
		public ResponseEntity<NewsModelDto> update(@Valid @RequestBody NewsRequestDto updateRequest) {
				return new ResponseEntity<>(newsService.update(updateRequest), HttpStatus.ACCEPTED);
		}

		@Override
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
				return new ResponseEntity<>(newsService.deleteById(id), HttpStatus.OK);
		}

		@GetMapping("/by-various-parameters")
		public ResponseEntity<Set<NewsModelDto>> readNewsByVariousParameters(@RequestBody NewsRequestDto newsRequestDto) {
				return new ResponseEntity<>(newsService.readNewsByVariousParameters(newsRequestDto), HttpStatus.OK);
		}
}