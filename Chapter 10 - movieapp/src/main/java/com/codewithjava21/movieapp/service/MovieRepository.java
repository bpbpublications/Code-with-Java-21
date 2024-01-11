package com.codewithjava21.movieapp.service;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.data.CqlVector;

@Repository
public interface MovieRepository extends CassandraRepository<Movie,Integer> {

	@Query("SELECT * FROM movies ORDER BY movie_vector ANN OF ?0 LIMIT 6")
	List<Movie> findMoviesByVector(CqlVector<Float> vector);
}
