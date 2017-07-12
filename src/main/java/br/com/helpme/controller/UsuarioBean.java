package br.com.helpme.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;

import br.com.helpme.dao.UsuarioDao;
import br.com.helpme.modelo.Usuario;

@Named
@ApplicationScoped
public class UsuarioBean{

	@Inject
	private Usuario usuario;
	
	@Inject
	private UsuarioDao usuarioDao;

	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String findUsuaruioLogado(){
		Usuario usuarioLogado = null;
		if(getUsuario()!=null){
			usuarioLogado =	usuarioDao.findUsuarioByUsuario(getUsuario());
			setUsuarioLogado(usuarioLogado);
		}
		if(usuarioLogado== null){
			Messages.addGlobalInfo("Usuário ou senha invalido");;
			return "Login.xhtml";
		}else{
			return "MeusPedidos.xhtml";
		}
		
	}
	
	public boolean isExisteUsuárioLogado(){
		if(getUsuarioLogado()==null){
			return false;
		}else{
			return true;
		}
	}
	
	
	
}
