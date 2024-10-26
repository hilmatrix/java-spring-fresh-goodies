package com.hilmatrix.fresh_goodies.home;

import com.hilmatrix.fresh_goodies.response.ApiConstants;
import com.hilmatrix.fresh_goodies.response.ApiResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
public class HomeController implements ErrorController {
    @GetMapping("/")
    public ApiResponse<String> helloWorld() {
        return new ApiResponse<String>(
                ApiConstants.STRING_HOMEPAGE,
                ApiConstants.STATUS_SUCCESS,
                true,
                null
        );
    }
}


