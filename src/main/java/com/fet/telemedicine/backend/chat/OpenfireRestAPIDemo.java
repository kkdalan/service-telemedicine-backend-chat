package com.fet.telemedicine.backend.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.GroupEntity;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.igniterealtime.restclient.entity.RosterItemEntity;
import org.igniterealtime.restclient.entity.SystemProperty;
import org.igniterealtime.restclient.entity.UserEntities;
import org.igniterealtime.restclient.entity.UserEntity;
import org.igniterealtime.restclient.entity.UserGroupsEntity;

public class OpenfireRestAPIDemo {

    public static void main(String[] args) {

	testUserRelatedExamples();

	testChatRoomsRelatedExamples();

	testSessionRelatedExamples();

	testSystemRelatedExamples();

	testGroupRelatedExamples();
	
	testRosterRelatedExamples();

    }

    private static void testRosterRelatedExamples() {
	// Set Shared secret key
	AuthenticationToken authenticationToken = new AuthenticationToken("admin", "admin");
	// Set Openfire settings (9090 is the port of Openfire Admin Console)
	RestApiClient restApiClient = new RestApiClient("http://localhost", 9090, authenticationToken);

	// Retrieve user roster
	restApiClient.getRoster("testUsername");

	// Create a user roster entry (Possible values for subscriptionType are: -1
	// (remove), 0 (none), 1 (to), 2 (from), 3 (both))
	RosterItemEntity rosterItemEntity = new RosterItemEntity("testUser2@testdomain.com", "TestUser2", 3);
	// Groups are optional
	List<String> groups = new ArrayList<String>();
	groups.add("Supporter");
	rosterItemEntity.setGroups(groups);
	restApiClient.addRosterEntry("testUsername", rosterItemEntity);

	// Update a user roster entry
	rosterItemEntity = new RosterItemEntity("testUser2@testdomain.com", "SomeUser", 3);
	restApiClient.updateRosterEntry("testUsername", rosterItemEntity);

	// Delete a user roster entry
	restApiClient.deleteRosterEntry("testUsername", "testUser2@testdomain.com");
    }

    private static void testGroupRelatedExamples() {
	// Set Shared secret key
	AuthenticationToken authenticationToken = new AuthenticationToken("admin", "admin");
	// Set Openfire settings (9090 is the port of Openfire Admin Console)
	RestApiClient restApiClient = new RestApiClient("http://localhost", 9090, authenticationToken);

	// Retrieve all groups
	restApiClient.getGroups();

	// Retrieve specific group
	restApiClient.getGroup("Moderators");

	// Create a group
	GroupEntity groupEntity = new GroupEntity("Moderators", "Moderator Group");
	restApiClient.createGroup(groupEntity);

	// Update a group
	groupEntity = new GroupEntity("Moderators", "Changed Moderator Group description");
	restApiClient.updateGroup(groupEntity);

	// Delete a group
	restApiClient.deleteGroup("Moderators");
    }

    private static void testSystemRelatedExamples() {
	// Set Shared secret key
	AuthenticationToken authenticationToken = new AuthenticationToken("admin", "admin");
	// Set Openfire settings (9090 is the port of Openfire Admin Console)
	RestApiClient restApiClient = new RestApiClient("http://localhost", 9090, authenticationToken);

	// Retrieve all system properties
	restApiClient.getSystemProperties();

	// Retrieve specific system property e.g. "xmpp.domain"
	restApiClient.getSystemProperty("xmpp.domain");

	// Create a system property
	SystemProperty systemProperty = new SystemProperty("propertyName", "propertyValue");
	restApiClient.createSystemProperty(systemProperty);

	// Update a system property
	systemProperty = new SystemProperty("propertyName", "ChangedPropertyValue");
	restApiClient.updateSystemProperty(systemProperty);

	// Delete a system property
	restApiClient.deleteSystemProperty("propertyName");
    }

