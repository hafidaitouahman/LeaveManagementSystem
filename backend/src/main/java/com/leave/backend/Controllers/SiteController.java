package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Exceptions.SiteNotFoundException;
import com.leave.backend.Services.SiteService;

import java.util.List; 
@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")
 @RestController
@RequestMapping("/api/site")
public class SiteController {
    private final SiteService siteService;

    
    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('RH')")
    public SiteDTO addSite(@RequestBody SiteDTO siteDTO){
        return siteService.addSite(siteDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RH')")
    public SiteDTO updateSite(@PathVariable int id,@RequestBody SiteDTO siteDTO)throws SiteNotFoundException{
        return siteService.updateSite(id,siteDTO);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('RH')")
    public List<SiteDTO> getAllSites() {
        return siteService.getAllSites();
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('RH')")
    public void deleteSiteById(@PathVariable int id){
        siteService.deleteSiteById(id);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RH')")
    public SiteDTO getById(@PathVariable int id) throws SiteNotFoundException {
        return siteService.findById(id);
    }
    
}
