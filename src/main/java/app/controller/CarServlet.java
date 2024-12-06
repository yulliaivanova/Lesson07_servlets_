package app.controller;

import app.model.Car;
import app.repository.CarRepository;
import app.repository.CarRepositoryDB;
import app.repository.CarRepositoryMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CarServlet extends HttpServlet {

    private CarRepository repository = new CarRepositoryDB();
    private ObjectMapper mapper = new ObjectMapper();

    // GET http://10.2.3.4:8080/cars


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Для получение из БД автомобилей (всех или одного автомобиля по id)
        // /cars; GET /cars?id=3

        // request - это объект запроса. Из него мы можем извлечь все, что прислал клиент в запросе.
        // response - это объект ответа, который будет отправлен клиенту после того, как отработает наш метод.
        // Мы можем в этот объект ответа поместить всю информацию, которую хотим отправить клиенту в ответ на его запрос.

        Map<String, String[]> params = request.getParameterMap();
        // "id" : ["3"]

        if (params.isEmpty()) {
            List<Car> cars = repository.getAll();

            response.setContentType("application/json"); // Устанавливаем тип контента JSON
            // Преобразовать список машин в JSON
            String json = mapper.writeValueAsString(cars);
            response.getWriter().write(json);
        } else {
            // ["3"]
            String idStr = params.get("id")[0];
            Long id = Long.parseLong(idStr);
            Car car = repository.findById(id);
            if (car == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Car not found!");
            } else {
                String jsonResponse = mapper.writeValueAsString(car); // Преобразуем объект car в JSON
                response.getWriter().write(jsonResponse);
            }

        }


//        cars.forEach(car -> {
//            try {
//                // Записываем каждую машину в виде строки
//                response.getWriter().write(car.toString() + "\n");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

    //    POST -> /cars
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Для сохранения нового автомобиля в БД

//        преобразуем json из запроса в java обьект, используя jackson
        Car car = mapper.readValue(request.getReader(), Car.class);
        System.out.println("car from Post: " + car);

//    сохоаняем в базу данных
        car = repository.save(car);

        String json = mapper.writeValueAsString(car);

        response.getWriter().write(json);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Для изменения существующего автомобиля в БД
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Для удаление автомобиля из БД по id
        // DELETE /cars?id=3
        super.doDelete(req, resp);
    }
}