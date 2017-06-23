package br.com.helpme.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.helpme.modelo.PessoaFisica;

@ManagedBean
@ViewScoped
public class ClienteBean {

	
	private PessoaFisica pessoaFisica;
	
	
	
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	
	
	
	
	
}
