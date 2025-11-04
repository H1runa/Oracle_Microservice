package com.hiruna.oracle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiruna.oracle.data.model.AppUser;
import com.hiruna.oracle.service.AppUserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/appuser")
public class AppUserController {
    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService){
        this.appUserService=appUserService;
    }

    @PostMapping("")
    public ResponseEntity<AppUser> createAppUser(@RequestBody AppUser user) {
        try {
            AppUser saved_user = appUserService.createAppUser(user);
            System.out.println("App User has been created");          
            return ResponseEntity.ok(saved_user);  
        } catch (Exception e) {
            System.err.println("ERROR: App User Not Created");
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    //update request
    @PutMapping("")
    public ResponseEntity<?> updateAppUser(@RequestBody AppUser user) {
        try{
            Boolean state = appUserService.updateAppUser(user);
            return ResponseEntity.ok(state);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR: Update Failed.");
        }
    }

    //delete request
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppUser(@PathVariable long id){
        try{
            Boolean state = appUserService.deleteAppUser(id);
            return ResponseEntity.ok(state);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR: Deletion Failed.");
        }
    }

    //exists request
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsAppUser(@PathVariable long id) {
        return ResponseEntity.ok(appUserService.existsAppUser(id));
    }
    
    
}
