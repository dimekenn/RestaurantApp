package kz.nurbekov.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import kz.nurbekov.restaurant.Model.Food;
import kz.nurbekov.restaurant.Model.Order;
import kz.nurbekov.restaurant.database.Database;

public class FoodDetail extends AppCompatActivity {

    TextView food_name, food_price, food_descripton;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String foodId= "";

    FirebaseDatabase database;
    DatabaseReference foods;
    Food currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        //Init view
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getFood(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()));
                Toast.makeText(FoodDetail.this, "Добавлена в корзину", Toast.LENGTH_SHORT).show();
            }
        });

        food_descripton = (TextView)findViewById(R.id.food_description);
        food_name = (TextView)findViewById(R.id.food_name);
        food_price = (TextView)findViewById(R.id.food_price);
        food_image = (ImageView)findViewById(R.id.img_food);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsAppBar);

        //Get Food id from Intent
        if (getIntent()!=null)
            foodId = getIntent().getStringExtra("FoodId");
        if (!foodId.isEmpty()){
            getDetailFood(foodId);
        }

    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class);
                //SetImage
                Picasso.get().load(currentFood.getImage()).into(food_image);
                collapsingToolbarLayout.setTitle(currentFood.getFood());
                food_price.setText(currentFood.getPrice());
                food_descripton.setText(currentFood.getDescription());
                food_name.setText(currentFood.getFood());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
