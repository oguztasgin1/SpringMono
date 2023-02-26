package com.oguztasgin.SpringMono.service;

import com.oguztasgin.SpringMono.dto.request.UrunSaveRequestDto;
import com.oguztasgin.SpringMono.dto.request.UrunUpdateRequestDto;
import com.oguztasgin.SpringMono.mapper.IUrunMapper;
import com.oguztasgin.SpringMono.repository.IUrunRepository;
import com.oguztasgin.SpringMono.repository.entity.Urun;
import com.oguztasgin.SpringMono.service.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UrunService extends ServiceManager<Urun,Long> {
    public UrunService(IUrunRepository repository){
        super(repository);
    }

    public void save(UrunSaveRequestDto dto){
        save(IUrunMapper.INSTANCE.urunFromDto(dto));
    }

    public void update(UrunUpdateRequestDto dto){
        update(IUrunMapper.INSTANCE.urunFromUpdateDto(dto));
    }
}