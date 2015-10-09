package br.sc.supero.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.sc.supero.model.Pessoa;

@Stateless
public class PessoaDAO {

	@PersistenceContext(unitName = "SuperoConfig")
	private EntityManager em;

	public boolean save(Pessoa pessoa) {
		try {
			em.merge(pessoa);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Pessoa getPessoa(Integer codigo) {
		return em.find(Pessoa.class, codigo);
	}

	public boolean delete(Integer codigo) {
		try {
			em.remove(this.getPessoa(codigo));
			em.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> getList() {
		Query query = em.createQuery("Select c from Pessoa c");
		List<Pessoa> pessoas = query.getResultList();
		return pessoas;
	}
}
