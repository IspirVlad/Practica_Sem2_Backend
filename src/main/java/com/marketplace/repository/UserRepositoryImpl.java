package com.marketplace.repository;

import com.marketplace.dto.AuthDto;
import com.marketplace.model.Product;
import com.marketplace.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<AuthDto> getByEmailWithPasswordAndRole(String email) {

        String query = "select new com.marketplace.dto.AuthDto(" +
                "u.id, u.email, u.password, u.role.role) " +
                "from User u " +
                "where u.email = :email ";
        try {
            AuthDto authDto =
                    entityManager.createQuery(query, AuthDto.class)
                            .setParameter("email", email)
                            .getSingleResult();
            return Optional.of(authDto);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public List<User> getPaginatedUsers(int firstResults, int maxResults) {
        String query = "select d from User u";
        List<User> users = entityManager.createQuery(query, User.class)
                .setFirstResult(firstResults)
                .setMaxResults(maxResults)
                .getResultList();
        return users;
    }

    @Override
    public long getTotalUsers() {
        String query = "select count(u) from User u";

        long totalUsers = entityManager.createQuery(query,Long.class)
                .getSingleResult();
        return totalUsers;
    }
}
