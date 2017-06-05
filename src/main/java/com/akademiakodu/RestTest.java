package com.akademiakodu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lukasz Kolacz on 05.06.2017.
 */
public class RestTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę użytkownika:");
        String username = scanner.next();

//        OTWIERAMY POLACZENIE HTTP
        try {
//            URL url = new URL("http://localhost:8080/api/user");
            URL url = new URL("http://localhost:8080/api/user/" + username);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Access-Password", "akademiakodujestfajna");

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            System.out.println(stringBuilder.toString());

//            Tworzenie tablicy obiektów JSON-owskich:

//            JSONArray array = new JSONArray(stringBuilder.toString());
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject object = array.getJSONObject(i);
//                System.out.println("Username: " + object.getString("username"));
//                System.out.println("Password: " + object.getString("password"));
//                System.out.println("--------------------------------------------");
//
//            }
//            ---------------------------------------------------------------------------
//            Wyszukiwanie tylko jednego usera po jego nazwie
            JSONObject object = new JSONObject(stringBuilder.toString());
            System.out.println("Username: " + object.getString("username"));
            System.out.println("Password: " + object.getString("password"));
            System.out.println("--------------------------------------------");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
