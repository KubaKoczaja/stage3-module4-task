package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseRestController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.AuthorRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorRestControllerImpl implements BaseRestController<AuthorRequestDto, AuthorModelDto, Long> {
		private final AuthorService authorService;
		@Override
		@GetMapping
		public ResponseEntity<List<AuthorModelDto>> readAll() {
				List<AuthorModelDto> authorModelDtos = authorService.readAll();
				return new ResponseEntity<>(authorModelDtos, HttpStatus.OK);
		}

		@Override
		public ResponseEntity<AuthorModelDto> readById(Long id) {
				return null;
		}

		@Override
		public ResponseEntity<AuthorModelDto> create(AuthorRequestDto createRequest) {
				return null;
		}

		@Override
		public ResponseEntity<AuthorModelDto> update(AuthorRequestDto updateRequest) {
				return null;
		}

		@Override
		public boolean deleteById(Long id) {
				return false;
		}
}
