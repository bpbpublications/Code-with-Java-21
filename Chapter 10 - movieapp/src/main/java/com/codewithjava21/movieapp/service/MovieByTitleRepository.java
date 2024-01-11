package com.codewithjava21.movieapp.service;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieByTitleRepository  extends CassandraRepository<MovieByTitle,String> {

}
