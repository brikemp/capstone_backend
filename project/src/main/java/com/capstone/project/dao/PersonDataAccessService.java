package com.capstone.project.dao;

import com.capstone.project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
//    public int insertPerson(UUID id, Person person) {
//        return 0;
//    }
    public int insertPerson(UUID id, Person person) {
        String sql = "INSERT INTO person (id, name, twitter_name, spotify_name, pinterest_name) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id, person.getName(), person.getTwitterName(), person.getSpotifyName(), person.getPinterestName());
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name, twitter_name, spotify_name, pinterest_name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String twitterName = resultSet.getString("twitter_name");
            String spotifyName = resultSet.getString("spotify_name");
            String pinterestName = resultSet.getString("pinterest_name");
            return new Person(id, name, twitterName, spotifyName, pinterestName);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name, twitter_name, spotify_name, pinterest_name FROM person WHERE id = ?";

        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String twitterName = resultSet.getString("twitter_name");
                    String spotifyName = resultSet.getString("spotify_name");
                    String pinterestName = resultSet.getString("pinterest_name");
                    return new Person(id, name, twitterName, spotifyName, pinterestName);
                });
        return Optional.ofNullable(person);
    }

    @Override
//    public int deletePersonById(UUID id) {
//        return 0;
//    }
    public int deletePersonById(UUID id) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

        @Override
    public int updatePersonById(UUID id, Person person) {
        String sql = "UPDATE person SET name = ?, twitter_name = ?, spotify_name = ?, pinterest_name = ? WHERE id = ?";
            return jdbcTemplate.update(sql, person.getName(), person.getTwitterName(), person.getSpotifyName(), person.getPinterestName(), id);
    }
//    UPDATE person SET name = 'Harry Potter' WHERE id = 'c86f5512-0222-4ef2-9712-6f850a96e6ce';

//    public int updateName(UUID id, String name) {
//            String sql = "" +
//                    "UPDATE person " +
//                    "SET name = ? " +
//                    "WHERE id = ?";
//            return jdbcTemplate.update(sql, name, id);
//        }
}
