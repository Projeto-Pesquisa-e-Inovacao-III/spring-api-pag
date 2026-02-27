package com.fillipe.pagmodulo.presentation.controller;

import com.fillipe.pagmodulo.application.dto.request.ReqCreateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.response.ResActivateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.response.ResCheckoutDto;
import com.fillipe.pagmodulo.application.dto.response.ResInactivateCheckoutDto;
import com.fillipe.pagmodulo.application.mapper.ApplicationCheckoutMapper;
import com.fillipe.pagmodulo.application.usecase.ActivateCheckout.ActivateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.InactivateCheckout.InactivateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.GetCheckout.GetCheckoutUseCase;
import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/checkouts")
public class CheckoutController {

    private final CreateCheckoutUseCase createCheckoutUseCase;
    private final GetCheckoutUseCase getCheckoutUseCase;
    private final InactivateCheckoutUseCase inactivateCheckoutUseCase;
    private final ActivateCheckoutUseCase activateCheckoutUseCase;

    public CheckoutController(CreateCheckoutUseCase createCheckoutUseCase, GetCheckoutUseCase getCheckoutUseCase, InactivateCheckoutUseCase inactivateCheckoutUseCase, ActivateCheckoutUseCase activateCheckoutUseCase) {
        this.createCheckoutUseCase = createCheckoutUseCase;
        this.getCheckoutUseCase = getCheckoutUseCase;
        this.inactivateCheckoutUseCase = inactivateCheckoutUseCase;
        this.activateCheckoutUseCase = activateCheckoutUseCase;
    }

    @PostMapping
    public ResponseEntity<ResCheckoutDto> createCheckout(@Valid @RequestBody ReqCreateCheckoutDto request) {
        CreateCheckoutCommand command = ApplicationCheckoutMapper.toCreateCheckoutCommand(request);
        Checkout checkout = createCheckoutUseCase.execute(command);

        ResCheckoutDto response = ApplicationCheckoutMapper.toCheckoutResponseDTO(checkout);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResCheckoutDto> getCheckout(@PathVariable UUID uuid) {
        Checkout checkout = getCheckoutUseCase.execute(uuid);

        ResCheckoutDto response = ApplicationCheckoutMapper.toCheckoutResponseDTO(checkout);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PostMapping("/{uuid}/activate")
    public ResponseEntity<ResActivateCheckoutDto> activateCheckout(@PathVariable UUID uuid) {
        return new ResponseEntity<>(
                new ResActivateCheckoutDto(uuid, activateCheckoutUseCase.execute(uuid).getStatus()) ,
                HttpStatus.OK);
    }

    @PostMapping("/{uuid}/inactivate")
    public ResponseEntity<ResInactivateCheckoutDto> inactivateCheckout(@PathVariable UUID uuid) {
        return new ResponseEntity<>(
                new ResInactivateCheckoutDto(uuid, inactivateCheckoutUseCase.execute(uuid).getStatus()),
                HttpStatus.OK);
    }
}
