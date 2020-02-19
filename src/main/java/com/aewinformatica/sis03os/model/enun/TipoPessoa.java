package com.aewinformatica.sis03os.model.enun;

public enum TipoPessoa {


	FISICA("Física"), 
	JURIDICA("Jurídica");
	
	private String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
