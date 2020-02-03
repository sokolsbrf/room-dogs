package ru.dimasokol.school.roompets.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Dog.class, Vaccination.class}, version = 2)
@TypeConverters({DateConverter.class})
public abstract class DogsDatabase extends RoomDatabase {

    public abstract DogsDao getDogsDao();

}
