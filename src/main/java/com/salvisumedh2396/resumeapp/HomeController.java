package com.salvisumedh2396.resumeapp;

import com.salvisumedh2396.resumeapp.models.Job;
import com.salvisumedh2396.resumeapp.models.User;
import com.salvisumedh2396.resumeapp.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){
        /*UserProfile userProfile1 = new UserProfile();
        userProfile1.setId(1);
        userProfile1.setUserName("sumedh");
        userProfile1.setDesignation("Student");
        userProfile1.setFirstName("Sumedh");
        userProfile1.setLastName("Salvi");
        userProfile1.setEmail("ssalvi@ncsu.edu");
        userProfile1.setPhone("984-202-9201");
        userProfile1.setSummary("Just some Lorem Ipsum");
        userProfile1.setTheme(1);*/

        Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("sumedh");
        profileOptional.orElseThrow(() -> new RuntimeException("Not found"));

        UserProfile userProfile1 = profileOptional.get();

        Job job1 = new Job();
        job1.setCompany("Company1");
        job1.setDesignation("Designation");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2020,1,1));
        job1.setEndDate(LocalDate.of(2020,3,1));
        Job job2 = new Job();
        job2.setCompany("Company2");
        job2.setDesignation("Designation2");
        job2.setId(2);
        job2.setStartDate(LocalDate.of(2019,5,1));
        job2.setEndDate(LocalDate.of(2020,3,1));
        /*List<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);*/
        userProfile1.getJobs().clear();
        userProfile1.getJobs().add(job1);
        userProfile1.getJobs().add(job2);

        //userProfile1.setJobs(jobs);
        userProfileRepository.save(userProfile1);

        return "profile";
    }

    @GetMapping("/edit")
    public String edit(){
        return "Edit Page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") String userId, Model model){
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);

        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: "+ userId));

        model.addAttribute("userId",userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);

        System.out.println(userProfile.getJobs());
        return "profile-templates/"+userProfile.getTheme()+"/index";
    }
}