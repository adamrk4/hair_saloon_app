package com.example.final_p.classes;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference usersRef;

    public FirebaseUtils() {
        firebaseAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    // Method to save additional user data to Firebase Realtime Database
    public void saveUserData(String name, String email) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            // Create a new node under "users" with the user's UID
            DatabaseReference userNodeRef = usersRef.child(uid);
            // Write user data to the database
            userNodeRef.child("name").setValue(name);
            userNodeRef.child("email").setValue(email);


            // You can write any additional user data as needed
        }
    }
}

