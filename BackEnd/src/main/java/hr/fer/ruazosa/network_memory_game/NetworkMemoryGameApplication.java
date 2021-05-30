package hr.fer.ruazosa.network_memory_game;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class NetworkMemoryGameApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(NetworkMemoryGameApplication.class, args);


		FileInputStream serviceAccount =
				new FileInputStream("C:\\projekt_ruazosa_memory_game\\memorygame-ce778-firebase-adminsdk-ecm7b-bbe8c55d13.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);


	}


}
