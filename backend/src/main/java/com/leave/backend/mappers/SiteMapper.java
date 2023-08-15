package com.leave.backend.mappers;

import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Entities.Site;

import org.springframework.beans.BeanUtils;

@Service
public class SiteMapper {
      public SiteDTO fromSite(Site site){
        SiteDTO siteDTO=new SiteDTO();
        BeanUtils.copyProperties(site, siteDTO);
        siteDTO.setName(site.getName());

        return siteDTO;
    }
    public Site fromSiteDTO(SiteDTO siteDTO){
    Site site=new Site();
    BeanUtils.copyProperties(siteDTO, site);
    site.setName(siteDTO.getName());

    return site;
}
}
