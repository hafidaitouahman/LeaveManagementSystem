package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Exceptions.SiteNotFoundException;
import com.leave.backend.Services.SiteService;

import java.util.List;
@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/site")
public class SiteController {
    private final SiteService siteService;

    
    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    
    }
    @PostMapping("/add")
    public SiteDTO addSite(@RequestBody SiteDTO siteDTO){
        return siteService.addSite(siteDTO);
    }

    @PutMapping("/{id}")
    public SiteDTO updateSite(@PathVariable int id,@RequestBody SiteDTO siteDTO)throws SiteNotFoundException{
        return siteService.updateSite(id,siteDTO);
    }

    @GetMapping("/all")
    public List<SiteDTO> getAllSites() {
        return siteService.getAllSites();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSiteById(@PathVariable int id){
        siteService.deleteSiteById(id);
    }
    @GetMapping("/{id}")
    public SiteDTO getById(@PathVariable int id) throws SiteNotFoundException {
        return siteService.findById(id);
    }
    
}
