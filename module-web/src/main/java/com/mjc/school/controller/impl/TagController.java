package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseRestController;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagModelDto;
import com.mjc.school.service.dto.TagRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tags", consumes = {"application/JSON"}, produces = {"application/JSON"})
public class TagController implements BaseRestController<TagRequestDto, TagModelDto, Long> {
		private final TagService tagService;
		@Override
		@GetMapping
		public ResponseEntity<List<TagModelDto>> readAllPagedAndSorted(@Min(1) @RequestParam int page,
																												@RequestParam(required = false, defaultValue = "10") int size,
																												@RequestParam(name = "sort_by", required = false, defaultValue = "name::asc") String sortBy) {

				return new ResponseEntity<>(tagService.readAllPagedAndSorted(page, size, sortBy), HttpStatus.OK);
		}

		@Override
		@GetMapping("/{id}")
		public ResponseEntity<TagModelDto> readById(@PathVariable Long id) {
				return new ResponseEntity<>(tagService.readById(id), HttpStatus.OK);
		}

		@Override
		@PostMapping("/create")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<TagModelDto> create(@Valid @RequestBody TagRequestDto createRequest) {
				return new ResponseEntity<>(tagService.create(createRequest), HttpStatus.CREATED);
		}

		@Override
		@PutMapping("/update/{id}")
		public ResponseEntity<TagModelDto> update(@PathVariable Long id, @Valid @RequestBody TagRequestDto updateRequest) {
				updateRequest.setId(id);
				return new ResponseEntity<>(tagService.update(updateRequest), HttpStatus.ACCEPTED);
		}

		@Override
		@DeleteMapping("/delete/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteById(@PathVariable Long id) {
				tagService.deleteById(id);
		}

		@GetMapping("/by-news/{newsId}")
		public ResponseEntity<List<TagModelDto>> readByNewsId(@PathVariable Long newsId) {
				return new ResponseEntity<>(tagService.readByNewsId(newsId), HttpStatus.OK);
		}
}
