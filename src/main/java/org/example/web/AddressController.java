package org.example.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Address;
import org.example.repository.AddressRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/v1/addresses")
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping("/{id}")
    public Mono<Address> getAddress(@PathVariable UUID id) {
        return addressRepository.findById(id);
    }

    @GetMapping
    public Flux<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @PostMapping
    public Mono<Address> createAddress(@RequestBody Address address) {
        log.info("Address to save is {}", address);
        return addressRepository.save(address);
    }

}
