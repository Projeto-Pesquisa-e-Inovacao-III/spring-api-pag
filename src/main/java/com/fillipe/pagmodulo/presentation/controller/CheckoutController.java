package com.fillipe.pagmodulo.presentation.controller;

import com.fillipe.pagmodulo.application.dto.request.ReqCreateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.response.ResCheckoutDto;
import com.fillipe.pagmodulo.application.mapper.ApplicationCheckoutMapper;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.GetCheckout.GetCheckoutUseCase;
import com.fillipe.pagmodulo.domain.entity.Checkout;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/checkouts")
public class CheckoutController {

    private final CreateCheckoutUseCase createCheckoutUseCase;
    private final ApplicationCheckoutMapper checkoutMapper;
    private final GetCheckoutUseCase getCheckoutUseCase;

    public CheckoutController(CreateCheckoutUseCase createCheckoutUseCase, ApplicationCheckoutMapper checkoutMapper, GetCheckoutUseCase getCheckoutUseCase) {
        this.createCheckoutUseCase = createCheckoutUseCase;
        this.checkoutMapper = checkoutMapper;
        this.getCheckoutUseCase = getCheckoutUseCase;
    }

    @PostMapping
    public ResponseEntity<ResCheckoutDto> createCheckout(@Valid @RequestBody ReqCreateCheckoutDto request) {
        CreateCheckoutCommand command = checkoutMapper.toCreateCheckoutCommand(request);
        Checkout checkout = createCheckoutUseCase.execute(command);

        ResCheckoutDto response = checkoutMapper.toCheckoutResponseDTO(checkout);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResCheckoutDto> getCheckout(@PathVariable UUID uuid){
        Checkout checkout = getCheckoutUseCase.execute(uuid);

        ResCheckoutDto response = checkoutMapper.toCheckoutResponseDTO(checkout);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
