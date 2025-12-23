package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;

@Service
@Transactional
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository productivityMetricRecordRepository;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository productivityMetricRecordRepository) {
        this.productivityMetricRecordRepository = productivityMetricRecordRepository;
    }

    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {

        // 1️⃣ LOAD EXISTING RECORD
        ProductivityMetricRecord existing =
                productivityMetricRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Metric not found"));

        // 2️⃣ UPDATE FIELDS (ID & submittedAt stay untouched)
        existing.setDate(updated.getDate());
        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());
        existing.setRawDataJson(updated.getRawDataJson());

        // 3️⃣ RECALCULATE SCORE (ignore request score)
        Double score = ProductivityCalculator.computeScore(existing);
        existing.setProductivityScore(score);

        // 4️⃣ SAVE AND RETURN
        return productivityMetricRecordRepository.save(existing);
    }
}
