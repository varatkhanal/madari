package com.vadin.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vadin.dto.RoleDTO;
import com.vadin.model.Privilege;
import com.vadin.dto.PrivilegeDTO;
import com.vadin.dto.UserDTO;
import com.vadin.exceptions.ErrorMessages;
import com.vadin.exceptions.UserServiceException;
import com.vadin.model.Role;
import com.vadin.model.Users;
import com.vadin.repository.UserRepository;
import com.vadin.repository.RoleRepository;
import com.vadin.shared.Utils;
import com.vadin.repository.AddressRepository;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
   // @Autowired
  //  private PrivilegeRepository privilegeRepository;
    
    @Autowired
    AddressRepository addressRepository;
   
    @Autowired
    Utils utils;
    
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserDTO createUser(UserDTO userDto) {
    	
    	if(userRepository.findByEmail(userDto.getEmail())!=null) throw new RuntimeException("Email already exist");
    	else if(userRepository.findByUserName(userDto.getUserName())!=null) throw new RuntimeException("Username already exist");
    	Users users = new Users(); 
    	String publicUserId=utils.generatedUserId(30);

    	RoleDTO strRoles = userDto.getRole();
		Role role = new Role();

		if (strRoles == null) {
			role = roleRepository.findByName("ROLE_USER");
		} else {
			switch (strRoles.getName()) {
				case "admin":
					role = roleRepository.findByName("ROLE_ADMIN");
					break;
				case "moderator":
					role = roleRepository.findByName("ROLE_MODERATOR");
					break;
				case "author":
					role = roleRepository.findByName("ROLE_AUTHOR");
					break;
				default:
					role = roleRepository.findByName("ROLE_USER");
			}
		}

		ModelMapper modelMapper = new ModelMapper();
    	
    	users=modelMapper.map(userDto,Users.class);	
    	users.setRole(role);	
    	users.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    	users.setUserId(publicUserId);
    	
    	Users savedEntity = userRepository.save(users);
    	UserDTO returnedDto=getUser(savedEntity.getUserName());
    	
    	return returnedDto;
    }  

    public UserDTO getUser(String userName) {
        // Fetch user details from the database
        Users user = userRepository.findByUserName(userName);

        // Fetch user's roles and privileges using a join query
        Role role = user.getRole();       
		List<Privilege> privileges = role.getPrivileges();	
		ModelMapper modelMapper = new ModelMapper();   	  	
    	RoleDTO roleDto=modelMapper.map(role, RoleDTO.class);	
    	List<PrivilegeDTO> privilegeDto = privileges.stream()
    		    .map(priv -> modelMapper.map(priv, PrivilegeDTO.class))
    		    .collect(Collectors.toList());
    	
    	roleDto.setPrivilages(privilegeDto);

        // Map the user, roles, and privileges to a UserDto object
        UserDTO userDto = new UserDTO();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getEncryptedPassword());
        userDto.setEmail(user.getEmail());
        userDto.setRole(roleDto);

        return userDto;
    }
    
  	@Override
  	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
  		Users userEntity = userRepository.findByUserName(userName);
  		if(userEntity==null) throw new UsernameNotFoundException(userName);
  		//return new User();
  		return new User(userEntity.getUserName(),userEntity.getEncryptedPassword(),getAuthorities(userEntity.getRole()));
  	} 
  	
  	 // userservice object can get me a user authorities
  	private final Collection<? extends GrantedAuthority> getAuthorities(final Role role) {
          return getGrantedAuthorities(getPrivileges(role));
      }
  	
  	// userservice object can get me a user authorities
      private final List<String> getPrivileges(final Role role) {
          final List<String> privileges = new ArrayList<String>();
          final List<Privilege> collection = role.getPrivileges();//new ArrayList<Privilege>();
         
          for (final Privilege item : collection) {
              privileges.add(item.getName());
          }
          return privileges;
      }
     
      private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
          final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
          for (final String privilege : privileges) {
              authorities.add(new SimpleGrantedAuthority(privilege));
          }
          return authorities;
      }

	@Override
	public UserDTO getUserByUserId(String userId) {
		UserDTO userDto=new UserDTO();
		Users retrivedValue=userRepository.findByUserId(userId); 
		if(retrivedValue==null)throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		userDto = modelMapper.map(retrivedValue, UserDTO.class);
		return userDto;
	}

	@Override
	public UserDTO updateUser(String userId,UserDTO userDto) {
   	
		Users Users = userRepository.findByUserId(userId);
		if(Users==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
    	Users.setFirstName(userDto.getFirstName());
    	Users.setLastName(userDto.getLastName());
    	Users.setUserName(userDto.getUserName());
    	Users.setEmail(userDto.getEmail());
    	
    	Users updatedEntity = userRepository.save(Users); 	 
    	UserDTO returnedDto=new UserDTO();
    	BeanUtils.copyProperties(updatedEntity, returnedDto);
    	 
    	return returnedDto;
	}

	@Override
	public void deleteUser(String userId) {
		Users Users=userRepository.findByUserId(userId);
		
		if(Users==null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());	
		userRepository.delete(Users);	
	}
	
	@Override
	public List<UserDTO> getUsers(int page, int limit){
		List<UserDTO> returnedValue = new ArrayList<>();
		
		if(page>0) page-=1;
		
		Pageable pageableRequest= PageRequest.of(page, limit);
		Page<Users> usersPage=userRepository.findAll(pageableRequest);
		
		List<Users> users = usersPage.getContent();	
		for(Users user:users) {
			UserDTO userDto=new UserDTO();
			BeanUtils.copyProperties(user, userDto);
			returnedValue.add(userDto);
		}
		return returnedValue;
	}	
}
