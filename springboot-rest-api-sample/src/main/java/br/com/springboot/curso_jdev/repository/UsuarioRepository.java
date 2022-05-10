package br.com.springboot.curso_jdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso.jdev.model.Usuario;

//Repository sempre tem que ser uma Interface

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	//Implementando metodo para trazer por parte do nome
	//like%?1% somente 1 paramentro do metodo
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like%?1%")
	List<Usuario> buscarPorNome(String nome);
}
