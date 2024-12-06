package app.repository;

import app.constants.Constants;
import app.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static app.constants.Constants.*;

public class CarRepositoryDB implements CarRepository {

    private Connection getConnection() {

        try {
//            погрузили класс в память приложения
            Class.forName(Constants.DB_DRIVER_PATH);
            String dbUrl = String.format("%s%s?user=%s&password=%s", Constants.DB_ADDRESS,
                    DB_NAME, DB_USER, DB_PASSWORD);
            return DriverManager.getConnection(dbUrl);


        } catch (Exception e) {
            throw new RuntimeException("Ошибка подкл",e);
        }

    }

    @Override
    public List<Car> getAll() {


        try(Connection connection = getConnection()){

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public Car save(Car car) {


       try(Connection connection = getConnection()){
//           1.Cоставить sql запросы
//        INSERT INTO cars (brand,price, year) VALUES ('Toyota', 3500, 2022);
           String query = String.format("INSERT INTO cars (brand,price, year) VALUES ('%s', $s, $d);",
                   car.getBrand(), car.getPrice(),car.getYear());

           Statement statement =connection.createStatement();
//           statement.execute(query);- запросы , который вносят изменения в БД(POST,PUT)
//           statement.executeQuery()- запросы для получени яданных (GET)

//            т к у нас метод save вносит изменения (записывает фвто в БД
           statement.execute(query);

           return car;

       }catch (Exception e){
           throw new RuntimeException(e);
       }



    }

    @Override
    public Car findById(Long id) {


        try(Connection connection = getConnection()){

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Car update(Car car) {


        try(Connection connection = getConnection()){

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {

        try(Connection connection = getConnection()){

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return false;
    }
}
