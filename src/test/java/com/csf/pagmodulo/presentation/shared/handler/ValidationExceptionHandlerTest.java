package com.csf.pagmodulo.presentation.shared.handler;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ValidationExceptionHandlerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new TestValidationController())
                .setControllerAdvice(new ValidationExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnBadRequestWithFieldErrorsWhenBodyIsInvalid() throws Exception {
        String payload = """
                {
                  "name": "",
                  "quantity": 0
                }
                """;

        mockMvc.perform(post("/test/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Dados Invalidos"))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("name: O nome e obrigatorio")))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("quantity: A quantidade deve ser maior que zero")));
    }

    @RestController
    static class TestValidationController {

        @PostMapping("/test/validate")
        public void validate(@Valid @RequestBody TestRequest request) {
            // no-op
        }
    }

    record TestRequest(
            @NotBlank(message = "O nome e obrigatorio") String name,
            @Positive(message = "A quantidade deve ser maior que zero") int quantity
    ) {
    }
}

