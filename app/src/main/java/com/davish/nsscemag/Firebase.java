package com.davish.nsscemag;

import com.davish.nsscemag.models.Article;
import com.davish.nsscemag.models.BigData;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Firebase {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    void getArticle(){
        db.collection("category").get()
                .addOnCompleteListener(task ->{
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot doc : task.getResult()){
                            Map category = doc.getData();
                            for(Object val: category.values()){
                                ArrayList<Article> tempArticleList = new ArrayList<>();
                                db.collection("article").whereArrayContains("category",val).get()
                                        .addOnCompleteListener(article_task ->{
                                            if(article_task.isSuccessful()){
                                                for(QueryDocumentSnapshot article_doc : article_task.getResult()){
                                                    tempArticleList.add(article_doc.toObject(Article.class));


                                                }
                                                //dataList.add(new BigData(val.toString(),tempArticleList));

                                            }
                                        } );


                            }
                        }
                        //jhkjg
                    }


                } );
    }
}
