package com.justa.emprestimos.repositories;

import com.justa.emprestimos.models.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, String> {

    /**
     * Returns a list of files according to loan id
     * @param idEmprestimo
     * @return List<Arquivo>
     */
    @Query("SELECT a FROM Arquivo a WHERE a.emprestimo.id = :idEmprestimo")
    List<Arquivo> getFilesLoan (@Param("idEmprestimo") Long idEmprestimo);

    /**
     * Returns a list of files according to user id
     * @param idUsuario
     * @return List<Arquivo>
     */
    @Query("SELECT a FROM Arquivo a WHERE a.usuario.id = :idUsuario")
    List<Arquivo> getFilesUser (@Param("idUsuario") Long idUsuario);
}
