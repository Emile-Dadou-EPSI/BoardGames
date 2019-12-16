package BoardGames.servlets;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import javax.servlet.RequestDispatcher;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@javax.servlet.annotation.WebServlet(name = "ServletLogin")
public class ServletLogin extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // Récupération des infos du formulaire de connexion
        RequestDispatcher rd = null;
        String Login = request.getParameter("login");
        String Pwd = request.getParameter("password");
        System.out.println(Login);
        // Connexion to cloud firestore
        FileInputStream refreshToken = new FileInputStream("C:\\Users\\emile\\IdeaProjects\\BoardGames\\src\\main\\java\\boardgames-6813a-firebase-adminsdk-vvivt-657fd5ca31.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(refreshToken);
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef2 = db.collection("users").document("user1");
        ApiFuture<DocumentSnapshot> future = docRef2.get();

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
        } else {
            System.out.println("No such document!");
        }

        if (Login.equals("test") && Pwd.equals("test")) {
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else {
            request.getRequestDispatcher("/test.html").forward(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
