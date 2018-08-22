package com.prueba.clase.apiclase.utils;

public class ResponseMapping <T> {

	boolean error = true;
	String mensaje= "";
	T objeto;
	/**
	 * 
	 */
	public ResponseMapping() {
	}
	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the objeto
	 */
	public T getObjeto() {
		return objeto;
	}
	/**
	 * @param objeto the objeto to set
	 */
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}
	
	
}
