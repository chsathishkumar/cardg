package com.cardg.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.cardg.model.AccessRights;
import com.cardg.model.Role;
import com.cardg.model.User;
import com.cardg.repository.AccessRightRepository;
import com.cardg.repository.RoleRepository;
import com.cardg.repository.UserRepository;

@Service
public class UserService {

	Properties properties = new Properties();
	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	InputStream resource = classloader.getResourceAsStream("application-constants.properties");

	public UserService() {
		try {
			properties.load(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public User getProfile(String userEmail, User user, UserRepository userRepository) {
		return userRepository.findByUserEmail(userEmail);
	}

	// public User udpateProfile(String userEmail, String userName, String
	// phoneNumber, String addressOne,
	// String addressTwo, String city, String state, String country, String
	// zipcode, User objUser,
	// UserRepository userRepository) {
	//
	// List<User> listUser = userRepository.findByuserEmail(userEmail);
	// System.out.println(listUser);
	// if (listUser.size() > 0) {
	// listUser.get(0).setUserName(userName);
	// listUser.get(0).setUserPhonenumber(phoneNumber);
	// listUser.get(0).setUserAddressOne(addressOne);
	// listUser.get(0).setUserAddressTwo(addressTwo);
	// listUser.get(0).setUserCity(city);
	// listUser.get(0).setUserState(state);
	// listUser.get(0).setUserCountry(country);
	// listUser.get(0).setZipCode(zipcode);
	// }
	// return userRepository.save(listUser.get(0));
	// }

	public User userExists(String userEmail, String userPassword, UserRepository userRepository) {
		return userRepository.findByUserEmail(userEmail);
	}

	public List<User> getAllUsers(UserRepository userRepository) {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public void deleteSupplier(long supplierId, UserRepository userRepository) {
		// TODO Auto-generated method stub
		userRepository.delete(supplierId);

	}

	public User getSupplierQuotesByStatus(long supplierID, UserRepository userRepository) {
		return userRepository.findOne(supplierID);
	}

	public User udpateProfile(String userEmail, String userName, String phoneNumber, String addressOne,
			String addressTwo, String city, String state, String country, String zipcode, User objUser,
			UserRepository userRepository) {

		User user = userRepository.findByUserEmail(userEmail);

		user.setUserName(userName);
		user.setUserPhonenumber(phoneNumber);
		user.setUserAddressOne(addressOne);
		user.setUserAddressTwo(addressTwo);
		user.setUserCity(city);
		user.setUserState(state);
		user.setUserCountry(country);
		user.setZipCode(zipcode);
		return userRepository.save(user);
	}

	public List<Role> getAllRoles(RoleRepository rolesRepository) {
		return rolesRepository.findAll();
	}

	public List<AccessRights> getAllAccessRights(AccessRightRepository accessRightRepository) {
		return accessRightRepository.findAll();
	}

	public Role addRole(String roleName, ArrayList<AccessRights> accessList, RoleRepository roleRepository) {
		List<AccessRights> listAccessRight = new ArrayList<>();
		Role role = new Role();
		role.setRoleName(roleName);
		role.setStatus(properties.get("STATUS_ACTIVE").toString());

		for (AccessRights accessRights : accessList) {
			listAccessRight.add(accessRights);
		}
		role.setListAcessRights(listAccessRight);
		return roleRepository.save(role);
	}

	public AccessRights addAccessRight(String accessRightsName, AccessRightRepository accessRightRepository) {
		AccessRights accessRight = new AccessRights();
		accessRight.setAccessRightsName(accessRightsName);
		accessRight.setAccessRightsStatus("true");
		return accessRightRepository.save(accessRight);
	}

	public Role updateRole(long roleID, String roleName, ArrayList<String> accessList, RoleRepository roleRepository,
			AccessRightRepository accessRightRepository) {
		List<AccessRights> listAccessRights = new ArrayList<>();
		for (String accessRight : accessList) {
			String newAccessRight = accessRight.replaceAll("^\"|\"$", "");
			AccessRights accessRights = accessRightRepository.findOne(Long.parseLong(newAccessRight));
			listAccessRights.add(accessRights);
		}
		Role role = roleRepository.findOne(roleID);
		role.setRoleName(roleName);
		role.setListAcessRights(listAccessRights);
		return roleRepository.save(role);
	}
	
	public User saveUser(String userEmail, String userName, String userPassword, String userLocation, String userType,
			User user, UserRepository userRepository) {

		user.setUserEmail(userEmail);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setDateCreated(new Date());
		user.setUserStatus("pending");
		user.setUserType(userType);

		User modifiedUser = userRepository.save(user);

		return modifiedUser;

	}

	public User updateUser(long userID, String userStatus, String roleName, UserRepository userRepository,
			RoleRepository roleRepository) {
		User user = userRepository.findOne(userID);
		user.setRole(roleRepository.findByRoleName(roleName));
		user.setUserStatus(userStatus);
		return userRepository.save(user);
	}

	public AccessRights updateAccessRight(long accessRightsID, String accessRightName, String accessRightsStatus,
			AccessRightRepository accessRightRepository) {
		AccessRights accessRights = accessRightRepository.findOne(accessRightsID);
		accessRights.setAccessRightsName(accessRightName);
		accessRights.setAccessRightsStatus(accessRightsStatus);
		return accessRightRepository.save(accessRights);
	}
}
