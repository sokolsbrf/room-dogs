package ru.dimasokol.school.roompets;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import java.util.Date;
import java.util.List;

import ru.dimasokol.school.roompets.data.Dog;
import ru.dimasokol.school.roompets.data.DogsDatabase;
import ru.dimasokol.school.roompets.data.Vaccination;
import ru.dimasokol.school.roompets.data.migrations.From1To2;

public class MainActivity extends AppCompatActivity {

    private DogsDatabase mDatabase;
    private View mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase =
                Room.databaseBuilder(this, DogsDatabase.class, "dogs.db")
                        // НЕ НАДО ТАК ДЕЛАТЬ
                        // ЭТО ИСКЛЮЧИТЕЛЬНО ДЛЯ ПРИМЕРА РАБОТЫ DAO И ПРОЧИХ ОБЪЕКТОВ
                        // ROOM СПЕЦИАЛЬНО СОЗДАН ТАКИМ, ЧТОБЫ БИТЬ ПО РУКАМ ЗА ПОТОКИ НА UI THREAD
                        .allowMainThreadQueries()
                        // ЕЩЁ РАЗ: НИКОГДА НЕ ДЕЛАЕМ ТАК, КАК СТРОЧКА ВЫШЕ
                        .addMigrations(new From1To2())
                        .build();

        Dog dog = new Dog();
        dog.setName("Бобик");
        dog.setMale(true);

        Vaccination first = new Vaccination();
        first.setVaccineName("Фуфломицин");
        first.setVaccinationDate(new Date(System.currentTimeMillis() - 1000));
        first.setDogId(1);

        Vaccination second = new Vaccination();
        second.setVaccineName("Фуфлобиотик");
        second.setVaccinationDate(new Date(System.currentTimeMillis()));
        second.setDogId(1);

        mDatabase.getDogsDao().addDog(dog);
        mDatabase.getDogsDao().addVaccination(first);
        mDatabase.getDogsDao().addVaccination(second);

        LiveData<List<Dog>> dogsData = mDatabase.getDogsDao().getAllDogs();
        dogsData.observe(this, new Observer<List<Dog>>() {
            @Override
            public void onChanged(List<Dog> dogs) {
                Log.d("Dogs", "size() = " + dogs.size());
            }
        });

        mAddButton = findViewById(R.id.add_dog);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dog otherDog = new Dog();
                otherDog.setMale(false);
                otherDog.setName("Жучка");
                mDatabase.getDogsDao().addDog(otherDog);
            }
        });
    }
}
