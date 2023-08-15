package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Exceptions.SiteNotFoundException;

public interface SiteService {
    SiteDTO addSite(SiteDTO siteDTO);
    SiteDTO updateSite( int id,SiteDTO siteDTO) throws SiteNotFoundException;
    List<SiteDTO> getAllSites();
    void deleteSiteById(int id);
    SiteDTO findById(int id) throws SiteNotFoundException;

}
