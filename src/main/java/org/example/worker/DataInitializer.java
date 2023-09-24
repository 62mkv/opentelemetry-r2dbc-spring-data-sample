package org.example.worker;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Address;
import org.example.repository.AddressRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "initialize", name = "data", matchIfMissing = true)
@Slf4j
@DependsOn({"databaseStartupValidator"})
public class DataInitializer implements InitializingBean {

    private final AddressRepository addressRepository;
    @Override
    public void afterPropertiesSet() {
        addressRepository.count()
                .filter(count -> count > 500)
                .switchIfEmpty(insertFakeData().then(addressRepository.count()))
                .block();
    }

    private Mono<Void> insertFakeData() {
        return Mono.defer(() -> {
            log.info("Adding addresses");
            Faker faker = new Faker();
            var addressesToAdd = new ArrayList<Address>();
            for (int i = 0; i < 100; i++) {
                var address = new Address();
                var fakeAddress = faker.address();
                address.setCity(fakeAddress.city());
                address.setCountry(fakeAddress.country());
                address.setStreet(fakeAddress.streetAddress());
                addressesToAdd.add(address);
            }

            return addressRepository.saveAll(addressesToAdd)
                    .doOnComplete(() -> log.info("Finished adding addresses"))
                    .then();
        });
    }
}
