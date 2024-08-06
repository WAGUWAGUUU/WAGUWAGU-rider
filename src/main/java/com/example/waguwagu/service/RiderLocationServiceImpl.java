package com.example.waguwagu.service;

import com.example.waguwagu.domain.entity.RiderLocation;
import com.example.waguwagu.domain.request.RiderLocationRequest;
import com.example.waguwagu.domain.response.RiderLocationResponse;
import com.example.waguwagu.global.exception.RiderLocationNotFoundException;
import com.example.waguwagu.global.repository.RiderLocationRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderLocationServiceImpl implements RiderLocationService {
    private final RiderLocationRedisRepository riderLocationRedisRepository;

    @Override
    public void saveRiderLocation(RiderLocationRequest req) {
        log.info(req.toString());
        riderLocationRedisRepository.save(req.toEntity());
        log.info("success");
    }

    @Override
    public RiderLocationResponse getByOrderId(UUID orderId) {
        RiderLocation location = riderLocationRedisRepository.findById(orderId)
                .orElseThrow(RiderLocationNotFoundException::new);
        RiderLocationResponse res = RiderLocationResponse.from(location);
        return res;
    }

    @Override
    public void deleteByOrderId(UUID orderId) {
        RiderLocation location = riderLocationRedisRepository.findById(orderId)
                .orElseThrow(RiderLocationNotFoundException::new);
        riderLocationRedisRepository.deleteById(orderId);
    }
}