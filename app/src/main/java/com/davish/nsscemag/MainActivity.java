package com.davish.nsscemag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chauthai.overscroll.RecyclerViewBouncy;
import com.davish.nsscemag.modelAdapter.SmallAdapter;
import com.davish.nsscemag.modelAdapter.VertAdapter;
import com.davish.nsscemag.models.Article;
import com.davish.nsscemag.models.BigData;
import com.davish.nsscemag.models.SmallData;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xw.repo.widget.BounceScrollView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerViewBouncy recyclerView;
    ArrayList<SmallData> source;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    SmallAdapter adapter;
    LinearLayoutManager HorizontalLayout;
    View ChildView;
    int RecyclerViewItemPosition;
    private StickyScrollView scrollView;
    private TextView imageView;
    LinearLayout topLayout;
    EditText search;
    List<BigData> dataList;
    RecyclerView recyclerView1;
    VertAdapter vertAdapter;
    BounceScrollView bounceScrollView, smallBounce, largeBounce;
/*
    TextView edit, art, tech, newtab;
    LinearLayout ledit, lart,ltech, lnew;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        /*edit = findViewById(R.id.edit_text);
        art = findViewById(R.id.art_text);
        tech = findViewById(R.id.tech_text);
        newtab = findViewById(R.id.new_text);

        ledit = findViewById(R.id.ledit);
        lart = findViewById(R.id.lart);
        ltech = findViewById(R.id.ltech);
        lnew = findViewById(R.id.lnew);*/
        bounceScrollView = findViewById(R.id.bounceScrollView);
        bounceScrollView.setTriggerOverScrollThreshold(25);
        bounceScrollView.setDamping(3.5f);
        bounceScrollView.setOnOverScrollListener(new BounceScrollView.OnOverScrollListener() {
            @Override
            public void onOverScrolling(boolean fromStart, int overScrolledDistance) {

            }
        });


        /*edit.setVisibility(View.VISIBLE);


        ledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.setVisibility(View.VISIBLE);
                art.setVisibility(View.GONE);
                tech.setVisibility(View.GONE);
                newtab.setVisibility(View.GONE);
            }
        });

        lart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                art.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                tech.setVisibility(View.GONE);
                newtab.setVisibility(View.GONE);
            }
        });

        ltech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tech.setVisibility(View.VISIBLE);
                art.setVisibility(View.GONE);
                edit.setVisibility(View.GONE);
                newtab.setVisibility(View.GONE);
            }
        });

        lnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newtab.setVisibility(View.VISIBLE);
                art.setVisibility(View.GONE);
                tech.setVisibility(View.GONE);
                edit.setVisibility(View.GONE);
            }
        });*/

        //BottomNavigationView navigation = findViewById(R.id.nav_view);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        search = findViewById(R.id.edt_search_text);
        topLayout = findViewById(R.id.topLayout);
        WebView view = (WebView) findViewById(R.id.textContent);
        String text;
        text = "<html><body><p align=\"justify\">";
        text+= "People no longer need to sit at a desk to get the benefits of technology. We can now build experiences that work seamlessly across different contexts.\n" +
                "        By removing abstract barriers, such as what functionality resides on what device, our users can focus more on what they\'re doing and less on how to\n" +
                "        start doing it.";
        text+= "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");
        topLayout.requestFocus();
        search.clearFocus();


        imageView = findViewById(R.id.text);
        scrollView =findViewById(R.id.scrollView);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                imageView.setTranslationY(scrollY/2);

            }
        });
        recyclerView
                = findViewById(
                R.id.recyclerview);
        RecyclerViewLayoutManager
                = new LinearLayoutManager(
                getApplicationContext());
        recyclerView.setLayoutManager(
                RecyclerViewLayoutManager);
        AddItemsToRecyclerViewArrayList();
        adapter = new SmallAdapter(source, MainActivity.this);
        HorizontalLayout
                = new LinearLayoutManager(
                MainActivity
                        .this,
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView1 = findViewById(R.id.dummyfrag_scrollableview);
        recyclerView1.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setHasFixedSize(true);





        dataList = new ArrayList<>();
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
                                                dataList.add(new BigData(val.toString(),tempArticleList));

                                                Log.e("tempb",dataList.toString());

                                                vertAdapter = new VertAdapter(dataList, MainActivity.this);
                                                recyclerView1.setHasFixedSize(true);
                                                recyclerView1.setLayoutManager(linearLayoutManager);
                                                recyclerView1.setAdapter(vertAdapter);
                                            }
                                        } );


                            }
                        }
                        Log.e("tempa",dataList.toString());

                    }


                } );

        /*Gson gson = new GsonBuilder().create();
        String jsonFileString = Utils.getJsonFromAssets(MainActivity.this, "magazine.json");
        Type listUserType = new TypeToken<List<BigData>>() { }.getType();
        dataList = new ArrayList<>();
        dataList =gson.fromJson(jsonFileString, listUserType);
        //Toast.makeText(getContext(), dataList.get(0).getType(), Toast.LENGTH_LONG).show();*/



    }

    public void AddItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        source = new ArrayList<SmallData>();
        source.add(new SmallData("Stories", "fd9331", "e12c5d", 0));
        source.add(new SmallData("Art", "039bca", "cb22b3", 1));
        source.add(new SmallData("Poems", "42e65f", "4eacac", 1));
    }


}