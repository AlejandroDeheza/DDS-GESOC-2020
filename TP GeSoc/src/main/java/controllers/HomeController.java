package controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;

public class HomeController {

    public ModelAndView getHome() {
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("anio", LocalDate.now().getYear());
       
        
        return new ModelAndView(modelo, "home.html.hbs");
    }
}