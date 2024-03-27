package com.example.eightpool

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var mParentAdapter: ParentAdapter
    private lateinit var mFoodItemTypeRecyclerView: RecyclerView
    private lateinit var dineImageButton: ImageButton
    private lateinit var takeAwayImageBnt:ImageButton
    private lateinit var deliveryImageButton: ImageButton
    private lateinit var shareImageButton: ImageButton
    private lateinit var fab:FloatingActionButton
    private lateinit var backImageButton: ImageButton
    private lateinit var cartImageView: ImageView
    private lateinit var cartBadge:TextView
    private lateinit var takeAwayBadge:ImageView
    private lateinit var dineInBadge:ImageView
    private lateinit var deliveryBadge:ImageView
    private lateinit var likeImageBtn:ImageButton
    private var sampleFoodItemTypes = listOf<FoodItemType>()
    private  var selectedItems:MutableLiveData<Int> = MutableLiveData(0)

//            dineBadge
//    takeAwayBadge
//    deliverBadge
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        #f1f1f1
        // card view color  #ffffff
        // fab button and another two buttons
        initViews()
    initClickListeners()
    selectedItems.observe(this, Observer { count ->
        cartBadge.text = count.toString()
    })
    }

    private fun initClickListeners() {
        dineImageButton.setOnClickListener {
            when(dineInBadge.visibility){
                View.VISIBLE->{
                    dineInBadge.visibility = View.INVISIBLE
                }
                View.INVISIBLE->{
                    dineInBadge.visibility=View.VISIBLE
                }


            }
        }
        takeAwayImageBnt.setOnClickListener {
            when(takeAwayBadge.visibility){
                View.VISIBLE->{
                    takeAwayBadge.visibility = View.INVISIBLE
                }
                View.INVISIBLE->{
                    takeAwayBadge.visibility=View.VISIBLE
                }


            }
        }
        deliveryImageButton.setOnClickListener {
            when(deliveryBadge.visibility){
                View.VISIBLE->{
                    deliveryBadge.visibility = View.INVISIBLE
                }
                View.INVISIBLE->{
                    deliveryBadge.visibility=View.VISIBLE
                }


            }
        }
        cartImageView.setOnClickListener {
//            if(selectedItems==0) {
//                cartBadge.visibility = View.INVISIBLE
//            }
//            else{
//                cartBadge.text = selectedItems.toString()
//            }

        }
        fab.setOnClickListener{
            Toast.makeText(this,"Opening chat support!",Toast.LENGTH_SHORT).show()
        }
        shareImageButton.setOnClickListener {
            Toast.makeText(this,"Shared",Toast.LENGTH_SHORT).show()
        }
        likeImageBtn.setOnClickListener {
            when(likeImageBtn.drawable.constantState){
                resources.getDrawable(R.drawable.heartfilled,null).constantState->{
                    likeImageBtn.setImageResource(R.drawable.heartoutlined)
                }
                resources.getDrawable(R.drawable.heartoutlined,null).constantState->{
                    likeImageBtn.setImageResource(R.drawable.heartfilled)
                }
            }
        }

        mParentAdapter.parentCLick = object :ChildAdapter.OnItemClickedListenerC{

            override fun onItemClicked(item: Item) {
                Log.i("selected items", selectedItems.value.toString())
                selectedItems.value = selectedItems.value?.plus(1)
            }
        }
//        mParentAdapter.setOnItemClickedListener(object : ChildAdapter.OnItemClickedListenerC {
//            override fun onItemClicked(item: Item) {
//                Log.i("selected items", selectedItems.value.toString())
//                selectedItems.value = selectedItems.value?.plus(1)
//            }
//        })
    }

    private fun initViews() {
        backImageButton = findViewById(R.id.backImageButton)
        cartImageView = findViewById(R.id.cartImageBtn)
        dineImageButton = findViewById(R.id.dineInImageBtn)
        takeAwayImageBnt = findViewById(R.id.takeAwayImageBtn)
        cartBadge = findViewById(R.id.cart_badge)
        cartBadge.text  = selectedItems.value.toString()
        deliveryBadge = findViewById(R.id.deliverBadge)
        takeAwayBadge = findViewById(R.id.takeAwayBadge)
        dineInBadge = findViewById(R.id.dineBadge)
        deliveryImageButton = findViewById(R.id.deliverImageBtn)
        likeImageBtn = findViewById(R.id.imageBtnHeart)
        shareImageButton = findViewById(R.id.imageBtnShare)
        mFoodItemTypeRecyclerView = findViewById(R.id.recyclerViewFoodItemTypes)
        fab  = findViewById(R.id.fab)

        mFoodItemTypeRecyclerView.layoutManager = LinearLayoutManager(
            this,)
        val  sampleFoodItems = listOf(
            Item(
                "fod1",
                "Eight Pool Salad",
                "Lettuce, tomatoes, cucumber, olives",
                "77.0 MAD",
                R.drawable.burger
            ),
            Item(
                "fod2",
                "Margherita Pizza",
                "Tomato sauce, mozzarella cheese, basil",
                "120.0 MAD",
                R.drawable.buterchicken
            ),
            Item(
                "fod3",
                "Grilled Salmon",
                "Salmon fillet, lemon, herbs",
                "150.0 MAD",
                R.drawable.buterchicken
            ),
            // Add more sample items as needed
        )

        sampleFoodItemTypes = listOf(
            FoodItemType("Salads", sampleFoodItems),
            FoodItemType("Pizzas", sampleFoodItems),
            FoodItemType("Seafood", sampleFoodItems)
        )

        mParentAdapter = ParentAdapter(this, sampleFoodItemTypes)
        mFoodItemTypeRecyclerView.adapter = mParentAdapter





    }
}