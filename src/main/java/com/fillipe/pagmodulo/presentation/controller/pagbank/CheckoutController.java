package com.fillipe.pagmodulo.presentation.controller.pagbank;

import com.fillipe.pagmodulo.application.dto.checkout.request.ReqCreateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.checkout.response.ResActivateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.checkout.response.ResCheckoutCreatedDto;
import com.fillipe.pagmodulo.application.dto.checkout.response.ResCheckoutDto;
import com.fillipe.pagmodulo.application.dto.checkout.response.ResInactivateCheckoutDto;
import com.fillipe.pagmodulo.presentation.mapper.checkout.CheckoutMapper;
import com.fillipe.pagmodulo.application.usecase.ActivateCheckout.ActivateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutResponse;
import com.fillipe.pagmodulo.application.usecase.InactivateCheckout.InactivateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.GetCheckout.GetCheckoutUseCase;
import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Profile;
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
    public ResponseEntity<ResCheckoutCreatedDto> createCheckout(@Valid @RequestBody ReqCreateCheckoutDto request) {
        CreateCheckoutCommand command = CheckoutMapper.toCreateCheckoutCommand(request);
        CreateCheckoutResponse createCheckoutResponse = createCheckoutUseCase.execute(command);

        ResCheckoutCreatedDto response = CheckoutMapper.toResCheckoutCreatedDto(createCheckoutResponse.checkout(), createCheckoutResponse.payLink());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{uuid}")
    @Profile("dev")
    public ResponseEntity<ResCheckoutDto> getCheckout(@PathVariable UUID uuid) {
        Checkout checkout = getCheckoutUseCase.execute(uuid);

        ResCheckoutDto response = CheckoutMapper.toCheckoutResponseDTO(checkout);
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
