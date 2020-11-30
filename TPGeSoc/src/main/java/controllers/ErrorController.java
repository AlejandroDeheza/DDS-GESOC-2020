package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ErrorController {
    public ModelAndView getError(Request request, Response response) {
        return new ModelAndView(null,"error.html.hbs");
    }
}
