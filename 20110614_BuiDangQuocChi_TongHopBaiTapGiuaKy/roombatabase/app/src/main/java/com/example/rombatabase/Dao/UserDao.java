package com.example.rombatabase.Dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rombatabase.User;
import com.example.rombatabase.UserDatabase;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from Users")
    List<User> getAll();

    @Query("select * from Users where username=:username")
    List<User> checkUser(String username);






    @Query("select * from Users where id in (:userIds) ")
    List<User>loadAllByIds(int[] userIds);

    @Insert
    void insertAll(User...Users);

    @Update
    void update(User... Users);

    @Delete
    void delete(User user);


}
