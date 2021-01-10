package hr.fer.ruazosa.network_memory_game;


import com.google.firebase.FirebaseApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class NetworkMemoryGameApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(NetworkMemoryGameApplication.class, args);

		FirebaseApp.initializeApp();

	}


}
