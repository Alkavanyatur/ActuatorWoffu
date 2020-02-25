package com.impronta.fichajesservice;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado implements Serializable {

	private static final long serialVersionUID = -2311343888791074940L;

	private String nombre;
	private String apellidos;
	private String token;
	private String bearer;
	private String id_Empleado_Woffu;

}
