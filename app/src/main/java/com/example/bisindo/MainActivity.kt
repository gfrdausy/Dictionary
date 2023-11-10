package com.example.bisindo

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.widget.Filter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bisindo.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var kamusList: ArrayList<kamusModel>

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().getReference("kamus")
        kamusList = arrayListOf()

        getKamus("Alfabet")

        binding.rvKamus.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }


    }

    private fun getKamus(filter: String) {
        dbRef.orderByChild("jenis").equalTo(filter).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                kamusList.clear()
                if (snapshot.exists()){
                    for (kamusSnap in snapshot.children){
                        val kamus = kamusSnap.getValue(kamusModel::class.java)
                        kamusList.add(kamus!!)
                    }
                }
                val rvAdapter = kamusAdapter(kamusList)
                binding.rvKamus.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}