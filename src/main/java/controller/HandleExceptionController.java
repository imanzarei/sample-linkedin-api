package controller;

import exception.CustomGenericException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by iman on 12/27/2014.
 */
public class HandleExceptionController {

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomException(CustomGenericException ex) {

        ModelAndView model = new ModelAndView("error/generic_error");
        model.addObject("exception", ex);

        return model;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("generic_error");

        return model;

    }
}
