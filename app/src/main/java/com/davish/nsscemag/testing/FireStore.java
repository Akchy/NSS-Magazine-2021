package com.davish.nsscemag.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.davish.nsscemag.R;
import com.davish.nsscemag.models.Article;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FireStore extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG="data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_store);


        db.collection("category").get()
                .addOnCompleteListener(task ->{
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot doc : task.getResult()){
                            Map category = doc.getData();
                            for(Object val: category.values()){
                                ArrayList<Article> articleList = new ArrayList<>();
                                db.collection("article").whereArrayContains("category",val).get()
                                        .addOnCompleteListener(article_task ->{
                                            if(article_task.isSuccessful()){
                                                for(QueryDocumentSnapshot article_doc : article_task.getResult()){
                                                    articleList.add(article_doc.toObject(Article.class));
                                                }
                                            }
                                        } );


                            }
                        }
                    }
                } );

       /* db.collection("category").get()
                .addOnCompleteListener(task ->{
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot doc : task.getResult()){
                            Map category = doc.getData();
                            for(Object val: category.values()){
                                Log.d("category",val.toString());
                                db.collection("article").whereArrayContains("category",val).get()
                                        .addOnSuccessListener((OnSuccessListener<QuerySnapshot>) querySnapshot -> {
                                            //articleList.add(documentSnapshot.toObjects(Article.class));
                                            Log.d("query",querySnapshot.getDocuments().getClass().getSimpleName());
                                        });
                            }
                        }
                    }
                } );*/
    }
}