package com.leave.backend.Services.Implementation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Entities.Site;
import com.leave.backend.Entities.Site;
import com.leave.backend.Exceptions.SiteNotFoundException;
import com.leave.backend.Repositories.SiteRepository;
import com.leave.backend.Services.SiteService;
import com.leave.backend.mappers.SiteMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Transactional
@AllArgsConstructor
@Service
public class SiteServiceImpl implements SiteService {
    private final SiteMapper siteMapper;
    private final SiteRepository siteRepository;

 
    @Override
    public SiteDTO addSite(SiteDTO siteDTO){
      
        Site site = siteMapper.fromSiteDTO(siteDTO);
        // Save new user in database
        Site savedLeaveType = siteRepository.save(site);
        // Convert Site entity to SiteDTO using the siteMapper
        return siteMapper.fromSite(savedLeaveType);
    }

    @Override
    public SiteDTO updateSite( int id,SiteDTO siteDTO) throws SiteNotFoundException{

          Site site = siteRepository.findById(id)
         .orElseThrow(() -> new SiteNotFoundException("not found"));
   

            // Update the properties of the existing user
            site.setName(siteDTO.getName());
           
            // ... update other properties ...

            Site updatedUser = siteRepository.save(site);
            return siteMapper.fromSite(updatedUser);




    
    }

    @Override
      public List<SiteDTO> getAllSites() {
        List<Site> allLeaveTypes = siteRepository.findAll();
        List<SiteDTO> leaveTypeDTOList = allLeaveTypes.stream()
                .map(siteMapper::fromSite)
                .collect(Collectors.toList());
        return leaveTypeDTOList;
    }
    
    @Override
     public void deleteSiteById(int id){
        Optional<Site> OptionalLeaveType = siteRepository.findById(id);

        Site site = OptionalLeaveType.get();

        // Convert the Room entity to RoomDTO using the roomMapper
        SiteDTO siteDTO = siteMapper.fromSite(site);

        siteRepository.delete(site);

     }

     @Override
     public SiteDTO findById(int id) throws SiteNotFoundException{
         Site site = siteRepository.findById(id)
         .orElseThrow(() -> new SiteNotFoundException("not found"));
                 
         return siteMapper.fromSite(site);
     }
}
