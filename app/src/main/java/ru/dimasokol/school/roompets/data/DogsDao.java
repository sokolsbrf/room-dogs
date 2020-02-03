package ru.dimasokol.school.roompets.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DogsDao {

    @Query("SELECT * FROM dogs")
    LiveData<List<Dog>> getAllDogs();

    @Query("SELECT * FROM dogs WHERE id = :dogId")
    LiveData<Dog> getDogById(long dogId);

    @Insert
    long addDog(Dog dog);

    @Delete
    void deleteDog(Dog dog);

    @Update
    void updateDog(Dog dog);

    @Query("DELETE FROM dogs WHERE id = :dogId")
    void deleteDogById(long dogId);

    @Query("SELECT * FROM vaccinations WHERE dog_id = :dogId ORDER BY date DESC")
    List<Vaccination> getVaccinationsForDog(long dogId);

    @Insert
    long addVaccination(Vaccination vaccination);
}
