package sg.edu.nus.iss.day14workshop.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day14workshop.model.User;

@Repository
public class AppRepository {

    private static final String ALL_CONTACTS = "allcontacts";

    @Autowired
    RedisTemplate<String, Object> redisTemplate; 

    public User createContact(User user) {
        redisTemplate.opsForHash().put(ALL_CONTACTS, user.getId(), user);
        return (User) redisTemplate.opsForHash().get(ALL_CONTACTS, user.getId());
    }

    public User getContactById(String id) {
        User user = (User) redisTemplate.opsForHash().get(ALL_CONTACTS, id);
        return user;
    }

    public List<String> getAllContacts(){
        List<String> contactIDs = redisTemplate.opsForHash()
                                                .keys(ALL_CONTACTS)
                                                .stream()
                                                .map(obj -> (String) obj)
                                                .collect(Collectors.toList());
        return contactIDs;
    }
}
