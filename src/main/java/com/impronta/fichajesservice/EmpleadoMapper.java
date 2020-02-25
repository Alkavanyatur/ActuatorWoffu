package com.impronta.fichajesservice;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmpleadoMapper {

	@Select("select * from empleados")
	public List<Empleado> findAll();

	@Select("SELECT * FROM empleados WHERE ID_EMPLEADO_WOFFU = #{id} AND BORRADO=0")
    public Empleado findById(String id);

    @Delete("DELETE FROM empleados WHERE ID_EMPLEADO_WOFFU = #{id} AND BORRADO=1")
    public int deleteById(String id);

    @Insert("INSERT INTO empleados(ID_EMPLEADO_WOFFU, nombre, apellidos, token, bearer) " +
        " VALUES (#{id_Empleado_Woffu}, #{nombre}, #{apellidos}, #{token}, #{bearer})")
    public int insert(Empleado empleado);

    @Update("Update empleados set nombre=#{nombre}, apellidos=#{apellidos}, " +
        " token=#{token}, bearer=#{bearer} where ID_EMPLEADO_WOFFU = #{id_Empleado_Woffu} AND BORRADO=0")
    public int update(Empleado empleado);
    
    @Update("UPDATE empleados SET BORRADO=1 WHERE ID_EMPLEADO_WOFFU = #{id}")
    public int logicDeleteById(String id);
}
