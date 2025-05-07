package com.example.movies.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.domain.FilmItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase= FirebaseDatabase.getInstance()

    fun loadUpcoming(): LiveData<MutableList<FilmItemModel>>{
        val listData = MutableLiveData<MutableList<FilmItemModel>>()
        val ref =firebaseDatabase.getReference("Upcomming")

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FilmItemModel>()
                for(childSnapshot in snapshot.children){
                    val item=childSnapshot.getValue(FilmItemModel::class.java)
                    item?.let{lists.add(it)}
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

                Log.e("FirebaseTest", "数据加载失败: ${error.message}")
            }

        })
        return listData
    }

    fun loadItem(): LiveData<MutableList<FilmItemModel>>{
        val listData = MutableLiveData<MutableList<FilmItemModel>>()
        val ref =firebaseDatabase.getReference("Items")

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FilmItemModel>()
                for(childSnapshot in snapshot.children){
                    val item=childSnapshot.getValue(FilmItemModel::class.java)
                    item?.let{lists.add(it)}
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

                Log.e("FirebaseTest", "数据加载失败: ${error.message}")
            }

        })
        return listData
    }
}