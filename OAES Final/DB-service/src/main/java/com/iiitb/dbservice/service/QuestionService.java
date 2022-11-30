package com.iiitb.dbservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.iiitb.dbservice.model.Question;
import com.iiitb.dbservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static javax.swing.Action.NAME;

@Service
@Component
@RequiredArgsConstructor
public class QuestionService{
    private static int ID = 1;
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    public void modifyQuestion(Question question){
        questionRepository.save(question);
    }

    @Autowired
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    @Autowired
    public void deleteQuestion(Question question){
        questionRepository.deleteById(question.getQID());
    }

    private static final String COLLECTION_NAME="question";

    public String firebaseAddQ(Question question) throws ExecutionException, InterruptedException {
        question.setQID(ID++);
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture =
                dbFirestore.collection(COLLECTION_NAME).document(Integer.toString(question.getQID())).set(question);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String firebaseModQ(Question question) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture =
                dbFirestore.collection(COLLECTION_NAME).document(Integer.toString(question.getQID())).set(question);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String firebaseDelQ(Question question) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture;
//        collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(question.getQuestion()).delete();

        collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(Integer.toString(question.getQID())).delete();

        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
