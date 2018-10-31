package net.daw.service;

import java.util.ArrayList;

import net.daw.bean.UsuarioBean;

public class FillService {
	
	public ArrayList<UsuarioBean> FillUsuario(int numero) {
		String[] dni = { "9654123F", "25896321P", "96325812H", "75395182R", "98741236L" };
		String[] nombre = { "Carlos", "Victor", "Manuel", "Maria", " Lucia" };
		String[] ape1 = { "Martinez", "Garcia", "Perez", "Gonzalez", "Marquez" };
		String[] ape2 = { "Martinez", "Garcia", "Perez", "Gonzalez", "Marquez" };
		String[] login = { "bemu", "poka", "moci", "pofu", "mita" };
		String[] pass = { "12345", "aeiou", "qwert", "abcde", "00000" };
		int[] id_tipoUsuario = { 1,2 };
		
		ArrayList<UsuarioBean> resultado = new ArrayList<>();
		UsuarioBean resultadoUsuario;
		for (int i = 0; i < numero; i++) {
			resultadoUsuario = new UsuarioBean();
			resultadoUsuario.setDni(dni[randomMath(dni.length)]);
			resultadoUsuario.setNombre(nombre[randomMath(nombre.length)]);
			resultadoUsuario.setApe1(ape1[randomMath(ape1.length)]);
			resultadoUsuario.setApe2(ape2[randomMath(ape2.length)]);
			resultadoUsuario.setLogin(login[randomMath(login.length)]);
			resultadoUsuario.setPass(pass[randomMath(pass.length)]);
			resultadoUsuario.setId_tipoUsuario(id_tipoUsuario[randomMath(id_tipoUsuario.length)]);
			
			resultado.add(resultadoUsuario);
		}
		return resultado;
	}

	private int randomMath(int number) {
		return (int) (Math.random() * number);
	}
}
