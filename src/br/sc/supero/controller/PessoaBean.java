package br.sc.supero.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.sc.supero.dao.PessoaDAO;
import br.sc.supero.model.Pessoa;

/**
 * @author Andre Guilherme
 * 
 */
@ManagedBean(name = "pessoaBean")
@SessionScoped
public class PessoaBean implements Serializable {

	// @EJB: Essa anotação serve para fazermos a injeção de dependência do EJB
	// (PessoasDAO),

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private PessoaDAO pessoaDAO;
	private Pessoa pessoa = new Pessoa();
	private Integer codigo;

	public void novo() {
		pessoa = new Pessoa();
	}

	public String save() {
		if (this.codigo != null) {
			pessoa.setCodigo(codigo);
		}

		if (pessoaDAO.save(pessoa)) {
			pessoa = new Pessoa();
			return "listaPessoas";
		} else {
			return "";
		}
	}

	public void delete() {
		boolean result = pessoaDAO.delete(this.codigo);
		if (result) {
			pessoa = new Pessoa();
		}
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}
		if (this.codigo == null) {
			this.pessoa = new Pessoa();
		} else {
			this.pessoa = pessoaDAO.getPessoa(getCodigo());
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		pessoaDAO.getList();
		return pessoaDAO.getList();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
}
