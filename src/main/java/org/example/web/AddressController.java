package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.entity.Address;
import org.example.repository.AddressRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping("/{id}")
    public Mono<Address> getAddress(@PathVariable UUID id) {
        return addressRepository.findById(id);
    }

    @PostMapping
    public Mono<Address> createAddress(Address address) {
        return addressRepository.save(address);
    }

}
