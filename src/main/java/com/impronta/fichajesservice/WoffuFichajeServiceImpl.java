package com.impronta.fichajesservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class WoffuFichajeServiceImpl implements WoffuFichajeService {

	@Override
	public String fichaje(Empleado empleado) {

		try {

			URL url = new URL("https://improntasoluciones.woffu.com/api/signs");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestProperty("Authorization", "Bearer " + empleado.getBearer());
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json; utf-8");
			conn.setRequestMethod("POST");

			JSONObject JsonObject = new JSONObject();

			JsonObject.put("UserId", empleado.getId_Empleado_Woffu());

			JsonObject.put("TimezoneOffset", -120);

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = JsonObject.toString().getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;

			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output);
			}

			in.close();

			System.out.println("Response:-" + response.toString());

			if (response.toString().contains("\"SignIn\":false")) {
				return "Ha salido.";
			} else if (response.toString().contains("\"SignIn\":true")) {
				return "Ha entrado.";
			}

		} catch (Exception e) {
			System.out.println("Exception " + e);
		}

		return "Ha tenido un error en el proceso de fichaje.";

	}

}
