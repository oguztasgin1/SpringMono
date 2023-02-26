package com.oguztasgin.SpringMono.service;

import com.oguztasgin.SpringMono.repository.ISatisRepository;
import com.oguztasgin.SpringMono.repository.entity.Satis;
import com.oguztasgin.SpringMono.service.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class SatisService extends ServiceManager<Satis,Long> {
    private final ISatisRepository repository;
    public SatisService(ISatisRepository repository){
        super(repository);
        this.repository = repository;
    }

}
