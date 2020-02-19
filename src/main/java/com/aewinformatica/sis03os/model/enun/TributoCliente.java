package com.aewinformatica.sis03os.model.enun;

public enum TributoCliente {

	ICMS("Contribuinte ICMS"), 
	RURAL("Produtor Rural"), 
	FINAL("Consumidor final");
	
	private String descricao;

	TributoCliente(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}