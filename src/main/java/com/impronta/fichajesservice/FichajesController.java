package com.impronta.fichajesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class FichajesController {

	@Autowired
	private EmpleadoMapper empleadoMapper;

	private WoffuFichajeService woffuFichajeService = new WoffuFichajeServiceImpl();

	@GetMapping("/fichar/{codigo}")
	@ResponseBody
	public Accion fichar(@PathVariable String codigo) {
		Empleado empleado = empleadoMapper.findById(codigo);
		if (empleado != null) {

			String respuesta = woffuFichajeService.fichaje(empleado);

			return new Accion("Usuario: " + codigo + " " + respuesta);
		} else {
			return new Accion("Usuario: " + codigo + " No encontrado.");
		}
	}

	@GetMapping(path = "/usuario/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object usuarioGet(@PathVariable String codigo) {
		try {
			Empleado empleado = empleadoMapper.findById(codigo);
			if (empleado != null) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(empleado);
			} else {
				return new Accion("Usuario: " + codigo + " No encontrado.");
			}
		} catch (Exception e) {
			return new Accion("Error " + e);
		}

	}

	@PostMapping(path = "/usuario/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object usuarioPost(@RequestBody Empleado empleado) {
		try {

			if (empleado != null) {
				if (empleadoMapper.insert(empleado) == 1) {
					empleado = empleadoMapper.findById(empleado.getId_Empleado_Woffu());
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(empleado);
				} else {
					return new Accion("ERROR al insertar usuario.");
				}
			} else {
				return new Accion("ERROR al insertar usuario.");
			}
		} catch (Exception e) {
			return new Accion("Error " + e);
		}
	}

	@PutMapping(path = "/usuario/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object usuarioPut(@RequestBody Empleado empleado, @PathVariable String codigo) {

		try {
			Empleado empleadoAux = empleadoMapper.findById(codigo);

			if (empleado != null && empleadoAux.getId_Empleado_Woffu().equals(empleado.getId_Empleado_Woffu())) {

				empleadoMapper.update(empleado);
				empleado = empleadoMapper.findById(codigo);
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(empleado);
			} else {
				return new Accion("Usuario: " + empleado.getId_Empleado_Woffu() + " No encontrado.");
			}
		} catch (JsonProcessingException e) {
			return new Accion("Error " + e);
		}

	}
	
	@DeleteMapping(path="/usuario/{codigo}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object usuarioDelete(@PathVariable String codigo) {

		try {
			Empleado empleado = empleadoMapper.findById(codigo);

			if (empleado != null) {

				if (empleadoMapper.logicDeleteById(codigo)==1) {
					return new Accion("Usuario: " + codigo + " Borrado.");
				}
				else {
					return new Accion("Usuario: " + codigo + " No encontrado.");
				}
			} else {
				return new Accion("Usuario: " + codigo + " No encontrado.");
			}
		} catch (Exception e) {
			return new Accion("Error " + e);
		}

	}
	
	
	public EmpleadoMapper getEmpleadoMapper() {
		return empleadoMapper;
	}

	public void setEmpleadoMapper(EmpleadoMapper empleadoMapper) {
		this.empleadoMapper = empleadoMapper;
	}

}
