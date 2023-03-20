package sg.edu.nus.iss.day14workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day14workshop.model.User;
import sg.edu.nus.iss.day14workshop.repository.AppRepository;

@Service
public class AppService {
    
    @Autowired
    AppRepository appRepository;

    public User createContact(User user) {
        return appRepository.createContact(user);
    }

    public User getContactById(String id) {
        User user = appRepository.getContactById(id);
        return user;
    }

    public List<String> getAllContacts(){
        List<String> contactIDs = appRepository.getAllContacts();
        return contactIDs;
    }
}
