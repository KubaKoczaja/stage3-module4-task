package com.mjc.school.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseRestController<T, R, K> {
		ResponseEntity<List<R>> readAll();

		ResponseEntity<R> readById(K id);

		ResponseEntity<R> create(T createRequest);

		ResponseEntity<R> update(T updateRequest);

		boolean deleteById(K id);
}
