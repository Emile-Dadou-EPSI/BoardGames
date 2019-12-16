import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import jdk.internal.jimage.ImageStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class testfirebase {
    // Use the application default credentials
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FileInputStream refreshToken = new FileInputStream("C:\\Users\\emile\\IdeaProjects\\BoardGames\\src\\main\\java\\boardgames-6813a-firebase-adminsdk-vvivt-657fd5ca31.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(refreshToken);
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();

        // Add document data with id "test"
        Map<String, Object> data = new HashMap<>();
        data.put("Name", "Emile");
        data.put("LastName", "Dadou");
        data.put("Name", "Lionel");
        data.put("LastName", "Batt");
        data.put("Password", "test");
        //db.collection("FirstTest").document("Test1").set(data);
        ApiFuture<WriteResult> res = db.collection("users").document("user1").set(data);
        System.out.print("Update time : " + res.get().getUpdateTime());


        DocumentReference docRef = db.collection("users").document("user1");
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
            System.out.println("Hello");
        } else {
            System.out.println("No such document!");
        }
    }

}