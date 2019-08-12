package com.justa.emprestimos.repositories;

import com.justa.emprestimos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    /**
     * Return a user by username
     * @param username
     * @return Usuario
     */
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario getUser(@Param("username") String username);

}
