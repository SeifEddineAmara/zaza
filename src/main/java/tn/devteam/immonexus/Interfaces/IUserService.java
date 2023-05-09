package tn.devteam.immonexus.Interfaces;

import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Exception.domain.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IUserService {
    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void deleteUser(String username) throws IOException;
}
