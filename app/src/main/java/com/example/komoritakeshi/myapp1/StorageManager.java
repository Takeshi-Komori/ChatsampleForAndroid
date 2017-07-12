package com.example.komoritakeshi.myapp1;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by komoritakeshi on 2017/07/11.
 */

public class StorageManager {
    private StorageReference imagesRef;

    StorageManager() {
        StorageReference imagesRef;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://chaty-d559d.appspot.com");
        imagesRef = storageRef.child("profile_image");
    }

    StorageManager getInstance() {
        return new StorageManager();
    }

    void uploadImage(String userID) {

    }

    void downLoadImage(String userID) {

    }

}