    private static void testSessionRelatedExamples() {
	// Set Shared secret key
	AuthenticationToken authenticationToken = new AuthenticationToken("admin", "admin");
	// Set Openfire settings (9090 is the port of Openfire Admin Console)
	RestApiClient restApiClient = new RestApiClient("http://localhost", 9090, authenticationToken);

	// Request all active Sessions
	restApiClient.getSessions();

	// Request all active Sessions from a specific user
	restApiClient.getSessions("admin");
    }

    private static void testChatRoomsRelatedExamples() {
	// Set Shared secret key
	AuthenticationToken authenticationToken = new AuthenticationToken("admin", "admin");
	// Set Openfire settings (9090 is the port of Openfire Admin Console)
	RestApiClient restApiClient = new RestApiClient("http://localhost", 9090, authenticationToken);

	// Request all public chatrooms
	restApiClient.getChatRooms();

	// Search for the chat room with the room name "test". This act like the
	// wildcard search %String%
	HashMap<String, String> querys = new HashMap<String, String>();
	querys.put("search", "test");
	restApiClient.getChatRooms(querys);

	// Create a new chat room (chatroom id, chatroom name, description). There are
	// more chatroom settings available.
	MUCRoomEntity chatRoom = new MUCRoomEntity("chatroom1", "First Chat Room", "Some description");
	restApiClient.createChatRoom(chatRoom);

	// Update a chat room
	chatRoom.setDescription("Updated description");
	restApiClient.updateChatRoom(chatRoom);

	// Delete a chat room
	restApiClient.deleteChatRoom("chatroom1");

	// Add user with role "owner" to a chat room
	restApiClient.addOwner("chatroom1", "username");

	// Add user with role "admin" to a chat room
	restApiClient.addAdmin("chatroom1", "username");

	// Add user with role "member" to a chat room
	restApiClient.addMember("chatroom1", "username");

	// Add user with role "outcast" to a chat room
	restApiClient.addOutcast("chatroom1", "username");

	// Get all participants from a specified chat room
	restApiClient.getChatRoomParticipants("chatroom1");
    }

    private static void testUserRelatedExamples() {
	// Set Shared secret key
	AuthenticationToken authenticationToken = new AuthenticationToken("admin", "admin");
	// Set Openfire settings (9090 is the port of Openfire Admin Console)
	RestApiClient restApiClient = new RestApiClient("http://localhost", 9090, authenticationToken);

	// Request all available users
	UserEntities users = restApiClient.getUsers();
	System.out.println(String.format("Available users count = '%d' ", users.getUsers().size()));

	// Get specific user by username
	UserEntity user = restApiClient.getUser("alan");
	System.out.println(String.format("Found user('%s'): name= '%s', password= '%s', email= '%s' ",
		user.getUsername(), user.getName(), user.getPassword(), user.getEmail()));

	// Search for the user with the username "test". This act like the wildcard
	// search %String%
	HashMap<String, String> querys = new HashMap<String, String>();
	querys.put("search", "alan");
	users = restApiClient.getUsers(querys);
	System.out.println(String.format("Search users, found = '%d' ", users.getUsers().size()));

	// Create a new user (username, name, email, password). There are more user
	// settings available.
	UserEntity userEntity = new UserEntity("testUsername", "testName", "test@email.com", "p4ssw0rd");
	restApiClient.createUser(userEntity);

	// Update a user
	userEntity.setName("newName");
	restApiClient.updateUser(userEntity);

	// Delete a user
	restApiClient.deleteUser("testUsername");

	// Get all user groups from a user
	restApiClient.getUserGroups("testUsername");

	// Add user to groups
	List<String> groupNames = new ArrayList<String>();
	groupNames.add("Moderators");
	groupNames.add("Supporters");
	UserGroupsEntity userGroupsEntity = new UserGroupsEntity(groupNames);
	restApiClient.addUserToGroups("testUsername", userGroupsEntity);

	// Add user to group
	restApiClient.addUserToGroup("testUsername", "Moderators");

	// Delete user from a group
	restApiClient.deleteUserFromGroup("testUsername", "Moderators");

	// Lockout/Ban a user
	restApiClient.lockoutUser("testUsername");

	// Unlock/Unban a user
	restApiClient.unlockUser("testUsername");
    }
}
