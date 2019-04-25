package dto;

import Models.User;

import javax.ejb.Stateless;

@Stateless
public class UserMapper {

    public UserDTO mapUserToDTO(User user){
        UserDTO returnDTO= new UserDTO();
        returnDTO.setBio(user.getBio());
        returnDTO.setFollowers(user.getFollowers());
        returnDTO.setFollowing(user.getFollowing());
        returnDTO.setId(user.getID());
        returnDTO.setKweets(user.getKweets());
        returnDTO.setLocation(user.getLocation());
        returnDTO.setUsername(user.getUsername());
        returnDTO.setProfilePicture(user.getProfilePicture());
        returnDTO.setWebsite(user.getWebsite());
        returnDTO.setRoles(user.getRoles());

        return returnDTO;
    }
}
