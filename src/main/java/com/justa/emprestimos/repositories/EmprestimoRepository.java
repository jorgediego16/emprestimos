package com.justa.emprestimos.repositories;

import com.justa.emprestimos.models.Emprestimo;
import com.justa.emprestimos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository <Emprestimo, Long> {

    /**
     * Get loans by id status
     * @param idStatus
     * @return List<Emprestimo>
     */
    @Query("SELECT e FROM Emprestimo e WHERE e.statusEmprestimo = :idStatus")
    List<Emprestimo> getLoanByStatus (@Param("idStatus") Integer idStatus);
}
