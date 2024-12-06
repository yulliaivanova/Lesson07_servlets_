
package app.repository;

import app.model.Car;

import java.util.List;


public interface CarRepository {
    // Получение всех машин
    List<Car> getAll();

    // Сохранение машины в Хранилище Данных
    Car save(Car car);

    Car findById(Long id);
    Car update (Car car);
    boolean deleteById(Long id);
}
