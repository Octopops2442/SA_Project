package com.iiitb.userservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.iiitb.userservice.model.Question;
import com.iiitb.userservice.model.User;
import com.iiitb.userservice.repository.QuestionRepository;
import com.iiitb.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Component
@RequiredArgsConstructor
public class UserService{
    private static int ID = 1;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    public void addUser(User user){
        userRepository.save(user);
    }

    @Autowired
    public List<Question> getQuestions(User user){
//        return userRepository.findAllQuestions(user.getUid());
        List<Question> qList = questionRepository.findAll();
        List<Question> result = new ArrayList<>();

        for (Question q: qList) {
            if (q.getUser_id() == user.getUser_id()) {
                result.add(q);
            }
        }
        return result;
    }

    private static final String COLLECTION_NAME="user";

    public String firebaseAddU(User user) throws ExecutionException, InterruptedException {
        user.setUser_id(ID++);
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture =
                dbFirestore.collection(COLLECTION_NAME).document(user.getUser_id().toString()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    private static final String COLLECTION_DATA="question";

    public List<QueryDocumentSnapshot> firebaseGetQ(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> collectionApiFuture =
                dbFirestore.collection(COLLECTION_DATA).whereEqualTo("user_id", user.getUser_id()).get();

        return collectionApiFuture.get().getDocuments();
    }


}
