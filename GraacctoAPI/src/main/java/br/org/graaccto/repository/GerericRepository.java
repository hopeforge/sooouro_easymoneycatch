package br.org.graaccto.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.web.PageableDefault;

import br.org.graaccto.model.entidades.Entidade;

@NoRepositoryBean
public interface GerericRepository <T extends Entidade> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>{

	Page<T> findAll(@PageableDefault(value = 20) Pageable arg0);

	List<T> findAll(Specification<T> spec);

}
