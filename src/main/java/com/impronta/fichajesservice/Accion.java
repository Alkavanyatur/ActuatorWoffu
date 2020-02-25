package com.impronta.fichajesservice;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Accion implements Serializable {

	private static final long serialVersionUID = -4718172128693031428L;

	private String accion;

}
