package br.com.eric.controllers;

import br.com.eric.exception.UnsupportedMathOperationException;
import br.com.eric.utils.converters.NumberUtils;
import br.com.eric.utils.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController1 {

    private SimpleMath simpleMath = new SimpleMath();

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (NumberUtils.isNumeric(numberOne) || NumberUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return simpleMath.sum(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
    }

    @RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (NumberUtils.isNumeric(numberOne) || NumberUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return simpleMath.subtraction(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
    }

    @RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (NumberUtils.isNumeric(numberOne) || NumberUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return simpleMath.multiplication(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
    }

    @RequestMapping(value="/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (NumberUtils.isNumeric(numberOne) || NumberUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return simpleMath.division(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
    }

    @RequestMapping(value="/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (NumberUtils.isNumeric(numberOne) || NumberUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return simpleMath.mean(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
    }

    @RequestMapping(value="/squareRoot/{number}", method = RequestMethod.GET)
    public Double squareRoot(
            @PathVariable("number") String number) throws Exception {

        if (NumberUtils.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return simpleMath.squareRoot(NumberUtils.convertToDouble(number));
    }
}
